<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원관리-보호소 관계자 신청</title>
    <link href="<%=request.getContextPath()%>/css/manager/managerMemberShelter.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                총 대기회원 수 <span id="memberTotal">6
                <!--총 대기회원수 불러오기-->
                    </span> 명
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th width="10px"><input type="checkbox" id="" /></th>
                        <th width="20px">No</th>
                        <th>아이디</th>
                        <th>보호소명</th>
                        <th>보호소 사업자번호</th>
                        <th>사업자대표명</th>
                        <th>보호소 전화번호</th>
                        <th width="20px">첨부파일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!--보여질 페이지 수 지정-->
                    <c:forEach var="board" items="${}">
                        <tr>
                            <td>
                                <input type="checkbox" id="" />
                            </td>
                            <td>
                                <!--번호 ${가져오기}-->1
                            </td>
                            <td>
                                <!--아이디 ${가져오기}-->ds
                            </td>
                            <td>
                                <!--보호소명 ${가져오기}-->한국동물구조관리협회
                            </td>
                            <td>
                                <!--보호소 사업자번호 ${가져오기}-->df안녕
                            </td>
                            <td>
                                <!--사업자대표명 ${가져오기}-->df
                            </td>
                            <td>
                                <!--보호소 전화번호 ${가져오기}-->df
                            </td>
                            <td>
                                <input type="submit" value="확인">
                            </td>
                        </tr>


                    </c:forEach>
                    </tbody>
                </table>

                <div id="buttonBox">
                    <input type="button" value="승인">
                    <input type="button" value="거부">
                </div>
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
</div>
</body>
</html>
