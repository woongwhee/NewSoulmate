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
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/HuskyEZCreator.js?1234" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/smarteditor2.js?1234" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/SE2M_Configuration.js?1234" charset="UTF-8"></script>

    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/SE2BasicCreator.js?1234" charset="UTF-8"></script>
</head>
<body>
<script>

    $(document).ready(function() {
        var oEditors = [];

        nhn.husky.EZCreator.createInIFrame({
            oAppRef : oEditors,
            elPlaceHolder : "adReviewContent",
            sSkinURI : "<%=request.getContextPath() %>/smarteditor2/SmartEditor2Skin.html",
            fCreator : "createSEditor2",
            htParams : {
                bUseToolbar : true,
                bUseVerticalResizer : true,
                bUseModeChanger : true,
            }
        });
        $("#formBtn").click(function(){
            oEditors.getById["adReviewContent"].exec("UPDATE_CONTENTS_FIELD", []);
            if(validation()) {
                $("#adoptReview").submit();
            }
            //}
        })
    });

    function validation(){
        var contents = $.trim(oEditors[0].getContents());
        if(contents === ''){
            alert("내용을 입력하세요.");
            oEditors.getById['adoptReview'].exec('FOCUS');
            return false;
        }
        return true;
    }
    </script>

    <form action="<%=request.getContextPath() %>/adoptReInsert.bo" method="post" id="adoptReview">
        <table>
            <tr>
                <td>제목<input type="text" name="adReviewTitle" id="adReviewTitle"></td>
            </tr>
            <tr>
                <td>내용<textarea name="adReviewContent" id="adReviewContent"></textarea></td>
            </tr>
        </table>
        <button type="submit" id="formBtn" >작성하기</button>
        <button type="button" onclick = "location.href = '<%=request.getContextPath()%>/adoptReList.bo'">목록으로 돌아가기</button>
    </form>

</body>
</html>
