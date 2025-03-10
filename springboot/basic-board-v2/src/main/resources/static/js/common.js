let handelTokenExpiration=()=> {
    $.ajax({
        type: 'POST',
        url: '/refresh-token',
        data: JSON.stringify(formData),//데이터를 JSON 형식으로 변환
        contentType: 'application/json; charset=utf-8',//전송 데이터의 타입
        dataType: 'json',//서버에서 받을 데이터의 타입
        xhrfields: {
            withCredentials: true //쿠키를 포함해서 요청
        },

        success: (response) => {
            alert('로그인이 성공했습니다.');
            console.log('new Access:: '+response);
            localStorage.setItem('accessToken', response.token);
            window.location.href = "/"
        },
        error: () => {
            alert('로그인이 필요합니다.다시 로그인 해주세요')
            window.location.href = "/member/login"
        }

    })
}


let checkToken=()=> {
    let token = localStorage.getItem('accecssToken');
    if (token == null || token.trim() === '') {
        window.location.href = "/member.login"
    }
}
let setupAjax=()=>{
    //모든 Ajax 요청에 JWT Access Token을 포함
    $.ajaxSetup({
        beforeSend:(xhr)=>{
            let token=localStorage.getItem('accessToken');
            if (token){
                xhr.setRequestHeader('Authrization','Bearer '+token)
            }
        }
    })
}

let getUserInfo=()=>{
    return new Promise((resolve,reject)=>{
        $.ajax({
            type: 'GET',
            url: '/user/info',
            success: (response) => {
                resolve(response);
            },
            error: (xhr) => {
                console.log('xhr'+xhr);
                if(xhr.status===401){
                   handelTokenExpiration();
                }else{
                    reject(xhr);
                }
            }

        })
    })
 }


