<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-16
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>

<%
    ArrayList<Member> mList = (ArrayList<Member>) request.getAttribute("mList");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사이트관리자 회원관리</title>


</head>
<body>


<div class="member-wrap">
    <div>회원목록</div>
    <table class="">
        <tr>
            <th>회원번호</th>
            <th>이름</th>
            <th>아이디</th>
            <th>이메일</th>
            <th>보호소번호</th>
            <th>등급</th>
            <th>가입일</th>
        </tr>

        <%if(mList == null || mList.isEmpty()) {%>
        <tr>
            <td colspan = "7">존재하는 회원내역이 없습니다.</td>
        </tr>

        <%} else { %>

        <%for (Member m : mList) {%>
        <tr>
            <td><%=m.getMemberNo() %>
            </td>
            <td><%=m.getMemberName() %>
            </td>
            <td><%=m.getMemberId() %>
            </td>
            <td><%=m.getEmail() %>
            </td>
            <td><%=m.getShelterNo()%>
            </td>
            <td><%=m.getMemberGrade()%>
            </td>
            <td><%=m.getEnrollDate() %>
            </td>
        </tr>
        <%} %>
        <%} %>
    </table>
</div>
</body>
</html>


</body>
</html>
