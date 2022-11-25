<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
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
    <title>메세지함</title>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterMessage.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterMessage.css" rel="stylesheet">
</head>
<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                <p>입양신청함</p>
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th width="20px">신청번호</th>
                        <th>공고번호</th>
                        <th>아이디</th>
                        <th>신청인</th>
                        <th>전화번호</th>
                        <th>신청일자</th>
                        <th>상태</th>
                        <th width="20px">관리</th>
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

                                <td>
                                    <input type="button" id="delete" class="btn btn-primary" data-bs-toggle="modal"
                                           data-bs-target="#staticBackdrop" value="삭제">
                                </td>
                            </tr>
                        <% }%>
                    <% } %>

                    </tbody>
                </table>

                <div id="pagingForm">
                    <!--페이지네이션-->
                </div>

            </div>

            <!-- ------------------------------------------------------------------------>


            <div class="box2">
                <p>봉사신청함</p>
            </div>

            <div id="memberList2">
                <table>
                    <thead>
                    <tr>
                        <th width="20px">No</th>
                        <th>봉사 희망 시간</th>
                        <th>보낸 시간</th>
                        <th>보낸 사람</th>
                        <th>상태</th>
                        <th width="20px">관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!--보여질 페이지 수 지정-->
                    <c:forEach var="board" items="${}">
                        <tr>
                            <td>
                                <!--번호 ${가져오기}-->1
                            </td>
                            <td>
                                <!--봉사희망시간 ${가져오기}-->ds한국동물구조관리협회
                            </td>
                            <td>
                                <!--보낸시간 ${가져오기}-->2022-10-22 10:22
                            </td>
                            <td>
                                <!--보낸사람-->20,000
                            </td>
                            <td>
                                <!--상태-->df
                            </td>
                            <td>
                                <!--관리-->
                                <input type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                       data-bs-target="#staticBackdrop" value="삭제">

                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>

                <div id="pagingForm">
                    <!--페이지네이션-->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
