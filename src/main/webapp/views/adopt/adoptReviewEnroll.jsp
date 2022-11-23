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

<form action="${context}/adoptRevInsert" method="post" id="adoptReview">
   <table width="100%">
       <tr>
           <td>제목</td>
           <td><input type="text" id="boardTitle" name="boardTitle" style="width: 680px"></td>
       </tr>
       <tr>
           <td>입양날짜</td>
           <td><input type="Date" id="adoptDate" name="adoptDate" style="width: 680px"></td>
       </tr>
       <tr>
           <td>내용</td>
           <td> <textarea name="boardContent" id="boardContent" style="width:680px; height:350px;"></textarea></td>
       </tr>
       <tr>
           <td colspan="2">
               <button type="submit" id="save">작성하기</button>
               <button type="button" onclick="location.href = '${context}/adoptRevList'">목록으로 돌아가기</button>
           </td>
       </tr>
   </table>
</form>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>
