const adcbtn = document.getElementById('admin-call-btn');

adcbtn.onclick = () => {
    const mainModal = new ModalObj();

    mainModal.createSimpleForm('비밀번호 입력',[{
        title : '확인',
        onclick : () => {
            const request = new XMLHttpRequest();
            const pw = document.getElementById('pw').value;
            request.onreadystatechange = () => {
                if (request.readyState == 4) {
                    if (request.status == 200) {
                        if (request.responseText === "false") {
                            mainModal.remove();
                            const cancelModal = new ModalObj();
                            cancelModal.createSimpleButton('알림', '잘못된 비밀번호 입니다.');
                            cancelModal.show();
                        }
                    } else {
                        alert("[에러] 페이지 오류(404, 500)");
                    }
                }
            }
            request.open("POST", "/", true);
            request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            request.send(`pw=${pw}`);
        }
    }, {
        title : '취소',
        onclick : () => mainModal.remove()
    }]);

    mainModal.show();
}