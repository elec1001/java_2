let selectedFile=null;

$(document).ready(()=>{
    checkToken();

    setupAjax();//헤더에 토큰을 보냄

    getUserInfo().then((userInfo)=>{
        console.log('userInfo :: ',userInfo);
        $('#hiddenUserName').val(userInfo.hiddenUserName);
        $('#hiddenUserId').val(userInfo.hiddenUserId);
        $('#user_id')
    }).catch((error)=>{
        console.error("Error get user Info: ",error)
    });

    $('#file').on('change',(event)=>{
        const file=event.target.files[0];//첫번째 파일만 선택

    })

    $('#submitBtn').on('click',(event)=>{
        event.preventDefault();

        let formData=new FormData($('#writeForm')[0]);

       // for (let [key, value] of formData.entries()) {
       //     if (value instanceof File) {
       //         console.log('Key:', key);
       //         console.log('Name:', value.name);
       //         console.log('Size:', value.size);
       //         console.log('Type:', value.type);
       //     } else {
       //         console.log(key + ': ' + value);
       //     }
       // }

        $.ajax({
            type:'POST',
            url:'/api/board',
            data: formData,
            contentType: false,
            processData: false,
            success:(response)=>{
                alert('게시글이 성공적으로 등록되었습니다.');
                window.location.href="/"
            },
            error:(error)=>{
                console.log('오류발생 :',error);
                alert('게시글 등록 중 오류가 발생했습니다')
            }
        });
    });
});
//파일 목록 업데이트 함수(파일 하나만)
let updateFileList=()=>{
    $('#fileList').empty();//기좀 목록 지우기

    if(selectedFile) {
        $('#fileList').append(`
                    <li>
                        ${selectedFile.name} <button type="button" class="remove-btn">X</button>
                    </li>
                `);

        // X 버튼 클릭 시 파일 제거
        $('.remove-btn').on('click', function () {
            selectedFile = null; // 선택된 파일 제거
            $('#file').val(''); // 파일 input 초기화
            updateFileList(); // 파일 목록 갱신
        });
    }
}