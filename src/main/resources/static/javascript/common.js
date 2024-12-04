class ModalObj {

    cover;

    constructor() {
        const cover = document.createElement('div');
        cover.setAttribute('id', 'cover');
        document.body.prepend(cover);
        this.cover = cover;
    }

    createButton(text, onclick) {
        const button = document.createElement('button');
        button.setAttribute('type', 'button');
        button.innerText = text;
        button.onclick = () => onclick();
        return button;
    }

    createModal(title, text, buttonOptions) {
        this.cover.innerHTML = '';

        const modal = new DOMParser().parseFromString(
            `
            <div class="xmas-modal">
                <span class="title">${title}</span>
                <span class="text">${text}</span>
                <div class="button-container">
                </div>
            </div>
            `,'text/html').querySelector('.xmas-modal');

        const buttonContainer = modal.querySelector('.button-container');

        for (const buttonOption of buttonOptions) {
            const button = this.createButton(buttonOption['title'], buttonOption['onclick']);
            buttonContainer.append(button);
        }

        this.cover.append(modal);
    }

    createSimpleForm(title, buttonOptions) {
        this.cover.innerHTML = '';

        const modal = new DOMParser().parseFromString(
            `
            <div class="xmas-modal">
                <span class="title">${title}</span>
                    <label>
                        <input type="password" id="pw" name="pw">
                    </label>
                <div class="button-container">
                </div>
            </div>
            `,'text/html').querySelector('.xmas-modal');

        const buttonContainer = modal.querySelector('.button-container');

        for (const buttonOption of buttonOptions) {
            const button = this.createButton(buttonOption['title'], buttonOption['onclick']);
            buttonContainer.append(button);
        }
        this.cover.append(modal);
    }

    createSimpleButton(title, text) {
        this.createModal(title, text, [{
            title : '닫기',
            onclick : () => this.remove()
        }])
    }


    createProductDetail(id, buttonOptions) {
        this.cover.innerHTML = '';

        const productModal = new DOMParser().parseFromString(`
            <div class="detail xmas-modal">
                <input type="hidden" name="id" value="${id}">
                <span class="title">상품 설명</span>
                <div class="main">
                    <div class="img-box">
                        <img src="../imgs/whitebell.jpg" alt="">
                    </div>
                    <div class="info-box">
                        <span class="info-text">상품 상세 정보</span>
                        <table class="info-table">
                            <tr>
                                <th class="subject">이름</th>
                                <td class="text">하얀 종 오너먼트</td>
                            </tr>
                            <tr>
                                <th class="subject">분류</th>
                                <td class="text">종</td>
                            </tr>
                            <tr>
                                <th class="subject">가격</th>
                                <td class="text">2000</td>
                            </tr>
                            <tr>
                                <th class="subject">설명</th>
                                <td class="text">하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. </td>
                            </tr>
                        </table>
                        <div class="quantity-box">
                            <button type="button" class="minus"><i class="fa-solid fa-minus"></i></button>
                            <label>
                                <input type="number" name="quantity" value="1">
                            </label>
                            <button type="button" class="plus"><i class="fa-solid fa-plus"></i></button>
                        </div>        
                    </div>
                </div>
                <div class="button-container">
                </div>
            </div>
        `,'text/html').querySelector('.xmas-modal');

        const quantity = productModal.querySelector('input[name="quantity"]');

        productModal.querySelector('button.minus').onclick = () => {
            if (quantity.value !== "1") {
                quantity.value = parseInt(quantity.value) - 1;
            }
        }

        productModal.querySelector('button.plus').onclick = () => {
            quantity.value = parseInt(quantity.value) + 1;
        }

        const buttonContainer = productModal.querySelector('.button-container');

        for (const buttonOption of buttonOptions) {
            const button = this.createButton(buttonOption['title'], buttonOption['onclick']);
            buttonContainer.append(button);
        }

        this.cover.append(productModal);
    }

    show() {
        this.cover.classList.add('-visible');
    }

    remove() {
        this.cover.remove();
    }
}