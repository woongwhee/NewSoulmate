<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/HuskyEZCreator.js" charset="UTF-8"></script>
    <link href="<%=request.getContextPath()%>/css/board/adoptReviewEnroll.css" rel="stylesheet">
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div id="content">
    <div id="review-enroll-title">입양후기 작성하기</div>
    <form action="adoptRevInsert" method="post" id="adoptReview">
        <div id="review-enroll-form">
            <table id="review-enroll-table">
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력해주세요"></td>
                </tr>
                <tr>
                    <th>입양날짜</th>
                    <td><input type="Date" id="adoptDate" name="adoptDate"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td> <textarea name="boardContent" id="boardContent" placeholder="내용을 입력해주세요"></textarea></td>
                </tr>
            </table>
        </div>
        <div id="adopt-review-btn">
            <button id="return" type="button" onclick="location.href = '${context}/adoptRevList'">목록으로 돌아가기</button>
            <button type="button" id="save">작성하기</button>
        </div>
    </form>
</div>
<script>let context=${context}</script>
<script src="${context}/js/adopt/adoptRevEnroll.js"></script>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>
