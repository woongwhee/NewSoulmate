<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원관리-회원리스트</title>
    <link href="<%=request.getContextPath()%>/css/manager/managerMemberList.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                총 회원 수 <span id="memberTotal">200
                <!--총 회원수 불러오기-->
                    </span> 명
            </div>

            <div class="box">
                회원등급
                <span id="memberGrade">
                        <label><input type="checkbox" name="" value="전체"> 전체 </label>
                        <label><input type="checkbox" name="" value="일반회원"> 일반 회원 </label>
                        <label><input type="checkbox" name="" value="보호소관계자"> 보호소 관계자 </label>
                    </span>
                <span id="gray">(체크한 회원만 조회됩니다)</span>
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>등급</th>
                        <th>가입일</th>
                        <th>관리</th>
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
                                <!--아이디 ${가져오기}-->ds
                            </td>
                            <td>
                                <!--이름 ${가져오기}-->df
                            </td>
                            <td>
                                <!--등급 ${가져오기}-->df안녕
                            </td>
                            <td>
                                <!--가입일 ${가져오기}-->df
                            </td>
                            <td>
                                <input type="submit" value="탈퇴">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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
