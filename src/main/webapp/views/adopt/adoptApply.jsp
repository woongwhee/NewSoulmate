<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/07
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Calendar date = Calendar.getInstance();
    SimpleDateFormat today = new SimpleDateFormat("yyy년 MM월 dd일");
    String dno= (String)request.getAttribute("dno");
%>

<!doctype html>
<html lang="en">
    <title>입양 신청하기</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<form action="<%=request.getContextPath()%>/adoptApplyInsert" method="post">

    // login_no hidden으로 숨겨서 넘기기 loginUserNo -> 나중에 작업
    <input type="hidden" name="subRead" value="N">
    <table>
        <tr>
            <th>이름</th>
            <td><input type="text" name="name"></td>

            <!--
                dno값이 있다면 dno를 서블릿으로 넘겨주고
                dno값이 없다면 사용자가 입력한 animalNo를 서블릿으로 넘겨주기
            -->

            <th>공고번호</th>
            <td><% if(dno==null){%>
                    <input type="text" id="animalNo" name="animalNo">
                <% }else{ %>
                    <input type="text" id="animalNo" name="animalNo" value="${dno}" disabled>
                <%}%>
            </td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><input type="text" name="telNum"></td>
            <th>성별</th>
            <td><input type="radio" name="gender"value="M" id="male"><label for="male">남자</label>
                <input type="radio" name="gender" value="F" id="female"> <label for="female">여자</label></td>
        </tr>
        <tr>
           <th colspan="4">입양을 결정하게 된 이유</th>
        </tr>
        <tr>
            <td colspan="4"><input type="text" name="adoptReason"></td>
        </tr>
        <tr>
            <th colspan="4">가족 구성원의 반대가 없었는지?</th>
        </tr>
        <tr>
            <td colspan="4"><input type="text" name="agreement"></td>
        </tr>
        <tr>
            <th colspan="4">입양해간 아이가 많이 아프다면?</th>
        </tr>
        <tr>
            <td colspan="4"><input type="text" name="whenSick"> </td>
        </tr>
        <tr>
            <th colspan="4">평생 사랑으로 책일질 수 있는지?</th>
        </tr>
        <tr>
            <td colspan="4"><input type="text" name="bigDuty"></td>
        </tr>
        <tr>
            <th><%=today.format(date.getTime())%></th>
        </tr>
        <tr>
            <th>입양희망날짜</th>
            <td><input type="date" name="wishDate"></td>

        </tr>
    </table>

    <input type="submit" value="입양신청">
</form>
<script>

</script>


</body>
</html>