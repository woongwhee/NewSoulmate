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
<script>

    let oEditors = [];
    $(document).ready(function () {
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "boardContent",
            sSkinURI: "${context}/smarteditor2/SmartEditor2Skin.html",
            fCreator: "createSEditor2",
            htParams: {
                bUseToolbar: true,
                bUseVerticalResizer: true,
                bUseModeChanger: true,
            },
        });
        $("#save").click(function () {
            oEditors.getById["boardContent"].exec("UPDATE_CONTENTS_FIELD", []);
                $("#adoptReview").submit();
        });
    });
</script>
<div id="content">
    <div id="review-enroll-title">봉사후기 작성하기</div>
    <form action="${context}/volunteerRevInsert" method="post" id="adoptReview">
        <div id="review-enroll-form">
            <table id="review-enroll-table">
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력해주세요"></td>
                </tr>
                <tr>
                    <th>봉사날짜</th>
                    <td><input type="Date" id="volunteerDate" name="volunteerDate"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td> <textarea name="boardContent" id="boardContent" placeholder="내용을 입력해주세요"></textarea></td>
                </tr>
            </table>
        </div>
        <div id="adopt-review-btn">
            <button id="return" type="button" onclick="location.href = '${context}/adoptRevList.bo'">목록으로 돌아가기</button>
            <button type="submit" id="save">작성하기</button>
        </div>
    </form>
</div>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>
