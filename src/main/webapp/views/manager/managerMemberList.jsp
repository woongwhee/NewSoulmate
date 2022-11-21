<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.MemberGrade" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-16
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Member> mList = (ArrayList<Member>) request.getAttribute("mList");
%>
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
                총 회원 수 <span id="memberTotal"><%= mList != null ?
                    mList.stream().mapToLong(ManagerCompleteResponse::getAmount).sum() : 0 %>
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
                        <th>회원번호</th>
                        <th>이름</th>
                        <td>닉네임</td>
                        <th>아이디</th>
                        <th>이메일</th>
                        <th>보호소이름</th>
                        <th>등급</th>
                        <th>가입일</th>
                        <th>최근접속일</th>
                        <th>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%if(mList == null || mList.isEmpty()) {%>
                    <tr>
                        <td colspan = "10">존재하는 회원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>

                    <%for (Member m : mList) {%>
                    <tr>
                        <td><%=m.getMemberNo() %>
                        </td>
                        <td><%=m.getMemberName() %>
                        </td>
                        <td><%=m.getNickName()%>
                        </td>
                        <td><%=m.getMemberId() %>
                        </td>
                        <td><%=m.getEmail() %>
                        </td>
                        <td><%=m.getShelterNo()%>
                        </td>
                        <td>
                            <%if(m.getMemberGrade() == MemberGrade.valueOfNumber(0)) { %>
                            관리자
                            <input type="hidden" name="memberGrade" value=<%=m.getMemberGrade()%>>
                            <%} else if (m.getMemberGrade() == MemberGrade.valueOfNumber(1)){ %>
                            보호소관계자
                            <input type="hidden" name="memberGrade" value=<%=m.getMemberGrade()%>>
                            <%} else { %>
                            일반회원
                            <input type="hidden" name="memberGrade" value=<%=m.getMemberGrade()%>>
                            <%} %>
                        </td>
                        <td><%=m.getEnrollDate() %>
                        </td>
                        <td><%=m.getResentConnection()%>
                        </td>
                        <td>
                            <button>탈퇴</button>
                        </td>
                    </tr>
                    <%} %>
                    <%} %>
                    </tbody>
                </table>
            </div>

            <div id="pagingForm">
                <!--페이지네이션-->

                <!-- 아래 코드 참고 / 다른 편한 방식으로 해도 돼!!! -->

                <!-- <div align="center" class="paging-area">

<%--                        <% if(currentPage != 1) { %>
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
                        <% } %>--%>

                    </div>
     -->
            </div>

        </div>
    </div>
</div>

</body>
</html>
