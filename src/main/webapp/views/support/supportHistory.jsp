<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.SupportPage" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Support> supportList = (ArrayList<Support>) request.getAttribute("supportList");
    SupportPage pageInfo = (SupportPage) request.getAttribute("pageInfo");

    int currentPage = pageInfo.getPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();

    LocalDate startDate = request.getAttribute("startDate") != null ? (LocalDate) request.getAttribute("startDate") : null;
    LocalDate endDate = request.getAttribute("endDate") != null ? (LocalDate) request.getAttribute("endDate") : null;
%>

<html>
<head>
    <title>후원내역</title>
    <%@include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/support/supportHistroy.css" rel="stylesheet">
</head>

<body>
<header>
    <%@include file="/views/template/menubar.jsp" %>
</header>
<main>


    <div id="container">

        <div class="content_main">
            <b>${loginUser.memberName}</b>님의 후원 내역
        </div>

        <div class="content_wrap">

            <div id="supportDate">
                <span>조회기간</span>
                <span><input type="date" id="startDate" value="<%=startDate%>"></span>
                <span>~</span>
                <span><input type="date" id="endDate" value="<%=endDate%>"></span>
                <button id="searchBtn" onclick="searchByDate()">조회</button>
            </div>

            <div id="supportList-wrap">
                <table class="supportList-area">
                    <thead>
                    <tr>
                        <th>후원번호</th>
                        <th>후원보호소</th>
                        <th>후원금액</th>
                        <th>후원시간</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%if(supportList == null || supportList.isEmpty()) {%>
                    <tr>
                        <td colspan = "4">존재하는 후원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>
                    <%for(Support s : supportList) { %>
                    <tr>
                        <td><%=s.getSupportNo()%></td>
                        <td><%=s.getShelterName()%></td>
                        <td><%=s.getAmount() %></td>
                        <td><%=s.getPayTime() %></td>
                    </tr>
                    <%} %>
                    <% } %>

                    </tbody>
                </table>
            </div>

            <div align="center" class="paging-area"> <!--페이징바-->
                <% if(currentPage != 1) { %>
                <button onclick="doPageClick(<%=currentPage - 1%>)" class="btn btn-secondary btn-sm">&lt;</button>
                <% } %>

                <% for(int i = startPage; i <= endPage; i++) { %>
                <% if(i != currentPage) {%>
                <button onclick="doPageClick(<%= i %>)" class="btn btn-secondary btn-sm"><%= i %></button>
                <% } else { %>
                <button disabled><%=i %></button>
                <% } %>
                <% } %>

                <% if(currentPage != maxPage) { %>
                <button onclick="doPageClick(<%=currentPage + 1%>)" class="btn btn-secondary btn-sm">&gt;</button>
                <% } %>
            </div>

            <div id="supportTotal">
                총 금액 : <%= supportList != null ?
                    supportList.stream().mapToLong(Support::getAmount).sum() : 0 %>
            </div>

        </div>
    </div>

</main>

<script>
    function searchByDate() {
        location.href = "${context}/supports?page=1&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val()
    }
    function doPageClick(currentPage) {
        if ($("#startDate").val() == null || $("#endDate").val() == null) {
            location.href = "${context}/supports?page=" + currentPage;
        } else {
            location.href = "${context}/supports?page=" + currentPage + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
        }
    }
</script>

<footer>
    <%@include file="/views/template/footer.jsp" %>
</footer>

</body>
</html>
