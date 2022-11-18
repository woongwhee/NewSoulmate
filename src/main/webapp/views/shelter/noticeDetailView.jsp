<%@ page import="tk.newsoulmate.domain.vo.Notice" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/14
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>환승주인 - 상세보기</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
<%
    Notice n=(Notice)request.getAttribute("n");
%>

</head>
<body>
<header><%@include file="/views/template/menubar.jsp"%></header>
<main>
    <img src="${n.filename}" alt="">
    <table>
        <tr>
            <td>공고번호</td>
            <td>${n.desertionNo}</td>
            <td>보호소전화번호</td>
            <td>${n.officetel}</td>s
        </tr>
        <tr>
            <td>공고시작일</td>
            <td>${n.noticeSdt}</td>
            <td>공고종료일</td>
            <td>${n.noticeEdt}</td>
        </tr>
        <tr>
            <td>색</td>
            <td>${n.colorCd}</td>
            <td>나이</td>
            <td>${n.age}</td>
        </tr>
        <tr>
            <td>성별</td>
            <td>${n.sexCd}</td>
            <td>중성화여부</td>
            <td>${n.sexCd}</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>

    </table>
    <a href="${context}/adoptApply?dno=${n.desertionNo}" class="btn btn-sm btn-secondary">입양신청</a>


</main>
<footer><%@include file="/views/template/footer.jsp"%></footer>
</body>
</html>
