<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-18
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.Board" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page import="tk.newsoulmate.domain.vo.BoardType" %>
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
    <title>작성게시글 확인</title>
    <link href="${context}/css/mypage/mypageBoardList.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>

</head>
<body>
<%--<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>--%>

    <div id="mPageList">
      <br>
      <br>

      <table align="center" class="list-area">
        <thead>
          <tr style="text-align: center">
          <th width="70">NO</th>
          <th width="300">글제목</th>
          <th width="100">작성일시</th>
          <th width="100">조회수</th>
          <th width="100" class="bHidden">게시글분류</th>

        </tr>
        </thead>
        <tbody style="text-align: center">
        <% if(list==null || list.isEmpty()){ %>
        <tr id="tableEmpty">
          <td colspan="4" align="center">조회된 리스트가 없습니다</td>
        </tr>
        <% } else { %>
        <% for(Board b : list) { %>
        <tr>
          <td><%= b.getBoardNo() %></td>
          <td><%= b.getBoardTitle() %></td>
          <td><%= b.getCreateDate() %></td>
          <td><%= b.getReadCount() %></td>
          <td class="bHidden"><%=b.getBoardName()%></td>
<%--          <input type="hidden" class="boardType" value="<%=b.getBoardType().boardName%>"/> 인풋요소 사용할수 없다고함... 이것이 에러원인이였음--%>
        </tr>
        <% } %>
        <% } %>
        </tbody>

      </table>
      <script>
        $(function(){
          $(".list-area>tbody>tr").click(function(){
            // 클릭시 해당 공지사항의 번호를 넘겨야함.
            // 해당 tr요소의 자손중에서 첫번째 td의 영역의 내용이 필요.
            if($(this).text()!=$("#tableEmpty").text()) { // 조회된 리스트가 없을경우 클릭방지
                let bno = $(this).children().eq(0).text(); // 0 => b.getBoardNo()
                let typeName = $(this).children().eq(4).text(); // 4 => b.getBoardType.boardName
                <%--location.href = '${context}/' + typeName + 'Detail.bo?bno=' + bno;--%>
                switch (typeName){
                    case "문의": location.href = '${context}/inquireDetail.bo?bno='+bno; break;
                    case "입양후기": location.href = '${context}/adoptRevDetail.bo?bno='+bno; break;
                    case "봉사후기": location.href = '${context}/......Detail.bo?bno='+bno; break;
                }

            }
          });

          $('.bHidden').attr('style', "display:none;");

        });
      </script>
      <br><br>

      <%-- 페이징바 처리--%>
      <div align="center" class="paging-area">

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
      <script>
        function doPageClick(currentPage){
          location.href = "<%=request.getContextPath()%>/myPageBoardList.bo?currentPage="+currentPage;
        }
      </script>

    </div>

</body>
</html>
