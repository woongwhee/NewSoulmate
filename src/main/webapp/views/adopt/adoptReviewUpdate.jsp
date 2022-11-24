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

<form action="${context}/adopt/update" method="post" id="adoptReview">
    <input type="hidden" name="boardNo" value="${b.boardNo}">
   <table width="100%">
       <tr>
           <th>제목</th>
           <td><input type="text" id="boardTitle" name="boardTitle" style="width: 680px" value="${b.boardTitle}"></td>
       </tr>
       <tr>
           <th>입양날짜</th>
           <td><input type="Date" id="adoptDate" name="adoptDate" style="width: 680px" value="${b.issueDate}"></td>
       </tr>
       <tr>
           <th>내용</th>
           <td> <textarea name="boardContent" id="boardContent" style="width:680px; height:350px;" value="">${b.boardContent}</textarea></td>
       </tr>
       <tr>
           <td colspan="2">
               <button type="submit" id="save">수정하기</button>
               <button type="button" onclick="location.href = '${context}/adoptRevList.bo'">목록으로 돌아가기</button>
           </td>
       </tr>
   </table>
    <script>
    </script>
</form>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>
