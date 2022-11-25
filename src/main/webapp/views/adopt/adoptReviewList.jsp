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
    <link href="<%=request.getContextPath()%>/css/board/adoptReviewList.css" rel="stylesheet">
</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>
    <div id="content">
        <div id="adopt-review-head">
            <p>입양후기</p>
            <button>작성하기</button>
        </div>
        <div id="adopt-review-area">
            <div class="adopt-thum">
                <img class="adopt-thumnail" src="" onclick="location.href='#'">
                <p>입양후기제목</p>
            </div>
        </div>

        <div class="adopt-paging-area">
            <% if(currentPage != 1) { %>
            <button onclick="doPageClick(<%=currentPage-1%>)" class="btn btn-secondary btn-sm">&lt;</button>

            <% } %>

            <% for(int i=startPage; i<=endPage; i++) { %>
            <% if(i != currentPage) {%>
            <button onclick="doPageClick(<%=i%>)" class="btn btn-secondary btn-sm"><%=i %></button>
            <% } else { %>
            <button disabled><%=i %></button>
            <% } %>
            <% } %>

            <% if(currentPage != maxPage) { %>
            <button onclick="doPageClick(<%=currentPage+1%>)" class="btn btn-secondary btn-sm">&gt;</button>

            <% } %>
        </div>
    </div>
    <script>
        function doPageClick(currentPage){
            location.href = "<%=request.getContextPath()%>/adoptRevList.bo?currentPage="+currentPage;
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
<%@ include file="/views/template/footer.jsp"%>
</body>
</html>
