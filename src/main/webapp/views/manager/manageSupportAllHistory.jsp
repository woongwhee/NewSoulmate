<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-24
  Time: 오후 7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="tk.newsoulmate.domain.vo.type.SupportStatus" %>
<%@ page import="tk.newsoulmate.domain.vo.type.WithdrawStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Support> allList = (List<Support>) request.getAttribute("mList");

    PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

    int currentPage = pageInfo.getCurrentPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();
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
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                <div id="supportDate">
                    <span>보호소 검색</span>
                    <span><input type="text" id="shelterName"
                                 value="<%=request.getParameter("filter") == null ? "" : request.getParameter("filter")%>">
                    </span>
                    <button id="searchBtn" onclick="searchByFilter()">조회</button>
                </div>
            </div>

            <div id="supportSupportAllHistory">

            </div>

            <div>

                <div id="supportAllrList">
                    <table>
                        <thead>
                        <tr>
                            <th>후원번호</th>
                            <th>결제번호</th>
                            <th>후원보호소명</th>
                            <th>후원일시</th>
                            <th>후원금액</th>
                            <th>후원자</th>
                            <th>결제상태</th>
                            <th>출금여부</th>
                        </tr>
                        </thead>
                        <%if (allList == null || allList.isEmpty()) {%>
                        <tr>
                            <td colspan="8">존재하는 후원내역이 없습니다.</td>
                        </tr>

                        <%} else { %>
                        <%for (Support su : allList) {%>
                        <tr>
                            <td><%=su.getSupportNo()%>
                            </td>
                            <td><%=su.getMerchantUid()%>
                            </td>
                            <td><%=su.getShelterName() %>
                            </td>
                            <td><%=su.getPayTime() %>
                            </td>
                            <td><%=su.getAmount()%> 원</td>
                            <td><%=su.getMemberName()%>
                            </td>
                            <td>
                                <%if (su.getStatus() == SupportStatus.DONE) { %>
                                결제완료
                                <%} else if (su.getStatus() == SupportStatus.PENDING) { %>
                                취소
                                <%} else { %>
                                결제실패
                                <%} %>
                            </td>
                            <td>
                                <%if (su.getWithdrawStatus() == WithdrawStatus.DONE) { %>
                                출금승인
                                <%} else if (su.getWithdrawStatus() == WithdrawStatus.PENDING) { %>
                                출금신청전
                                <%} else if (su.getWithdrawStatus() == WithdrawStatus.REQUESTED) { %>
                                출금신청중
                                <%} else {%>
                                취소
                                <%} %>
                            </td>
                        </tr>
                        <% } %>
                        <% } %>
                        <tbody>
                    </table>
                </div>
                <div align="center" class="paging-area"> <!--페이징바-->
                    <% if (currentPage != 1) { %>
                    <button onclick="doPageClick(<%=currentPage - 1%>)" class="btn btn-secondary btn-sm">&lt;</button>
                    <% } %>

                    <% for (int i = startPage; i <= endPage; i++) { %>
                    <% if (i != currentPage) {%>
                    <button onclick="doPageClick(<%= i %>)" class="btn btn-secondary btn-sm"><%= i %>
                    </button>
                    <% } else { %>
                    <button disabled><%=i %>
                    </button>
                    <% } %>
                    <% } %>

                    <% if (currentPage != maxPage) { %>
                    <button onclick="doPageClick(<%=currentPage + 1%>)" class="btn btn-secondary btn-sm">&gt;</button>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function(){
        $(".list-text").text("전체 후원내역");
    })

    function doPageClick(currentPage) {
        location.href = "${context}/manageSupportAllHistory?page=" + currentPage + "&filter=" + $("#shelterName").val();
    }

    function searchByFilter() {
        location.href = "${context}/manageSupportAllHistory?page=1&filter=" + $("#shelterName").val();
    }
</script>

</body>

</html>