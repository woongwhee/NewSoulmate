<%@ page import="tk.newsoulmate.domain.vo.Member" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-19
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Member loginUser = (Member) session.getAttribute("loginUser"); %>
<html>
<head>
    <title>회원탈퇴</title>
    <link href="<%=request.getContextPath()%>/css/mypage/mypageInfoDetail.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp" %>
</head>
<body>

<header>
    <%@include file="/views/myPage/myPageHeader.jsp" %>
</header>

<div>
    <div>회원탈퇴</div>

    <form action="<%=request.getContextPath()%>/memberDelete" method="post" name="myPageDeleteMember">
        <table>
            <tr>
                <td>${loginUser.memberName}</td>
                <td>현재 비밀번호</td>
                <td><input type="password" name="memberPwd" required></td>
            </tr>
        </table>
        <br>
        <button type="submit" class="deleteMemberBtn">탈퇴하기</button>

    </form>


</div>

</body>
</html>
