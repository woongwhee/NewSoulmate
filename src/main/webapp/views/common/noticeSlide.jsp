<%@ page import="java.util.List" %>
<%@ page import="tk.newsoulmate.domain.vo.Notice" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/11
  Time: 2:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--시연용 공고문--%>
<div class="post">
    <img id="1111111111111" src="/file/resources/board_upfiles/2022/11/30/example.jpg" class="notice-img">
</div>
<c:forEach items="${nList}" var="n" >
<div class="post">
    <img id="${n.desertionNo}" src="${n.popfile}" class="notice-img">
</div>
</c:forEach>
