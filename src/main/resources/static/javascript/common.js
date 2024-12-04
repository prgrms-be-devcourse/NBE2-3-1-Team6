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

        const request = new XMLHttpRequest();
        request.onreadystatechange = () => {
            if (request.readyState == 4) {
                if (request.status == 200) {
                    const responseData = JSON.parse(request.responseText);

                    let name = responseData.name;
                    let category = responseData.category;
                    let price = responseData.price;
                    let description = responseData.description;
                    let imgUrl = responseData.imgUrl;

                    const detailModal = new DOMParser().parseFromString(`
                        <div class="detail xmas-modal">
                            <input type="hidden" name="id" value="${id}">
                            <span class="title">상품 설명</span>
                            <div class="main">
                                <div class="img-box">
                                    <img src="../imgs/${imgUrl}" alt="">
                                </div>
                                <div class="info-box">
                                    <span class="info-text">상품 상세 정보</span>
                                    <table class="info-table">
                                        <tr>
                                            <th class="subject">이름</th>
                                            <td class="text name">${name}</td>
                                        </tr>
                                        <tr>
                                            <th class="subject">분류</th>
                                            <td class="text">${category}</td>
                                        </tr>
                                        <tr>
                                            <th class="subject">가격</th>
                                            <td class="text price">${price}</td>
                                        </tr>
                                        <tr>
                                            <th class="subject">설명</th>
                                            <td class="text">${description}</td>
                                        </tr>
                                    </table>
                                    <div class="quantity-box">
                                        <button type="button" class="minus"><i class="fa-solid fa-minus"></i></button>
                                        <label>
                                            <input type="number" name="quantity" value="1" max="99999" min="1">
                                        </label>
                                        <button type="button" class="plus"><i class="fa-solid fa-plus"></i></button>
                                    </div>        
                                </div>
                            </div>
                            <div class="button-container">
                            </div>
                        </div>
                    `,'text/html').querySelector('.xmas-modal');

                    const quantity = detailModal.querySelector('input[name="quantity"]');

                    quantity.oninput = () => {
                        if (parseInt(quantity.value) <= 1) {
                            quantity.value = 1;
                        }

                        if (parseInt(quantity.value) >= 99999) {
                            quantity.value = 99999;
                        }
                    }

                    detailModal.querySelector('button.minus').onclick = () => {
                        if (parseInt(quantity.value) <= 1) {
                            quantity.value = 1;
                        } else {
                            quantity.value = parseInt(quantity.value) - 1;
                        }
                    }

                    detailModal.querySelector('button.plus').onclick = () => {
                        if (parseInt(quantity.value) >= 99999) {
                            quantity.value = 99999;
                        } else {
                            quantity.value = parseInt(quantity.value) + 1;
                        }
                    }

                    const buttonContainer = detailModal.querySelector('.button-container');

                    for (const buttonOption of buttonOptions) {
                        const button = this.createButton(buttonOption['title'], buttonOption['onclick']);
                        buttonContainer.append(button);
                    }

                    this.cover.append(detailModal);

                } else {
                    alert("[에러] 페이지 오류(404, 500)");
                }
            }
        }
        request.open("GET", `/items/${id}`, true);
        request.send();
    }

    show() {
        this.cover.classList.add('-visible');
    }

    remove() {
        this.cover.remove();
    }
}