<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<%=request.getContextPath()%>/css/manager/managerHeader.css" rel="stylesheet">
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
            <!--로그아웃 클릭시 로그아웃처리된 상태로 메인화면ㄱㄱ-->
        </div>
    </div>

    <ul class="menu">
        <li>
            <div class="dropdown">
                <button class="dropdown-btn"><a href="#">회원관리</a></button>
                <div class="dropdown-submenu">
                    <a href="#">회원리스트</a>
                    <a href="#">보호소 관계자 신청</a>
                </div>
            </div>
        </li>
        <li><a href="#">보호소 관리</a></li>
        <li><a href="#">입양관리</a></li>
        <li><a href="#">후원관리</a></li>
        <li><a href="#">신고접수</a></li>
    </ul>

    <div id="right_text_fiex">
        <div class="right_text">
            <h1>회원리스트</h1>
            <!--수정필요 해당 게시글명 가져오기-->
        </div>
    </div>
</div>

</body>
</html>
