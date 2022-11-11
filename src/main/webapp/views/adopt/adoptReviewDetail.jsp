<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="tk.newsoulmate.domain.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%
<%
    Board b = (Board) request.getAttribute("b");
%>
<html>
<head>
    <title>입양후기 상세보기</title>
    <link rel="stylesheet" href="CSS/common/adoptDetail.css" type="text/css">
    <script src="JS/common/adoptDetail.js"></script>
</head>
<body>
<%@ include file="../template/menubar.jsp" %>
<div class="outer">
    <table id="detail-area">
        <tr id="title">
            <th>제목</th>
            <td><%=b.getBoardTitle() %></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><%=b.getMemberName()%></td>
            <th>작성일</th>
            <td><%=b.getCreateDate()%></td>
            <th>입양날짜</th>
            <td><%=b.getIssueDate()%></td>
            <th>조회수</th>
            <td><%=b.getReadCount()%></td>
        </tr>
        <hr>
        <tr id="content">
            <td>
                <p><%=b.getBoardContent() %></p>
            </td>
        </tr>
    </table>
    <table id="reply-area">
        <thead>
<%--            댓글리스트 출력--%>
        </thead>
        <tbody>
        <tr>
            <td><textarea placeholder="댓글을 작성해주세요"></textarea></td>
            <td> <button onclick="insertReply();">댓글등록</button></td>
        </tr>
        <tr>
            <td><textarea readonly>로그인후 댓글작성이 가능합니다.</textarea></td>
            <td><button disabled>댓글등록</button></td>
        </tr>
        </tbody>
    </table>



</div>
</body>
</html>
