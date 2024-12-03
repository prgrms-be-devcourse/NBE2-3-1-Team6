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


    createProductDetail(id) {
        this.cover.innerHTML = '';

        const productModal = new DOMParser().parseFromString(`
        <div class="modal">
                <span class="title"></span>
                <img src="" alt="">
                <span class="text"></span>
                
                <div class="button-container">
                </div>
            </div>
        `,'text/html').querySelector('.modal');

        const buttonContainer = productModal.querySelector('.button-container');

        this.cover.append(productModal);
    }

    show() {
        this.cover.classList.add('-visible');
    }

    remove() {
        this.cover.remove();
    }
}