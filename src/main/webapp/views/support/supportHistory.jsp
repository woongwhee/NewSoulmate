<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>후원내역</title>
  <%@include file="/views/template/styleTemplate.jsp"%>
  <link href="<%=request.getContextPath()%>/css/support/supportHistroy.css" rel="stylesheet">
</head>
<body>
<header><%@include file="/views/template/menubar.jsp"%></header>
<main>
  <div id="container">
    <div class="content_main">
      <b>안녕</b>님의 후원 내역
    </div>
    <div class="content_date">
      <div id="supportDate">
        <span>조회기간</span>
        <span><input type="date"></span>
        <span>~</span>
        <span><input type="date"></span>
      </div>

      <div id="supportList">
        <table>
          <thead>
          <tr>
            <th>후원번호</th>
            <th>후원날짜</th>
            <th>후원방법</th>
            <th>후원금액</th>
          </tr>
          </thead>
          <tbody>
          <!--보여질 페이지 수 지정-->
          <c:forEach var="board" items="${}">
            <tr>
              <th>
                <!--후원번호 ${가져오기}-->
              </th>
              <td>
                <!--후원날짜 ${가져오기}-->
              </td>
              <td>
                <!--후원방법 ${가져오기}-->
              </td>
              <td>
                <!--후원금액 ${가져오기}-->
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

      <div id="supportTotal">
        총 금액 :
        <!--총 금액 가져오기-->
      </div>

      <div id="pagingForm">
        <!--페이지네이션-->

        <!-- 아래 코드 참고 / 다른 편한 방식으로 해도 돼!!! -->

        <!-- <div align="center" class="paging-area">

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
 -->
      </div>
    </div>
  </div>

</main>
<footer><%@include file="/views/template/footer.jsp"%></footer>
>>>>>>> origin/YEOMCODING
</body>
</html>
