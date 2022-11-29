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
    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/mypage/mypageDeleteMember.css" rel="stylesheet">
</head>
<body>
<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div class="information">
            <p>탈퇴시 되돌릴 수 없습니다</p>
            <form action="<%=request.getContextPath()%>/memberDelete" method="post" name="myPageDeleteMember">
                <table>
                    <tr>
                        <td><input type="password" name="memberPwd" placeholder="비밀번호 입력" required></td>
                    </tr>
                    <tr>
                        <th><button type="submit" class="deleteMemberBtn">탈퇴하기</button></th>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<script>
    $(function (){
        $(".list-text").text("회원탈퇴");
    })

</script>

</body>
</html>
