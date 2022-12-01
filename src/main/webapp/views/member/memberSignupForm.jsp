<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-09
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <link href="<%=request.getContextPath()%>/css/member/memberSignupForm.css" rel="stylesheet">

    <%@include file="/views/template/styleTemplate.jsp" %>
</head>
<body>
<%@include file="/views/template/menubar.jsp" %>
<div class="content-wrap" align="center">
    <div class="title-wrap">
        <p>회원가입</p>
    </div>
    <div class="userInfo-wrap">
        <form action="<%=request.getContextPath()%>/signupForm.do" id="myForm" method="get">
            <div class="input-id-wrap">
                <label for="memberId">아이디 <span id="ajaxCheckId"></span></label>
                <br>
                <input type="text" name="memberId" id="memberId" placeholder="*아이디">
                <button type="button" id="checkid">중복확인</button>
            </div>

            <div class="input-pw-wrap">
                <label for="memberPw">비밀번호</label>
                <br>
                <input type="password" name="memberPwd" id="memberPw" placeholder="*비밀번호">
                <span id="pwChkMsg"></span>
            </div>

            <div class="input-pwre-wrap">
                <label for="memberPw">비밀번호 확인</label>
                <br>
                <input type="password" name="memberPwRe" id="memberPwRe" placeholder="*비밀번호 확인">
                <span id="pwReChkMsg"></span>
            </div>

            <div class="input-name-wrap">
                <label for="memberName">이름</label>
                <br>
                <input type="text" name="memberName" id="memberName" placeholder="*이름">
            </div>

            <div class="input-nickname-wrap">
                <label for="nickname">닉네임</label>
                <br>
                <input type="text" name="nickName" id="nickName" placeholder="*닉네임">
                <button type="button" id="checkNickname" >중복확인</button>
            </div>

            <div class="phone-wrap">
                <label for="memberPhone">전화번호</label>
                <br>
                <select name="memberPhone" id="memberPhone">
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="019">019</option>
                </select>
                <input type="number" name="Phone" id="Phone" placeholder="-빼고 입력" maxlength="8"
                       oninput="maxLengthChk(this)" required>
            </div>

            <div class="email-wrap">
                <div>
                    <label for="memberMail">이메일</label>
                    <br>
                    <input type="text" name="memberMail" id="memberMail" placeholder="*이메일">
                    <button type="button" id="emailCheck" onclick="sendMail();" disabled>인증번호 발송</button>
                    <div id="auth">
                        <div id="certified" style="display: none">
                            <input type="text" id="authCode" placeholder="인증번호">
                            <button type="button" class="authBtn" id="authBtn"
                                    onclick="authenticationMail()">인증하기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <span id="timeZone"></span>
            <span id="authMsg"></span>

            <div id="signup-btn-box">
                <button class="cancelBtn">취소</button>
                <button type="submit" onclick="return signupCheck();" id="signupBtn">회원가입</button>
            </div>
        </form>
    </div>
</div>

<%@include file="/views/template/footer.jsp" %>


<script>
    $(function () {
        $("#memberMail").on("keyup", function () {
            var flag2 = true;
            flag2 = $(this).val().length > 0 ? false : true;
            $("#emailCheck").attr("disabled", flag2);
        });
    });

    // 메일 인증번호 - 완료
    let checkMail = 0;
    let mailCode;
    let intervalId;
    let confirmNo;

    function sendMail() {
        const memberMail2 = $("#memberMail").val();
        $("#certified").show();
        $.ajax({
            url: "sendMail.do",
            data: {memberMail: memberMail2},
            type: "get",
            success: function (data) {
                if (data !=0) {
                    confirmNo = data;
                    mailCode = "notNull";
                    $("#auth").css("display", "flex");
                    authTime();
                }else{
                    alert('요청실패');
                }
            }
        });
    }

    // 입력시간 출력
    function authTime() {
        $("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
        intervalId = window.setInterval(function () {
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
                url: 'checkAuth',
                type: 'get',
                data: {
                    authCode: inputValue,
                    confirmNo: confirmNo
                },
                success: (result) => {
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
        } else {
            $("#authMsg").text("인증시간이 만료되었습니다.");
            checkMail = 0;
        }
        console.log(inputValue);
        console.log(mailCode);
    };


    // Phone - number maxlength 지정

    function maxLengthChk(pNum) {
        if (pNum.value.length > pNum.maxLength) {
            pNum.value = pNum.value.slice(0, pNum.maxLength);
        }
    }

    $("#memberPhone").change(function () {
        $("#memberPhone").val($("#Phone").val());
    });

    let checkId = 0;
    let checkPwd = 0;
    let checkPwdRe = 0;
    let checkNickname = 0;

    $(document).ready(function () {
        const memberId = document.querySelector("#memberId");
        const idReg = /^[a-zA-Z0-9]{5,20}/;

        $('#checkid').keyup(function(){
            checkid = 0;
        })

        $('#checkid').click(function () {

            let memberIds = $('#memberId').val();
            if (idReg.test(memberIds)) {
                $.ajax({
                    url: 'ajaxCheckId.do',
                    type: 'get',
                    data: {memberId: memberIds},
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            alert("이미 사용중인 아이디 입니다.");
                            checkId = 0;
                        } else if (result == 0) {
                            alert("사용가능한 아이디 입니다.");
                            checkId = 1;
                        }
                    },
                    error: function () {
                        alert("서버요청실패");
                        checkId = 0;
                    }
                })
            } else {
                alert("아이디가 6자 이상이어야 합니다.")
                checkId = 0;
            }
        });


        //비밀번호 유효성 검사 - 완료
        const memberPw = document.querySelector("#memberPw");
        const memberPwRe = document.querySelector("#memberPwRe");

        memberPw.addEventListener("change", function () {

            const inputPw = memberPw.value;
            const pwReg = /^[a-zA-Z0-9@$!%*#?&]{6,}$/;
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
            if (inputPwRe != "") {
                if (inputPw == inputPwRe) {
                    pwReChkMsg.innerText = "비밀번호가 일치합니다."
                    checkPwdRe = 1;
                } else {
                    pwReChkMsg.innerText = "비밀번호가 일치하지않습니다."
                    checkPwdRe = 0;
                }
            } else {

            }
        });

        // 비밀번호 일치 검사
        memberPwRe.addEventListener("change", function () {
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


        // 닉네임 중복체크
        const nickName = document.querySelector("#nickName");
        const nickReg = /^[a-zA-Z1-9ㄱ-힣]{3,15}/;

        $('#nickName').keyup(function(){
            checkNickname = 0;
        })

        $('#checkNickname').click( function() {
            let nickNames = $('#nickName').val();
            if (nickReg.test(nickNames)) {
                $.ajax({
                    url: 'ajaxCheckNickname.do',
                    type: 'get',
                    data: {nickName: nickNames},
                    dataType: 'json',
                    success: function (result) {
                        if (result == 1) {
                            alert("이미 사용중인 닉네임 입니다.");
                            checkNickname = 0;
                        } else if (result == 0) {
                            alert("사용가능한 닉네임 입니다.");
                            checkNickname = 1;
                        }
                    },
                    error: function () {
                        alert("서버요청실패");
                        checkNickname = 0;
                    }
                })
            } else {
                alert("닉네임은 3자 이상이어야 합니다.")
                checkNickname = 0;
            }
        });
    });

    // 필수입력사항 모두 입력돼야 회원가입 할 수 있게
    function signupCheck() {
        if (!(checkId == 1 && checkPwd == 1 && checkPwdRe == 1 && checkNickname == 1 && checkMail == 1)) {
            alert("필수 입력창을 모두 확인해주세요.")
            return false;
        }
    };


</script>

<%--<script src="${context}/jS/member/memberSignupForm.js"></script>--%>

</body>
</html>
