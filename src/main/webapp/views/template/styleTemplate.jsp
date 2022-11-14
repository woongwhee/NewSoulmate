<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/12
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/css/template/menubar.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/template/footer.css" rel="stylesheet">

<script>
    let msg = "<%= alertMsg %>"; // let msg = 성공적으로 로그인이 되었습니다.

    if(msg != "null") {
        alert(msg);
        // 알림창을 띄워준 후 session에 담긴 해당메세지는 지워줘야함.
        // 안그러면 menubar.jsp가 로딩될때마다 매번 alert가 계속 뜰 것.

        <% session.removeAttribute("alertMsg");%>
    }

</script>