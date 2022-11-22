<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2022-11-08
  Time: 오후 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>보호소리스트</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelter/shelterList.css" rel="stylesheet">
    <style>
        .notice-photo{
            width: 200px;
            height: 300px;
        }
    </style>
</head>
<body>
<%@ include file="/views/template/menubar.jsp"%>


<div id="content">
    <div id="search">
        <label for="date">날짜</label>
        <div id="date">
            <input type="date" id="bgndate" name="bgndate">
            <input type="date" id="enddate" name="enddate">
            <p>(날짜는 접수일 기준입니다.)</p>
        </div>

        <select name="cityNo" id="cityNo" onchange="choice();">
            <option value="0">--전체--</option>
        <c:forEach items="${cList}" var="c">
            <option value="${c.cityNo}">${c.cityName}</option>
        </c:forEach>
        </select>
        <select name="villageNo" id="villageNo" onchange="choiceShel();">
            <option value="" selected disabled hidden></option>
        </select>

        <select name="shelterNo" id="shelterNo">
            <option value="" selected disabled hidden></option>
        </select>

        <label for="breed">품종</label>
        <select name="breed" id="breed">
            <option value="" selected disabled hidden></option>
            <option value="DOG">개</option>
            <option value="CAT">고양이</option>
            <option value="ANOTHER">기타</option>
        </select>
        <label for="neuter">중성화여부</label>
        <select name="neuter" id="neuter">
            <option value="" selected disabled hidden></option>
            <option value="Y">예</option>
            <option value="N">아니오</option>
            <option value="U">미상</option>
        </select>
        <button type="button" id="noticeSearch">조회</button>
    </div>
    <div id="notice-area">
<%--        <c:forEach items="${nList}" var="n">--%>
<%--        <div class="notice">--%>
<%--            <img class="notice-photo" src="${n.popfile}" alt="" onclick="location.href='noticeDetail?dno=${n.desertionNo}'">--%>
<%--        </div>--%>
<%--        </c:forEach>--%>
    </div>
</div>
    <script src="${context}/js/shelter"></script>


    <%@ include file="/views/template/footer.jsp"%>

</body>
</html>