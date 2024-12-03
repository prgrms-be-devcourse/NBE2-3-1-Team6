

const cards = document.getElementsByClassName('card');

for (const card of cards) {
    card.onclick = () => {
        const productModal = new ModalObj();
        productModal.createProductDetail(1, [{
            title : "확인",
            onclick : () => productModal.remove()
        }]);
    }
}