const eachcard = document.getElementById('eachcard');
const sfrm = document.getElementById('sfrm');

window.onload = function() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if ( xhr.readyState == 4 ) {
            if ( xhr.status == 200 ) {
                const products = JSON.parse(xhr.responseText.trim());

                let cards = '<div class="cards">';
                for(let i=0; i<products.length; i++) {
                    cards += '<div class="card">';
                    cards += '<input type="hidden" name="id" value="' + products[i].id + '">';
                    cards += '<img src="../imgs/' + products[i].imgUrl + '" alt="' + products[i].name + '"/>';
                    cards += '<div class="card-content">';
                    cards += '<h3>' + products[i].name + '</h3>';
                    cards += '<p>' + products[i].category + '</p>';
                    cards += '</div>';
                    cards += '</div>';
                }
                cards += '</div>';
                eachcard.innerHTML = cards;

                cards = eachcard.querySelectorAll('.card');

                for (let card of cards) {
                    const id = card.querySelector('input[name="id"]').value;

                    card.onclick = () => {
                        const productModal = new ModalObj();
                        const cover = document.getElementById('cover');
                        productModal.createProductDetail(id, [{
                            title : "목록에 담기",
                            onclick : () => {
                                const quantity = cover.querySelector('input[name="quantity"]').value;
                                const productName = cover.querySelector('.text.name').innerText;
                                const price = cover.querySelector('.text.price').innerText;

                                productModal.remove();

                                const row = new DOMParser().parseFromString(`
                                <div class="row">
                                    <input type="hidden" name="id" value="${id}">
                                    <input type="hidden" name="price" value="${price}">
                                    <input type="hidden" name="quantity" value="${quantity}">
                                    <h6>${productName}
                                        <span class="badge badge-box">${quantity}개</span>
                                        <span class="spring"></span>
                                        <button type="button">삭제</button>
                                    </h6>
                                </div>
                                `,'text/html').querySelector('.row');

                                row.querySelector('button').onclick = () => {
                                    row.remove();
                                    setSummaryPrice();
                                }
                                const rows = sfrm.querySelectorAll('.row');

                                for (let el of rows) {
                                    if (el.querySelector('input[name="id"]').value === id) {
                                        const originQuantity = parseInt(el.querySelector('input[name="quantity"]').value);
                                        let lastQuantity = originQuantity + parseInt(quantity);

                                        if (lastQuantity >= 99999) {
                                            lastQuantity = 99999;
                                        }

                                        el.querySelector('input[name="quantity"]').value = lastQuantity;
                                        el.querySelector('.badge').innerText = lastQuantity + "개";

                                        setSummaryPrice();

                                        return;
                                    }
                                }

                                if (rows !== null) {
                                    sfrm.insertAdjacentElement('afterbegin', row);
                                } else {
                                    rows[rows.length - 1].insertAdjacentElement('afterend', row);
                                }

                                setSummaryPrice();
                            }
                        },{
                            title : "닫기",
                            onclick : () => productModal.remove()
                        }]);
                    };
                }

            } else {
                alert("[에러] 페이지 오류")
            }

        }
    }
    xhr.open("GET", "/items", true);
    xhr.send();
}

const setSummaryPrice = () => {
    const rows = sfrm.querySelectorAll('.row');

    let allPrice = 0;

    if (rows == null) {
        return;
    }

    for (let row of rows) {
        const price = parseInt(row.querySelector('input[name="price"]').value);
        const quantity = parseInt(row.querySelector('input[name="quantity"]').value);

        const totalPrice = price * quantity;

        allPrice += totalPrice;
    }

    document.querySelector('.text-end').innerText = allPrice.toLocaleString('ko-KR') + "원";
}

document.getElementById('pay').onclick = function () {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if ((xhr.status == 200) && (xhr.responseText == "1")) {
                alert("결제가 성공적으로 처리되었습니다.");
            } else {
                alert( "결제 요청 중 오류가 발생했습니다." );
            }
        }
    }
    xhr.open( "POST", "/pay", true );
    xhr.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let customer = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let zipcode = document.getElementById("postcode").value;

    // 이메일 유효성 검사 (정규 표현식)
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("유효하지 않은 이메일 주소입니다.");
        return; // 요청 중단
    }

    // 전화번호 유효성 검사 (숫자만)
    const phoneRegex = /^[0-9]{10,15}$/; // 10~15자리 숫자
    if (!phoneRegex.test(phone)) {
        alert("유효하지 않은 전화번호입니다. 숫자만 입력하세요.");
        return; // 요청 중단
    }

    // 이름 빈 값 확인
    if (customer.trim() === "") {
        alert("이름을 입력하셔야합니다.");
        return; // 요청 중단
    }

    // 주소 빈 값 확인
    if (address.trim() === "") {
        alert("주소를 입력하셔야합니다.");
        return; // 요청 중단
    }

    // 우편번호 유효성 검사 (숫자만)
    const zipcodeRegex = /^[0-9]{5,6}$/; // 5~6자리 숫자
    if (!zipcodeRegex.test(zipcode)) {
        alert("유효하지 않은 우편번호입니다. 5~6자리 숫자만 입력하세요.");
        return; // 요청 중단
    }

    xhr.send( 'email='+email+'&phone='+phone+'&customer='+customer+'&address='+address+'&zipcode='+zipcode+'' );
}