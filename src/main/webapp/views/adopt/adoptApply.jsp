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
    <link href="<%=request.getContextPath()%>/css/common/adoptApply.css" rel="stylesheet">

</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div id="content">
    <div id="war-box">
        <i class="bi bi-info-circle"></i>
        <div id="war-comment">환승주인의 동물들은 모두 입양 전 교육이수 후 첫만남이 가능합니다.<br>
            동물들의 나이는 추정나이로써 실제 나이와 일치하지 않을 수 있습니다<br>
            입양 후 생활패턴이나 환경 등 변화에 따라 성격이 달라질 수 있으니 충분히 고려하신 후 입양신청 부탁드립니다.<br>
        </div>
    </div>
    <form action="<%=request.getContextPath()%>/adoptApplyInsert" method="post">

<%--     login_no hidden으로 숨겨서 넘기기 loginUserNo -> 나중에 작업--%>
        <input type="hidden" name="subRead" value="N">
        <div id="adopt-form">
            <div id="form-title">입양신청서</div>
            <div id="user-info">
                <div class="info-group">이름</div>
                <input type="text" id="user-name" name="name" required>

                <div class="info-group">공고번호</div>
                <% if(dno==null){%>
                <input type="text" id="animalNo" name="animalNo" required>
                <% }else{ %>
                <input type="text" id="animalNo" name="animalNo" value="${dno}" required>
                <%}%>
                <div class="info-group">전화번호</div>
                <input type="text" id="user-phone" name="telNum" required>
                <div class="info-group">성별</div>
                <div id="gender-btn-box">
                    <input type="radio" id="user-genderM" name="gender" value="M" id="male" required><label
                        for="user-genderM" class="label">남자</label>
                    <input type="radio" id="user-genderF" name="gender" value="F" id="female">
                    <label for="user-genderF" class="label">여자</label>
                </div>
                <div class="info-group">입양희망날짜</div>
                <input id="wishdate" type="date" name="wishDate" required>
            </div>
            <table id="adopt-mind">
                <tr>
                    <th colspan="4">입양을 결정하게 된 이유</th>
                </tr>
                <tr>
                    <td colspan="4"><textarea name="adoptReason" style="resize: none" minlength="50"></textarea></td>
                </tr>
                <tr>
                    <th colspan="4">가족 구성원의 반대가 없었는지?</th>
                </tr>
                <tr>
                    <td colspan="4"><textarea name="agreement" style="resize: none"></textarea></td>
                </tr>
                <tr>
                    <th colspan="4">입양해간 아이가 많이 아프다면?</th>
                </tr>
                <tr>
                    <td colspan="4"><textarea name="whenSick" style="resize: none" minlength="50"></textarea></td>
                </tr>
                <tr>
                    <th colspan="4">평생 사랑으로 책임질 수 있는지?</th>
                </tr>
                <tr>
                    <td colspan="4"><textarea name="bigDuty" style="resize: none" minlength="50"></textarea></td>
                </tr>
            </table>
            <div id="apply-date">
                <%=today.format(date.getTime())%>
            </div>
            <div id="btn-box3">
                <button id="adopt-btn">입양신청하기</button>
            </div>
        </div>
    </form>
</div>
<script>
    let now_utc = Date.now()
    let timeOff = new Date().getTimezoneOffset()*60000;
    let today = new Date(now_utc-timeOff).toISOString().split("T")[0];
    document.getElementById("wishdate").setAttribute("min", today);
</script>

</body>
    <%@include file="/views/template/footer.jsp"%>
</html>