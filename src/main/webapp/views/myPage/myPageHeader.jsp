<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
      <a href="#" id="logout">로그아웃</a>
    </div>
  </div>

  <ul class="menu">
    <li>
      <div class="dropdown">
        <button class="dropdown-btn"><a href="#">회원정보</a></button>
        <div class="dropdown-submenu">
          <a href="#">회원정보 수정</a>
          <a href="#">비밀번호 변경</a>
        </div>
      </div>
    </li>

    <li><a href="${context}/myPageBoardList.bo">작성게시글 확인</a></li>
    <li><a href="${context}/myPageShelter">보호소관계자<br>등록신청</a></li>
    <li><a href="#">회원탈퇴</a></li>
  </ul>

  <div id="right_text_fiex">

    <div class="right_text">
      <h1>회원정보 수정</h1>
      <!--수정필요 해당 게시글명 가져오기-->
    </div>
  </div>
</div>

<script>



</script>



</body>
</html>
