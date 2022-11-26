<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-24
  Time: 오후 7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="tk.newsoulmate.domain.vo.response.ShelterSupportResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="tk.newsoulmate.domain.vo.type.WithdrawStatus" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="tk.newsoulmate.domain.vo.SupportPage" %>
<%@ page import="tk.newsoulmate.domain.vo.response.ManageSupportResponse" %>
<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Support> allList = (ArrayList<Support>) request.getAttribute("allList");

    // SupportPage pageInfo = (SupportPage) request.getAttribute("pageInfo");
    //
    // int currentPage = pageInfo.getPage();
    // int startPage = pageInfo.getStartPage();
    // int endPage = pageInfo.getEndPage();
    // int maxPage = pageInfo.getMaxPage();
%>

<html>
<head>
    <title>후원관리 - 전체 후원내역 확인</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>

    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/manager/manageSupportHistory.css" rel="stylesheet">
</head>

<body>
<header>
    <%@include file="/views/manager/managerHeader.jsp" %>
</header>


<div class="headcontainer">

    <div id="right_text_fiex">
        <div class="right_text">
            <h1>전체 후원내역</h1>
        </div>
    </div>

    <div id="user_information">
<%--
                    <div class="box">
                       <div id="supportDate">
                            <span>조회기간</span>
                            <span><input type="date" id="startDate" value="<%=startDate%>"></span>
                            <span>~</span>
                            <span><input type="date" id="endDate" value="<%=endDate%>"></span>
                            <button id="searchBtn" onclick="searchByDate()">조회</button>
                        </div>
                    </div>
--%>

        <div id="supportSupportAllHistory">

        </div>
    </div>

    <div>

        <div id="supportAllrList">
            <table>
                <thead>
                <tr>
                    <th>결제번호</th>
                    <th>후원보호소명</th>
                    <th>후원일시</th>
                    <th>후원금액</th>
                    <th>후원자</th>
                    <th>결제상태</th>
                    <th>출금여부</th>
                </tr>
                </thead>
                <%if ( allList == null || allList.isEmpty()) {%>
                <tr>
                    <td colspan="6">존재하는 후원내역이 없습니다.</td>
                </tr>

                <%} else { %>
                <%for (Support su : allList) {%>
                <tr>
                    <td><%=su.getSupportNo()%>
                    </td>
                    <td><%=su.getMerchantUid()%>
                    </td>
                    <td><%=su.getShelterNo() %>
                    </td>
                    <td><%=su.getPayTime() %>
                    </td>
                    <td><%=su.getStatus()%>
                    </td>
                    <td><%=su.getMemberNo()%>

                    </td>
                </tr>
                <% } %>
                <% } %>
                <tbody>
            </table>
        </div>


    </div>


</div>
</div>


</body>

</html>