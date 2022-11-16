<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="loginUser" value="${requestScope.loginUser}"/>
<div id="header_total">

    <div id="teams">
        <div><%=request.getContextPath()%>
            <p> </p>
        </div>
        <div class="navbar_logo">
            <a href="${context}"><img src="${context}/image/logo.png"></a>
        </div>

        <div id="user">
            <ul>
                <li><a href="${context}/memberSignupTerm">회원가입</a></li>
                <li><a href="${context}/loginpage">로그인</a></li>
            </ul>
        </div>
    </div>

    <nav class="navbar">
        <div class="navbar_menu">

            <div class="dropdown">
                <div class="saveanimal">
                    오늘 구조된 동물수: 20 &nbsp; <!-- 통계표시 숫자 가져오기 임의로 숫자 넣어둠-->
                    오늘 안락사된 동물수: 10
                </div>
            </div>

            <div class="dropdown">
                <button class="dropdown-btn"><a href="">유기동물</a></button>
                <div class="dropdown-submenu">
                    <a href="#">동물목록</a>
                    <a href="<%=request.getContextPath()%>/shelterList">보호소</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropdown-btn"><a href="#">입양</a></button>
                <div class="dropdown-submenu">
                    <a href="${context}/adoptApply">입양신청</a>
                    <a href="#">입양후기</a>
                    <a href="#">입양절차</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropdown-btn"><a href="#">봉사</a></button>
                <div class="dropdown-submenu">
                    <a href="#">봉사신청</a>
                    <a href="#">봉사후기</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropdown-btn"><a href="#">후원</a></button>
                <div class="dropdown-submenu">
                    <a href="#">후원하기</a>
                    <a href="#">후원내역</a>
                </div>
            </div>

            <div class="dropdown">
                <button class="dropdown-btn"><a href="${context}/Inquire">고객센터</a></button>
                <div class="dropdown-submenu">
                    <a href="#">자주묻는 질문</a>
                    <a href="#">문의하기</a>
                </div>
            </div>
        </div>
    </nav>
</div>
</div>

