const cards = document.getElementsByClassName('card');

for (const card of cards) {
    card.onclick = () => {
        const productModal = new ModalObj();
        productModal.createProductDetail(1, [{
            title : "목록에 담기",
            onclick : () => {
                const productId = document.getElementById('cover').querySelector('input[name="id"]');
                const productQuantity = document.getElementById('cover').querySelector('input[name="quantity"]');
                console.log(`Id = ${productId.value}, quantity = ${productQuantity.value}`);
                productModal.remove();
            }
        },{
            title : "닫기",
            onclick : () => productModal.remove()
        }]);
    };
}

window.onload = function() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if ( xhr.readyState == 4 ) {
            if ( xhr.status == 200 ) {
                const products = JSON.parse(xhr.responseText.trim());
                console.log(products);
                let cards = '<div class="cards">';
                for(let i=0; i<products.length; i++) {
                    cards += '<div class="card">';
                    cards += '<img src="../imgs/' + products[i].imgUrl + '" alt="' + products[i].name + '"/>';
                    cards += '<div class="card-content">';
                    cards += '<h3>' + products[i].name + '</h3>';
                    cards += '<p>' + products[i].category + '</p>';
                    cards += '</div>';
                    cards += '</div>';
                }
                cards += '</div>';
                document.getElementById('eachcard').innerHTML = cards;
            } else {
                alert("[에러] 페이지 오류")
            }

        }
    }
    xhr.open("GET", "/items", true);
    xhr.send();
}
