<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>회원정보 수정</title>
  <link href="<%=request.getContextPath()%>/css/mypage/mypageHeader.css" rel="stylesheet">
  <c:set var="context" value="${pageContext.request.contextPath}"/>
</head>
<body>

<div class="headcontainer">
  <div id="header_box">
    <div>
      <a href="${context}"><img src="${context}/image/logo.png" width="300px"></a>
    </div>
    <div>
      <p></p>
    </div>
    <div id="user">
      <a href="#">로그아웃</a>
    </div>
  </div>

  <ul class="menu">
    <li><a href="#">회원정보 수정</a></li>
    <li><a href="#">작성게시글 확인</a></li>
    <li><a href="#">보호소관계자<br>등록신청</a></li>
    <li><a href="#">회원탈퇴</a></li>
  </ul>

  <hr>
  <div class="right_text">
    <h1>회원정보 수정</h1> <!-- 각 카테고리에 따라 다르게 수정 / 임의로 넣음-->
  </div>
  <hr>
</div>
</body>
</html>
