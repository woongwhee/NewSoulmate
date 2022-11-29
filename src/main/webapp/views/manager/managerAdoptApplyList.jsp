<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-23
  Time: 오후 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Subscription> list = (ArrayList<Subscription>) request.getAttribute("list");
    ManageService ms = new ManageService();
    PageInfo pi = (PageInfo) request.getAttribute("pi");
    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
%>
<html>
<head>
    <title>입양관리</title>
    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/manager/managerAdoptApplyList.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/views/manager/managerHeader.jsp" %>
</header>

<div class="headcontainer">
    <div id="right_view">
        <div id="applyList">

            <div class="box">
                총 내역 <span id="countApply"><%= ms.selectAdoptApplyListCount()%></span> 개
            </div>
            <br>
                <table class="list-area">
                    <thead>
                    <tr>
                        <th width="10px">No</th>
                        <th>공고번호</th>
                        <th>아이디</th>
                        <th>신청인</th>
                        <th>전화번호</th>
                        <th>신청일자</th>
                        <th>상태</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (list == null || list.isEmpty()) {%>
                    <tr id="tableEmpty">
                        <td colspan="7">존재하는 신청내역이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <% for (Subscription s : list) {%>
                    <tr>
                        <td><%=s.getSubNo()%>
                        </td>
                        <td><%=s.getAnimalNo()%>
                        </td>
                        <td><%=s.getMemberId()%>
                        </td>
                        <td><%=s.getName()%>
                        </td>
                        <td><%=s.getTelNum()%>
                        </td>
                        <td><%=s.getSubDate()%>
                        </td>
                        <c:set var="subRead" value="<%=s.getSubRead()%>"/>
                        <c:choose>
                            <c:when test="${subRead eq 'N'}">
                                <td>미확인</td>
                            </c:when>
                            <c:otherwise>
                                <td>확인</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <% }%>
                    <% } %>
                    </tbody>
                </table>
                <br><br>

                <%-- 페이징바 처리--%>
                <div align="center" class="paging-area">

                    <% if (currentPage != 1) { %>
                    <button onclick="doPageClick(<%=currentPage-1%>)" class="btn btn-secondary btn-sm">&lt;</button>

                    <% } %>

                    <% for (int i = startPage; i <= endPage; i++) { %>
                    <% if (i != currentPage) {%>
                    <button onclick="doPageClick(<%=i%>)" class="btn btn-secondary btn-sm"><%=i %>
                    </button>
                    <% } else { %>
                    <button disabled><%=i %>
                    </button>
                    <% } %>
                    <% } %>

                    <% if (currentPage != maxPage) { %>
                    <button onclick="doPageClick(<%=currentPage+1%>)" class="btn btn-secondary btn-sm">&gt;</button>

                    <% } %>


                </div>
                <script>
                    function doPageClick(currentPage) {
                        location.href = "<%=request.getContextPath()%>/adoptApplyList?currentPage=" + currentPage;
                    }
                </script>
            </div>
        </div>
    </div>
</div>

<script>
    $(function(){
        $(".list-text").text("입양관리");
    })

    $(function () {
        $(".list-area>tbody>tr").click(function () {
            // 클릭시 해당 공지사항의 번호를 넘겨야함.
            // 해당 tr요소의 자손중에서 첫번째 td의 영역의 내용이 필요.
            if ($(this).text() != $("#tableEmpty").text()) { // 조회된 리스트가 없을경우 클릭방지

                let sno = $(this).children().eq(0).text(); // 0 => s.getSubNO()
                // 현재 내가클릭한 tr의 자손들중 0번째에 위치한 자식의 textnode내용을 가져온다.
                location.href = "<%=request.getContextPath()%>/adoptApplyDetail?sno=" + sno;

            }
        });

    });
</script>
</body>
</html>
