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
            <div class="modal">
                <span class="title">${title}</span>
                <span class="text">${text}</span>
                <div class="button-container">
                </div>
            </div>
            `,'text/html').querySelector('.modal');

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
            <div class="modal">
                <span class="title">${title}</span>
                    <label>
                        <input type="password" id="pw" name="pw">
                    </label>
                <div class="button-container">
                </div>
            </div>
            `,'text/html').querySelector('.modal');

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
        <div class="detail modal">
                <span class="title">상품 설명</span>
                <div class="main">
                    <div class="img-box">
                        <img src="../imgs/whitebell.jpg" alt="">
                    </div>
                    <div class="info-box">
                        <span class="info-text">상품 상세 정보</span>
                        <table class="info-table">
                            <tr>
                                <th>이름</th>
                                <td>하얀 종 오너먼트</td>
                            </tr>
                            <tr>
                                <th>분류</th>
                                <td>종</td>
                            </tr>
                            <tr>
                                <th>가격</th>
                                <td>2000</td>
                            </tr>
                            <tr>
                                <th>설명</th>
                                <td>하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. 하얀 종입니다. </td>
                            </tr>
                        </table>        
                    </div>
                </div>
                <div class="button-container">
                </div>
            </div>
        `,'text/html').querySelector('.modal');

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