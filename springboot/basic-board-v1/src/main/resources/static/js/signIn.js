$(document).ready(() => {

    $('#signin').click(() => {
        let userId = $('#user_id').val();
        let password = $('#password').val();

        console.log('user id ::', userId);
        console.log('user pw ::', password);

        let formdata = {
            username: userId,
            password: password
        }

        $.ajax({
            type: 'POST',
            url: '/login', //서버의 엔드포인트 URL
            //data: JSON.stringify(formdata), //데이터를 JSON 형식으로 변환
            //contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            data: $.param(formData), // 데이터를 URL 인코딩된 형식으로 변환
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            dataType: 'json', //서버에서 받을 데이터의 타입
            success: (response) => {
                console.log(response);
                alert('회원가입이 성공했습니다!\n로그인해주세요.');
            },
            error: (error) => {
             console.log("오류발생: ",error)  ;
                alert("로그인 중 오류가 발생했습니다.");

            }
        })
    });
});