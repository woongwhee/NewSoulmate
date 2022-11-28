<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page import="tk.newsoulmate.domain.vo.Volunteer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Subscription> sList = (ArrayList<Subscription>) request.getAttribute("sList");
    ArrayList<Volunteer> vList = (ArrayList<Volunteer>)request.getAttribute("vList");

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
                    <% if(sList == null || sList.isEmpty()) {%>
                    <tr id="tableEmpty">
                        <td colspan = "7">존재하는 신청내역이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <% for (Subscription s : sList) {%>
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
                                    <input type="button" class="delete" class="btn btn-primary" data-bs-toggle="modal"
                                           data-bs-target="#staticBackdrop" value="삭제">
                                </td>
                            </tr>
                        <% }%>
                    <% } %>

                    </tbody>
                </table>

                <div id="pagingForm1" align="center">
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

            </div>
            <script>
                $(".delete").on("click",function(){

                    let thisRow = $(this).closest("tr");
                    let addr = thisRow.find("td:eq(0)").val();
                    console.log(addr);
                })
            </script>




            <div class="box2">
                <p>봉사신청함</p>
            </div>

            <div id="memberList2">
                <table>
                    <thead>
                    <tr>
                        <th width="20px">아이디</th>
                        <th>신청인</th>
                        <th>보호소명</th>
                        <th>봉사 희망 날짜</th>
                        <th>전화번호</th>
                        <th>상태</th>
                        <th width="20px">관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if(vList == null || vList.isEmpty()) {%>
                    <tr id="tableEmpty2">
                        <td colspan = "7">존재하는 신청내역이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <% for (Volunteer v : vList) {%>
                    <tr>
                        <td><%=v.getMemberId()%></td>
                        <td><%=v.getName()%></td>
                        <td><%=v.getShelterName()%></td>
                        <td><%=v.getStartDate()%></td>
                        <td><%=v.getTelNumber()%></td>

                        <c:set var = "volRead" value="<%=v.getVolRead()%>"/>
                        <c:choose>
                            <c:when test="${volRead eq 'N'}">
                                <td>미확인</td>
                            </c:when>
                            <c:otherwise>
                                <td>확인</td>
                            </c:otherwise>
                        </c:choose>

                        <td>
                            <!--관리-->
                            <input type="button" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#staticBackdrop" value="삭제">
                        </td>
                    </tr>
                    <% }%>
                    <% } %>

                    </tbody>
                </table>

                <div id="pagingForm2" align="center">
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
            </div>
        </div>
    </div>
</div>

</body>
</html>
