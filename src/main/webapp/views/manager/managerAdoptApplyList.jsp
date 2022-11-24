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
    <title>입양신청목록</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>

<div class="headcontainer">
  <div id="right_view">
    <div id="adopt_apply_list">
        <br>
        <br>
        <div class="box">
            <b>총 내역
            <span id="countApply" style="color: #f45d48;"><%= ms.selectAdoptApplyListCount()%></span> 개</b>
        </div>
        <br>
        <br>

        <div id="applyList">
            <table class="list-area" style="text-align: center">
                <thead>
                <tr>
                    <th width="100">신청번호</th>
                    <th width="250">공고번호</th>
                    <th width="100">아이디</th>
                    <th width="100">신청인</th>
                    <th width="200">전화번호</th>
                    <th width="100">신청일자</th>
                    <th width="100">상태</th>
                </tr>
                </thead>
                <tbody>
                <% if(list == null || list.isEmpty()) {%>
                    <tr id="tableEmpty">
                        <td colspan = "7">존재하는 신청내역이 없습니다.</td>
                    </tr>
                <% } else {%>
                    <% for (Subscription s : list) {%>
                    <tr>
                        <td><%=s.getSubNo()%></td>
                        <td><%=s.getAnimalNo()%></td>
                        <td><%=s.getMemberId()%></td>
                        <td><%=s.getName()%></td>
                        <td><%=s.getTelNum()%></td>
                        <td><%=s.getSubDate()%></td>
                        <c:set var = "subRead" value="<%=s.getSubRead()%>"/>
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
        </div>

    </div>

  </div>
</div>

        <script>
            $(function(){
                $(".list-area>tbody>tr").click(function(){
                    // 클릭시 해당 공지사항의 번호를 넘겨야함.
                    // 해당 tr요소의 자손중에서 첫번째 td의 영역의 내용이 필요.
                    if($(this).text()!=$("#tableEmpty").text()) { // 조회된 리스트가 없을경우 클릭방지

                        let sno = $(this).children().eq(0).text(); // 0 => s.getSubNO()
                        // 현재 내가클릭한 tr의 자손들중 0번째에 위치한 자식의 textnode내용을 가져온다.
                        location.href = "<%=request.getContextPath()%>/adoptApplyDetail?sno=" + sno;

                    }
                });

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
                location.href = "<%=request.getContextPath()%>/adoptApplyList?currentPage="+currentPage;
            }
        </script>

</body>
</html>
