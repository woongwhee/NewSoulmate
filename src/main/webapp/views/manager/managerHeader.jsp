<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/image/titlelogo.png" rel="shortcut icon" type="image/x-icon">
    <link href="<%=request.getContextPath()%>/css/manager/managerHeader.css" rel="stylesheet">
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
        </div>
    </div>

    <ul class="menu">
        <li>
            <div class="dropdown">
                <button class="dropdown-btn"><a href="${context}/manageMemberPage">회원관리</a></button>
                <div class="dropdown-submenu">
                    <a href="${context}/manageMemberPage">회원리스트</a>
                    <a href="${context}/manageGrade">보호소 관계자 신청</a>
                </div>
            </div>
        </li>
        <li><a href="${context}/adoptApplyList">입양관리</a></li>
        <li>
            <div class="dropdown">
                <button class="dropdown-btn"><a href="${context}/manageSupportAllHistoryPage">후원관리</a></button>
                <div class="dropdown-submenu">
                    <a href="${context}/manageSupportAllHistoryPage">전체 후원내역</a>
                    <a href="${context}/manageSupportPage">보호소별 출금신청내역</a>
                </div>
            </div>
        </li>


        <li><a href="${context}/reportList">신고접수</a></li>
    </ul>

    <div id="right_text_fiex">
        <div class="right_text" id="right_text">
            <h1><span class="list-text">회원리스트</span></h1>
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
