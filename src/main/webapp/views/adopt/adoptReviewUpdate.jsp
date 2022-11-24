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
    <link href="<%=request.getContextPath()%>/css/board/adoptReviewUpdate.css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/HuskyEZCreator.js" charset="UTF-8"></script>
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
    <div id="review-update-title">입양후기 수정하기</div>
    <form action="${context}/adopt/update" method="post" id="adoptReview">
        <div id="review-update-form">
            <table id="review-update-table">
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="boardTitle" name="boardTitle" value="${b.boardContent}"></td>
                </tr>
                <tr>
                    <th>입양날짜</th>
                    <td><input type="Date" id="adoptDate" name="adoptDate" value="${b.issueDate}"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td> <textarea name="boardContent" id="boardContent" value="${b.boardContent}"></textarea></td>
                </tr>
            </table>
        </div>
        <div id="adopt-update-btn">
            <button type="button" id="return" onclick="location.href = '${context}/adoptRevList.bo'">목록으로 돌아가기</button>
            <button type="submit" id="save">수정하기</button>
        </div>
    </form>
</div>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>
