<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/21
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div> 조회결과 : ${nList.size}</div>
<c:forEach items="${nList}" var="n">
  <div class="notice">
    <img class="notice-photo" src="${n.popfile}" alt="" onclick="location.href='noticeDetail?dno=${n.desertionNo}'">
  </div>
</c:forEach>