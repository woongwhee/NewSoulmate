<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-11
  Time: 오전 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>

<%
    Board b = (Board)request.getAttribute("b");
    // 게시글번호, 카테고리명, 제목, 내용, 작성자아이디, 작성일

    Attachment at = (Attachment)request.getAttribute("at");
    // 파일번호, 원본명, 수정명, 저장경로
%>
<html>
<head>
    <title>문의내역 상세보기</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>

    <div class="outer">
        <br>
        <h2 style="text-align:center;">1:1문의</h2>
        <br>
        <hr>
        <br>

        <table id="detail-area" align="center" style="border: 1px solid black;">

            <tr>
                <th width="100">카테고리</th>
                <td width="70"><%= b.getCategoryName() %></td>
                <th width="100">제목</th>
                <td width="350"><%= b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= b.getMemberName() %></td>
                <th>작성일</th>
                <td><%= b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>문의내용</th>
                <td colspan="3">
                    <p style="height:200px;"><%= b.getBoardContent() %></p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <% if(at == null) { %>
                    <!-- 첨부파일이 없는경우 -->
                    첨부파일된 파일 없음.
                    <% } else { %>
                    <!-- 첨부파일이 있는경우 -->
                    <a href="<%=request.getContextPath() %>/<%=at.getFilePath() + at.getChangeName() %>"
                       download=<%= at.getOriginName() %>>
                        <%= at.getOriginName() %>
                    </a>
                    <% } %>
                </td>
            </tr>
            <tr>
                <th>답변</th>
                <td><%= b.getResultStatus()%></td>
            </tr>
        </table>

        <br>

        <div align="center">
            <a href="<%=request.getContextPath() %>/inquire?currentPage=1" class="btn btn-secondary btn-sm">목록</a>
<%--            <% if(loginUser != null && loginUser.getMemberNo().equals(b.getMemberNo())) { %>--%>
            <!-- 현재 로그인한 사용자가 해당 글을 작성한 작성자일 경우에만 보여진다. -->
            <a href="<%=request.getContextPath() %>/inquireUpdateForm.bo?bno=<%=b.getBoardNo() %>" class="btn btn-secondary btn-sm">수정</a>
            <a href="<%=request.getContextPath() %>/inquireDelete.bo?bno=<%=b.getBoardNo() %>" class="btn btn-danger btn-sm">삭제</a>
<%--            <% } %>--%>

        </div>

    </div>

    <%@include file="/views/template/footer.jsp"%>
</body>
</html>
