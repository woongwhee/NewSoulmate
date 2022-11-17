<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="loginUser" value="${sessionScope.loginUser}" scope="session"/>


<div class="headcontainer">
    <div id="header_box">
        <div>
            <p> </p>
        </div>
        <div class="navbar_logo">
            <a href="${context}"><img src="${context}/image/logo.png"></a>
        </div>

        <div id="user">
            <ul>
                <c:if test="${empty loginUser}" var="result1">
                    <li><a href="${context}/memberSignupTerm">회원가입</a></li>
                    <li><a href="${context}/loginpage">로그인</a></li>

                </c:if>
                <c:if test="${!empty loginUser}" var="result2">
                    <p><b>낭낭</b>님 환영합니다!
                        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor"
                             class="bi bi-emoji-smile" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                            <path
                                    d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z" />
                        </svg>
                    </p>
                    <li><a href="${context}/mypage">마이페이지</a></li>
                    <li><a href="#">로그아웃</a></li>
<%--                    <li><a href="${context}/mypage">마이페이지</a></li>--%>
                </c:if>
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
                    <a href="${context}/shelter/list">보호소</a>
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
                <button class="dropdown-btn"><a href="${context}/inquire">고객센터</a></button>
<%--                <div class="dropdown-submenu">--%>
<%--                    <a href="#">자주묻는 질문</a>--%>
<%--                    <a href="#">문의하기</a>--%>
<%--                </div>--%>
            </div>
        </div>
    </nav>
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
</div>

