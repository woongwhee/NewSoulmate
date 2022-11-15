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
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/HuskyEZCreator.js"
            charset="UTF-8"></script>
</head>
<body>
<script>

    let oEditors = [];
    $(document).ready(function () {

        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "adContent",
            sSkinURI: "<%=request.getContextPath() %>/smarteditor2/SmartEditor2Skin.html",
            fCreator: "createSEditor2",
            htParams: {
                bUseToolbar: true,
                bUseVerticalResizer: true,
                bUseModeChanger: true,
            },
        });
        $("#save").click(function () {
            oEditors.getById["adContent"].exec("UPDATE_CONTENTS_FIELD", []);
                $("#adoptReview").submit();
        });
    });
</script>

<form action="<%=request.getContextPath()%>/views/adopt/adoptReviewDetail.jsp" method="post" id="adoptReview">
   <table width="100%">
       <tr>
           <td>제목</td>
           <td><input type="text" id="adTitle" name="adTitle" style="width: 680px"></td>
       </tr>
       <tr>
           <td>내용</td>
           <td> <textarea name="adContent" id="adContent" style="width:680px; height:350px;"></textarea></td>
       </tr>
       <tr>
           <td colspan="2">
               <button type="submit" id="save">작성하기</button>
               <button type="button" onclick="location.href = '<%=request.getContextPath()%>/adoptReList.bo'">목록으로 돌아가기</button>
           </td>
       </tr>
   </table>
</form>

</body>
</html>
