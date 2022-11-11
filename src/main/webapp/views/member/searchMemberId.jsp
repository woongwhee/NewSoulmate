<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-10
  Time: 오전 4:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>아이디 찾기</title>

</head>
<body>

<%@include file="/views/template/menubar.jsp"%>

<div class="content-wrap" align="center">

  <div class="title-wrap">
    <h2>아이디 찾기</h2>
  </div>

  <div class="content-box">
    <div>

      <div class="search-content">
        <input type="text" name="searchName" id="searchName" placeholder="이름">
      </div>

      <div class="search-content">
        <input type="text" name="searchMail" id="searchMail1" placeholder="이메일">
        <button onclick="sendMail();" class="authBtn">인증메일전송</button>
        </div>
      </div>

      <div class="search-content">
        <div id="auth">
          <input type="text" id="authCode" placeholder="인증번호" class="input-form">
          <button id="authBtn">인증하기</button>
        </div>
      </div>

      <div class="span-box">
        <span id="timeZone"></span>
        <span id="authMsg"></span>
      </div>

      <div class="search-content">
        <button type="submit" class="searchIdBtn">아이디 찾기</button>
      </div>

    </div>
  </div>

</div>



<%@include file="/views/template/footer.jsp"%>
<%--<script src="/JS/searchMemberId.js"></script>--%>







</body>
</html>