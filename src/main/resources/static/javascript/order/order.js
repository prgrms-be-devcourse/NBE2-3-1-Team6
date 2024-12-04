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