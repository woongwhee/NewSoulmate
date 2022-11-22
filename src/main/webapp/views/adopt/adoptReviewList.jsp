<%@ page import="tk.newsoulmate.domain.vo.Board" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
    PageInfo pi = (PageInfo) request.getAttribute("pi");
    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
%>
<html>
<head>
    <title>Title</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
    <header><%@include file="/views/template/menubar.jsp"%></header>
    <main>
        <div class="outer">
    <br>
    <h2 style="text-align: center;">입양후기게시판</h2>
    <br>
    <div align="center">
        <a href="${context}/adoptReEnroll" class="btn btn-secondary">글작성</a><br>
    </div>

    <table align="center" class="list-area">
        <thead>
        <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <% if(list.isEmpty()){ %>
        <tr id="tableEmpty">
            <td colspan="6">조회된 게시글이 없습니다</td>
        </tr>
        <% } else { %>
        <% for (Board b : list) { %>
        <tr>
            <td><%=b.getBoardNo() %></td>
            <td><%=b.getBoardTitle() %></td>
            <td><%=b.getMemberNo() %></td>
            <td><%=b.getReadCount() %></td>
            <td><%=b.getCreateDate() %></td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>



    <br><br>

    <div align="center" class="paging-area">

        <% if(currentPage != 1) { %>
        <button onclick="doPageClick(<%= currentPage-1 %>)">&lt;</button>
        <% } %>

        <% for(int i = startPage; i<=endPage; i++) { %>
        <% if(i != currentPage) { %>
        <button onclick="doPageClick(<%= i %>)"><%= i %></button>
        <% } else { %>
        <button disabled><%= i %></button>
        <% } %>
        <% } %>

        <% if(currentPage != maxPage) { %>
        <button onclick="doPageClick(<%= currentPage+1 %>)">&gt;</button>
        <% } %>

    </div>
    
    <script>
        function doPageClick(currentPage) {
            location.href = "${context}/adoptReList.bo?currentPage="+currentPage;
        }
        $(function() {
            $(".list-area>tbody>tr").click(function(){
                if($(this).text()!=$("#tableEmpty").text()){

                let bno = $(this).children().eq(0).text();
                location.href = '${context}/adoptRevDetail?bno='+bno;
                }
            });
        })
    </script>
    </div>
    </main>
    <footer>    <%@ include file="/views/template/footer.jsp"%></footer>
</body>
</html>
