<%@ page import="java.util.List" %>
<%@ page import="tk.newsoulmate.domain.vo.Notice" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/11
  Time: 2:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<Notice> nlist= (List<Notice>) request.getAttribute("nList");%>
<%for(Notice n:nlist){
%>
<div class="post">
    <img id="<%=n.getDesertionNo()%>" src="<%=n.getPopfile()%>" class="notice-img">
</div>
<%
}%>
