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

