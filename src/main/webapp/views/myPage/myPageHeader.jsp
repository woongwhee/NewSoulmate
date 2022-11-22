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
      <a href="#">로그아웃</a>
    </div>
  </div>

  <ul class="menu">
    <li><a href="#">회원정보 수정</a></li>
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

    <%
      HttpSession ss=request.getSession();
      String errorMsg = (String)ss.getAttribute("errorMsg");
      if (errorMsg != null) {
        ss.removeAttribute("errorMsg");
    %>
    alert("<%=errorMsg%>")
    <%
      }
      errorMsg=(String)request.getAttribute("errorMsg");
      if (errorMsg != null) {
        request.removeAttribute("errorMsg");
    %>
    alert("<%=errorMsg%>")
    <%
      }
    %>
    <%
      String alertMsg = (String)request.getAttribute("alertMsg");
      if (alertMsg != null) {
        request.removeAttribute("alertMsg");
    %>
    alert("<%=alertMsg%>");
    <%
      }
      alertMsg=(String) ss.getAttribute("alertMsg");
      if (alertMsg != null) {
    %>
    alert("<%=alertMsg%>");
    <%
        ss.removeAttribute("alertMsg");
      }
    %>
</script>





</body>
</html>
