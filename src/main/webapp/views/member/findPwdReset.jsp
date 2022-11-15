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
</head>
<body>

<%@include file="/views/template/menubar.jsp"%>

<div class="pwReset-wrap" align="center">
    <div>
        <h1>비밀번호 재설정</h1>
    </div>
    <div>
        <div class="input-member-id">
            <input type="text" id="memberId" placeholder="아이디 입력">
        </div>
        <div class="input-pw">
            <input type="text" id="password" placeholder="새로운 비밀번호 입력">
        </div>
        <div class="input-pwre">
            <input type="text" id="passwordConfirm" placeholder="비밀번호 재입력">
        </div>
        <div>
            <button onclick="changePassword()">비밀번호 변경</button>
        </div>
    </div>
</div>

<%@include file="/views/template/footer.jsp"%>

</body>
</html>

<script>
    function changePassword() {
        let memberId = $("#memberId").val()
        let password = $("#password").val()
        let passwordConfirm = $("#passwordConfirm").val()
        // 입력값 있는지 검증(Required), 패스워드랑 확인 같은지 검증(Confirm) 필요

        $.ajax({
            url: '<%=request.getContextPath()%>/pwdReset.do',
            type: 'get',
            contentType: "application/json; charset=utf-8",
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
                location.href = "memberLoginForm.jsp";
            }
        });
    }
</script>
