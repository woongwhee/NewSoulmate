<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/board/adoptReviewUpdate.css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/HuskyEZCreator.js" charset="UTF-8"></script>
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>

<div id="content">
    <div id="review-update-title">입양후기 수정하기</div>
        <div id="review-update-form">
            <table id="review-update-table">
                <input type="hidden" id="boardNo" name="boardNo" value="${b.boardNo}">
                <tr>
                    <th>제목</th>
                    <td><input type="text" id="boardTitle" name="boardTitle" value="${b.boardTitle}"></td>
                </tr>
                <tr>
                    <th>입양날짜</th>
                    <td><input type="Date" id="adoptDate" name="adoptDate" value="${b.issueDate}"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td> <textarea name="boardContent" id="boardContent" >${b.boardContent}</textarea></td>
                </tr>
            </table>
        </div>
        <div id="adopt-update-btn">
            <button type="button" id="return" onclick="location.href = '${context}/adoptRevList'">목록으로 돌아가기</button>
            <button type="button" id="save">수정하기</button>
        </div>
</div>
<%@include file="/views/template/footer.jsp"%>
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
            submitBoard();

            });
    });
    function vaildate(board){
        let msg='Y';
        if(board.boardTitle.length==0||board.boardContent.length==0||board.issueDate==0){
            return '모든 내용을 기입해주세요';
        }
        let toDay=new Date().getTime();
        let dateArr=board.issueDate.split('-');
        let inputDay=new Date(dateArr[0],parseInt(dateArr[1])-1,dateArr[2]).getTime();
        if(toDay<inputDay){
            return '유효한 날짜를 입력해주세요';
        }
        return msg;
    }
    function submitBoard(){
        let board={
            boardNo:$('#boardNo').val(),
            boardTitle:$('#boardTitle').val(),
            boardContent:$('#boardContent').val(),
            issueDate:$('#adoptDate').val(),
            fileCount:$('#boardContent').val().split("img src").length
        };
        let msg=vaildate(board);
        if(msg==='Y'){
            $.ajax({
                url: 'adopt/update',
                type: 'post',
                data: {board:JSON.stringify(board)},
                success:(result)=>{
                    alert(result.msg)
                    if(result.result==1) {
                        location.href='${context}/adoptRevDetail?bno='+result.bno;
                    }
                },
                error:(error)=>{
                    console.log(error);
                }
            })
        }else{
            alert(msg)
        }
    }

</script>

</body>
</html>
