<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Notice" %>
<%@ page import="tk.newsoulmate.domain.vo.Volunteer" %><%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-24
  Time: 오후 3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Volunteer v = (Volunteer)request.getAttribute("v");
%>

<html>
<head>
  <title>입양신청서확인</title>
  <link href="<%=request.getContextPath()%>/css/manager/managerAdoptApplyDetail.css" rel="stylesheet">
  <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>

<div class="headcontainer">
  <div id="right_view">
    <div id="adoptApplyDetail">
      <br>
      <br>
      <table class="check-area" style="text-align: center">

        <tr width="150">
          <th>신청 번호</th>
          <td>${v.volunteerNo}</td>
        </tr>

        <tr width="150">
          <th>신청인</th>
          <td>${v.name}</td>
        </tr>

        <tr width="150">
          <th>신청 아이디</th>
          <td>${v.memberId}</td>
        </tr>

        <tr width="150">
          <th>보호소 이름</th>
          <td>${v.shelterName}</td>
        </tr>
        <tr width="150">
          <th>봉사 희망 날짜</th>
          <td>${v.startDate}</td>
        </tr>
        <tr width="150">
          <th>신청 날짜</th>
          <td>${v.applyDate}</td>
        </tr>
        <tr width="150">
          <th>신청인 전화번호</th>
          <td>${v.telNumber}</td>
        </tr>
        <tr width="150">
          <th>성별</th>
          <td>
            <c:if test="${v.gender eq 'M'}">남자</c:if>
            <c:if test="${v.gender eq 'F'}">여자</c:if>
          </td>
        </tr>
      </table>

    </div>
  </div>
</div>


</body>
</html>
