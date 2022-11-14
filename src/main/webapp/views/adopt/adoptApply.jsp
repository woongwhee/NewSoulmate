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
    <table>
        <tr>
            <th>이름</th>
            <td><input type="text" name="name"></td>
            <th>공고번호</th>
            <td><input type="text" name="animalNo"></td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><input type="text" name="telNum"></td>
            <th>성별</th>
            <td><input type="radio" name="gender">남 <input type="radio" name="gender">여 </td>
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
            <td colspan="4"><input type="text" name="Agreement"></td>
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
            <th><div name="date"><%=today.format(date.getTime())%></div></th>
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