// 메일 인증번호
let checkMail = 0;
let mailCode;
let intervalId;

function sendMail() {
    const memberMail2 = $("#memberMail").val();
    $.ajax({
        url:context+"/sendMail.do",
        data: { memberMail: memberMail2 },
        type: "get",
        success: function(data) {
            if (data != null) {
                mailCode = "notNull";
                $("#auth").css("display","flex");
                authTime();
            }
        }
    });
}

// 입력시간 출력
function authTime() {
    $("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
    intervalId = window.setInterval(function() {
        timeCount();
    }, 1000);
}

function timeCount() {

    const min = Number($("#min").text());

    const sec = $("#sec").text();
    if (sec == "00") {
        if (min == 0) {
            mailCode = null;
            clearInterval(intervalId);
        } else {
            $("#min").text(min - 1);
            $("#sec").text(59);

        }
    } else {
        const Sec2 = Number(sec) - 1;
        if (Sec2 < 10) {
            $("#sec").text("0" + Sec2);
        } else {
            $("#sec").text(Sec2);
        }
    }
}

function authenticationMail() {
    const inputValue = $("#authCode").val();
    if (mailCode != null) {
        $.ajax({
            url: context+'/checkAuth',
            type: 'get',
            data: {authCode: inputValue},
            success: (result)=> {
                if (result == 1) {
                    $("#authMsg").text("인증에 성공하셨습니다.");
                    clearInterval(intervalId);
                    $("#timeZone").hide();
                    checkMail = 1;
                } else if (result == 0) {
                    $("#authMsg").text("인증번호가 일치하지 않습니다.");
                    checkMail = 0;
                }
            },
            error: function () {
                alert("서버요청실패");
                checkMail = 0;
            }

        });
    }else{
        $("#authMsg").text("인증시간이 만료되었습니다.");
        checkMail = 0;
    }
    console.log(inputValue);
    console.log(mailCode);
};