<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="<%=request.getContextPath()%>/css/shelterManager/shelterHeader.css" rel="stylesheet">
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
      <a href="${context}/logout" id="logout">로그아웃</a>
      <!--로그아웃 클릭시 로그아웃처리된 상태로 메인화면ㄱㄱ-->
    </div>
  </div>

  <ul class="menu">
    <li><a href="${context}/ShelterMessage">메세지함</a></li>
    <li><a href="${context}/UpdateShelterInfo">보호소 정보수정</a></li>
    <li><a href=${context}/shelterSupport>받은 후원함</a></li>
  </ul>

  <div id="right_text_fiex">
    <div class="right_text">
      <h1><span class="list-text">메세지함</span></h1>
      <!--수정필요 해당 게시글명 가져오기-->
    </div>
  </div>
</div>
<script>

</script>



</body>
</html>
