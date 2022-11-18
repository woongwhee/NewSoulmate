<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-16
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사이트관리자 회원관리</title>

    <%
        ArrayList<Member> mlist = (ArrayList<Member>)request.getAttribute("member");
    %>
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

        <%for(Member m : mlist) {%>
            <tr>
                <td>
                    <%=m.getMemberNo() %>
                    <input type = "hidden" name = "memberNo" value = <%=m.getMemberNo() %>>
                </td>
                <td>
                    <%=m.getMemberName() %>
                    <input type = "hidden" name = "memberName" value = <%=m.getMemberName() %>>
                </td>
                <td>
                    <%=m.getMemberId() %>
                    <input type = "hidden" name = "memberId" value = <%=m.getMemberId() %>>
                </td>
                <td>
                    <%=m.getEmail() %>
                    <input type = "hidden" name = "Email" value = <%=m.getEmail() %>>
                </td>
                <td>
                    <%=m.getShelterNo()%>
                    <input type="hidden" name="shelterNo" value="<%=m.getShelterNo()%>">
                </td>
                <td>
                    <%=m.getMemberGrade()%>
                    <input type = "hidden" name = "memberGrade" value = <%=m.getMemberGrade()%>
                </td>
                <td>
                    <%=m.getEnrollDate() %>
                    <input type = "hidden" name = "enrollDate" value = <%=m.getEnrollDate() %>>
                </td>
            </tr>
        <%} %>
    </table>
</div>
</body>
</html>



</body>
</html>
