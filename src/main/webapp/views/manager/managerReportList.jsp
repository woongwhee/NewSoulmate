<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page import="tk.newsoulmate.domain.vo.Report" %><%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-27
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Report> list = (ArrayList<Report>) request.getAttribute("list");
    PageInfo pi = (PageInfo) request.getAttribute("pi");
    Report r = new Report();
    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
%>
<html>
<head>
    <title>신고접수내역</title>
    <link href="<%=request.getContextPath()%>/css/manager/managerAdoptApplyList.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="reportList">
            <br>
            <br>
            <div id="applyList">
                <table class="list-area" style="text-align: center">
                    <thead>
                    <tr>
                        <th width="100">신고번호</th>
                        <th width="100">글번호</th>
                        <th width="200">제목</th>
                        <th width="200">신고사유</th>
                        <th width="100">처리결과(상태)</th>
                        <th width="100">확인</th>
                        <th width="100" class="rHidden"></th>

                    </tr>
                    </thead>
                    <tbody>
                    <% if(list == null || list.isEmpty()) {%>
                    <tr id="tableEmpty">
                        <td colspan = "6">존재하는 신고내역이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <% for (Report rp : list) {%>
                    <tr>
                        <input type="hidden" class="boardType" value="<%=rp.getBoardType().boardName%>"/>
                        <td><%=rp.getReportNo()%></td>
                        <td><%=rp.getRefNo()%></td>
                        <td><%=rp.getBoardTitle()%></td>
                        <td><%=rp.getReportContent()%></td>
                        <c:set var = "status" value="<%=rp.getStatus()%>"/>
                        <c:choose>
                            <c:when test="${status eq 'Y'}">
                                <td>미처리</td>
                            </c:when>
                            <c:otherwise>
                                <td>처리완료</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                        <form action="${context}/reportStatus" type="post">
                            <input type="hidden" name="bno" value="${r.refNo}">
                            <input type="hidden" name="status" value="${r.status}">
                            <input type="submit" id="confirm" value="처리완료" class="btn btn-danger btn-sm">
                        </form>
                        </td>


                    </tr>
                    <% }%>
                    <% } %>
                    </tbody>
                </table>
            </div>
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
                    location.href = "${context}/reportList?currentPage="+currentPage;
                }
            </script>
        </div>

    </div>
</div>
<script>
    $(function(){
        $(".list-area>tbody>tr").click(function(){
            // 클릭시 해당 공지사항의 번호를 넘겨야함.
            // 해당 tr요소의 자손중에서 첫번째 td의 영역의 내용이 필요.
            if($(this).text()!=$("#tableEmpty").text()) { // 조회된 리스트가 없을경우 클릭방지

                // 현재 내가클릭한 tr의 자손들중 0번째에 위치한 자식의 textnode내용을 가져온다.

                let bno = $(this).children().eq(1).text(); // 0 => s.getReportNo()
                let typeName = $(this).children().eq(0).val(); // 4 => b.getBoardType.boardName
                location.href = '${context}/' + typeName + 'Detail?bno=' + bno;

            }
        });
        $('.bHidden').attr('style', "display:none;");
    });
</script>
<br><br>
</body>
</html>
