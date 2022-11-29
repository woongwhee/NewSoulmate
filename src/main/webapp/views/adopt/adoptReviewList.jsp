<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>입양후기</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/board/adoptReviewList.css" rel="stylesheet">
</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>
    <div id="content">
        <div id="adopt-review-head">
            <p>입양후기</p>
            <button onclick="location.href='adoptRevEnroll'">작성하기</button>
        </div>
        <div id="adopt-review-area">
            <c:forEach items="${tList}" var="t">
            <div class="adopt-thum">
                <img class="adopt-thumnail" src="${t.filePath}/${t.changeName}" onclick="location.href='${context}/adoptRevDetail?bno=${t.boardNo}'">
                <p>${t.boardTitle}</p>
            </div>
            </c:forEach>
        </div>
        <div><img src="${context}/image/loading.gif"alt="로딩중" id="loadingImg"></div>
    </div>
    <%@ include file="/views/template/footer.jsp"%>
    <script>
        const listCount=${listCount};
    </script>
    <script src="${context}/js/adopt/adoptList.js"></script>
</body>
</html>
