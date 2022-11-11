let checkMail = 0;

let mailCode;
let intervalId;
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
            url: '<%= request.getContextPath()%>/checkAuth',
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

// 메일 인증번호


function sendMail() {
    const memberMail2 = $("#memberMail").val();
    $.ajax({
        url: "<%= request.getContextPath()%>/sendMail.do",
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

// number maxlength 지정

function maxLengthChk(pNum){
    if (pNum.value.length > pNum.maxLength){
        pNum.value = pNum.value.slice(0, pNum.maxLength);
    }
}

let checkId = 0;
let checkPwd = 0;
let checkPwdRe = 0;
let checkNickname = 0;

$(document).ready(function () {

    // 아이디 유효성검사 & 중복 검사 - 완료
    const memberId = document.querySelector("#memberId");
    const idReg = /^[a-zA-Z0-9]{6,}/;

    $('#checkid').click(function() {

        let memberIds = $('#memberId').val();
        if(idReg.test(memberIds)){
            $.ajax({
                url : '<%= request.getContextPath()%>/ajaxCheckId.do',
                type: 'get',
                data : { memberId: memberIds },
                dataType : 'json',
                success: function(result) {

                    if (result == 1) {
                        alert("이미 사용중인 아이디 입니다.");
                        checkId = 0;
                    } else if (result == 0) {
                        alert("사용가능한 아이디 입니다.");
                        checkId = 1;
                    }
                },
                error : function(){
                    alert("서버요청실패");
                    checkId = 0;
                }
            })
        }else {
            alert("아이디가 6자 이상이어야 합니다.")
            checkId = 0;
        }
    });




    //비밀번호 유효성 검사 - 완료
    const memberPw = document.querySelector("#memberPw");
    const memberPwRe = document.querySelector("#memberPwRe");

    memberPw.addEventListener("change", function() {

        const inputPw = memberPw.value;
        const pwReg = /^[a-zA-Z0-9]{8,}$/;
        const pwChkMsg = document.querySelector("#pwChkMsg");

        const inputPwRe = memberPwRe.value;
        const pwReChkMsg = document.querySelector("#pwReChkMsg");

        if (pwReg.test(inputPw)) {
            pwChkMsg.innerText = "사용 가능한 비밀번호 입니다."
            checkPwd = 1;
        } else {
            pwChkMsg.innerText = "사용 불가능한 비밀번호 입니다."
            checkPwd = 0;
        }
        if(inputPwRe != ""){
            if(inputPw == inputPwRe){
                pwReChkMsg.innerText = "비밀번호가 일치합니다."
                checkPwdRe = 1;
            }else{
                pwReChkMsg.innerText = "비밀번호가 일치하지않습니다."
                checkPwdRe = 0;
            }
        }else{

        }
    });


    // 비밀번호 일치 검사 - 완료
    memberPwRe.addEventListener("change", function() {
        const inputPw = memberPw.value;
        const inputPwRe = memberPwRe.value;
        const pwReChkMsg = document.querySelector("#pwReChkMsg");
        if (inputPw == inputPwRe) {
            pwReChkMsg.innerText = "비밀번호가 일치합니다."
            checkPwdRe = 1;
        } else {
            pwReChkMsg.innerText = "비밀번호가 일치하지않습니다."
            checkPwdRe = 0;
        }
    });


    // 닉네임 중복체크 - 완료

    const nickName = document.querySelector("#nickName");
    const nickReg = /^[a-zA-Zㄱ-힣]{3,}/;

    $('#checkNickname').click(function() {

        let nickNames = $('#nickName').val();

        if(nickReg.test(nickNames)){
            $.ajax({
                url : '<%= request.getContextPath()%>/ajaxCheckNickname.do',
                type: 'get',
                data : { nickName: nickNames },
                dataType : 'json',
                success: function(result) {

                    if (result == 1) {
                        alert("이미 사용중인 닉네임 입니다.");
                        checkNickname = 0;
                    } else if (result == 0) {
                        alert("사용가능한 닉네임 입니다.");
                        checkNickname = 1;
                    }
                },
                error : function(){
                    alert("서버요청실패");
                    checkNickname = 0;
                }
            })
        }else {
            alert("닉네임은 3자 이상이어야 합니다.")
            checkNickname = 0;
        }
    });




});

// 필수입력사항 모두 입력돼야 회원가입 할 수 있게
function signupCheck(){
    if (!(checkId == 1 && checkPwd == 1 && checkPwdRe == 1 && checkNickname == 1 && checkMail ==1)) {
        alert("필수 입력창을 모두 입력해주세요.")
        return false;
    }
};