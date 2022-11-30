<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/07
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>) request.getAttribute("sList");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/common/volunteerApply.css" rel="stylesheet">
        <title>봉사신청</title>
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div id="content">
    <form action="<%=request.getContextPath()%>/volunteerApplyEnroll" id="vol-form" method="post">
        <div id="volunteer-form">
            <p>봉사신청서</p>
            <div id="form-table">
                <div class="info-group">이름</div>
                <input type="text" id="user-name" name="name" required>

                <div class="info-group"> 봉사희망날짜</div>
                <input type="date" id="wishDate" name="wishDate"  required>

                <div class="info-group">전화번호</div>
                <input type="text" id="user-phone" name="telNum" required>

                <div class="info-group">성별</div>
                <div id="gender-btn-box">
                    <input type="radio"  name="gender" value="M" id="male" required>
                    <label for="male" class="label">남자</label>
                    <input type="radio"  name="gender" value="F" id="female">
                    <label for="female" class="label">여자</label>
                </div>

                <div class="info-group">보호소</div>
                <select id="selectShelter" name="shelterNo" required>
                    <option value="0">보호소를 선택해주세요.</option>
                    <%for(Shelter s : sList){%>
                        <option  value="<%=s.getShelterNo()%>">
                            <%=s.getShelterName()%>
                        </option>
                    <%}%>
                </select>
            </div>
            <button type="button" id="submitBtn">봉사신청하기</button>
        </div>
    </form>

    <p>봉사신청 가능한 보호소</p>
    <table class="list-area">
        <thead>
        <tr>
            <th>보호소명</th>
            <th>보호소 주소</th>
            <th>보호소 전화번호</th>
        </tr>
        </thead>
        <tbody>
        <%for(Shelter s : sList){ %>
        <tr>
            <td style="display:none">
                <%= s.getShelterNo()%>
            </td>
            <td>
                <%= s.getShelterName() %>
            </td>
            <td>
                <%= s.getShelterAddress() %>
            </td>
            <td>
                <%= s.getShelterLandline() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script>

    var now_utc = Date.now()
    var timeOff = new Date().getTimezoneOffset()*60000;
    var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
    document.getElementById("wishDate").setAttribute("min", today);

    $(".list-area>tbody>tr").click(function () {
        let shelterNo = $(this).children().eq(0).text().trim();
        $("#selectShelter").val(shelterNo);
        console.log(shelterNo);
        console.log($("#wishDate").val())
    })
    $('#submitBtn').click(()=>{
        if(validate()){
            $('#vol-form').submit();

        }else{
            $('#user-phone').focus();
            alert('올바르지 못한 핸드폰 번호입니다.');
        }


    })

    function validate(){
        let regPhone =/^01([1|6|7|8|9])?([0-9]{7,8})$/
        return !regPhone.test($('#user-phone').val())
    }
</script>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>
