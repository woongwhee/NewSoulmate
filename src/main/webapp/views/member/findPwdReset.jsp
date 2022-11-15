--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-14
  Time: 오후 7:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>비밀번호 재설정</title>
</head>
<body>

<%@include file="/views/template/menubar.jsp"%>

<div class="pwReset-wrap" align="center">
    <div>
        <h1>비밀번호 재설정</h1>
    </div>
    <div>
        <div class="input-pw">
            <input type="text" placeholder="새로운 비밀번호 입력">
        </div>
        <div class="input-pwre">
            <input type="text" placeholder="비밀번호 재입력">
        </div>
        <div>
            <button onclick="changePwdBtn">비밀번호 변경</button>
        </div>

    </div>

</div>

<%@include file="/views/template/footer.jsp"%>



</body>
</html>
