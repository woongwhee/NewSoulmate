<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Support> supportList = (ArrayList<Support>) request.getAttribute("supportList");
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

            <%--<form action="<%=request.getContextPath()%>/">--%>

                <div id="supportDate">
                    <span>조회기간</span>
                    <span><input type="date"></span>
                    <span>~</span>
                    <span><input type="date"></span>
                    <button id="searchBtn">조회</button>
                </div>


            <%--</form>--%>




            <div id="supportList-wrap">

                <table class="supportList-area">
                    <thead>
                    <tr>
                        <th>후원번호</th>
                        <th>결제번호</th>
                        <th>후원보호소</th>
                        <th>후원금액</th>
                        <th>후원시간</th>
                        <th>결제상태</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%if(supportList == null || supportList.isEmpty()) {%>
                    <tr>
                        <td colspan = "6">존재하는 후원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>
                    <%for(Support s : supportList) { %>
                        <tr>
                            <td><%=s.getSupportNo()%></td>
                            <td><%=s.getShelterNo() %></td>
                            <td><%=s.getMerchantUid() %></td>
                            <td><%=s.getAmount() %></td>
                            <td><%=s.getPayTime() %></td>
                            <td><%=s.getStatus() %></td>
                        </tr>
                        <%} %>
                    <% } %>

                    </tbody>
                </table>
            </div>




            <div id="totalSupport">
                총 후원 금액 :
            </div>






            <script>

              /*  const arr = supportList;

                const result = arr.reduce(function add(sum, currValue) {
                    return sum + currValue;
                }, 0);

                console.log(result);*/





                function total(){


                }
            </script>



<%--            <script>
                $(function(){
                    $(".supportList-area>tbody>tr").click(function(){
                        if($(this).text()!=$("#tableEmpty").text()) {
                            let bno = $(this).children().eq(1).text();
                            location.href = "<%=request.getContextPath()%>/supportHistory?bno=" + bno;
                        }
                    });
                });
            </script>--%>

<%--            <div id="pagingForm">

                <div align="center" class="paging-area">

                    <% if (currentPage != 1) { %>
                    <button onclick="doPageClick(<%= currentPage-1 %>)">&lt;</button>
                    <% } %>

                    <% for (int i = startPage; i <= endPage; i++) { %>
                    <% if (i != currentPage) { %>
                    <button onclick="doPageClick(<%= i %>)"><%= i %>
                    </button>
                    <% } else { %>
                    <button disabled><%= i %>
                    </button>
                    <% } %>
                    <% } %>

                    <% if (currentPage != maxPage) { %>
                    <button onclick="doPageClick(<%= currentPage+1 %>)">&gt;</button>
                    <% } %>
                </div>
            </div>--%>


        </div>
    </div>

</main>
<footer>
    <%@include file="/views/template/footer.jsp" %>
</footer>
</body>
</html>
