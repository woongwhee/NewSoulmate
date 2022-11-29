<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-14
  Time: 오후 7:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <title>비밀번호 재설정</title>
    <link href="<%=request.getContextPath()%>/css/member/pwdReset.css" rel="stylesheet">
    <%@include file="/views/template/styleTemplate.jsp"%>
</head>
<body>

<%@include file="/views/template/menubar.jsp"%>
<div class="content-wrap" align="center">
    <div class="title-wrap">
        <p>비밀번호 재설정</p>
    </div>
    <div>
        <div class="input-member-id">
            <input type="text" id="memberId" placeholder="아이디" required>
        </div>
        <div class="input-pw">
            <input type="password" name="password" id="password" placeholder="새로운 비밀번호 입력" required>
            <div><span id="pwChkMsg"></span></div>
        </div>
        <div class="input-pwre">
            <input type="password" name="passwordConfirm" id="passwordConfirm" placeholder="비밀번호 재입력" required>
            <div><span id="pwReChkMsg"></span></div>
        </div>
        <div class="reset-btn-box">
            <button onclick="changePassword()" id="resetBtn">비밀번호 변경</button>
        </div>
    </div>
</div>

<%@include file="/views/template/footer.jsp"%>

</body>
</html>

<script>
    function changePassword() {
        let memberId = $("#memberId").val();
        let password = $("#password").val();
        let passwordConfirm = $("#passwordConfirm").val();
        $.ajax({
            url: '<%=request.getContextPath()%>/pwdReset.do',
            type: 'post',
            contentType: "application/json; charset=utf-8",
            // JSON.stringify() : JavaScript 값이나 객체를 JSON 문자열로 변환
            data: JSON.stringify({
                'memberId': memberId,
                'password': password,
                'passwordConfirm': passwordConfirm
            }),
            success: function(data) {
                if (data == 1) {
                    alert("패스워드 변경에 성공했습니다!");
                } else {
                    alert("패스워드 변경에 실패했습니다!");
                }
                $(location).attr("href","${context}/loginpage");
            }
        });
    }



    //비밀번호 유효성 검사
    const password = document.querySelector("#password");
    const passwordConfirm = document.querySelector("#passwordConfirm");

    password.addEventListener("change", function() {

        const inputPw = password.value;
        const pwReg = /^[a-zA-Z0-9@$!%*#?&]{6,}$/;
        const pwChkMsg = document.querySelector("#pwChkMsg");

        const inputPwRe = passwordConfirm.value;
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


    // 비밀번호 일치 검사
    passwordConfirm.addEventListener("change", function() {
        const inputPw = password.value;
        const inputPwRe = passwordConfirm.value;
        const pwReChkMsg = document.querySelector("#pwReChkMsg");
        if (inputPw == inputPwRe) {
            pwReChkMsg.innerText = "비밀번호가 일치합니다."
            checkPwdRe = 1;
        } else {
            pwReChkMsg.innerText = "비밀번호가 일치하지않습니다."
            checkPwdRe = 0;
        }
    });









</script>