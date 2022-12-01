<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>봉사후기 작성하기</title>
    <%@include file="/views/template/styleTemplate.jsp"%>

    <script type="text/javascript" src="<%=request.getContextPath() %>/smarteditor2/js/HuskyEZCreator.js" charset="UTF-8"></script>
    <link href="<%=request.getContextPath()%>/css/board/volunteerReviewEnroll.css" rel="stylesheet">
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div id="content">
    <div id="review-enroll-title">봉사후기 작성하기</div>
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
        <div id="volunteer-review-btn">
            <button id="return" type="button" onclick="location.href = '${context}/adoptRevList'">목록으로 돌아가기</button>
            <button type="button" id="save">작성하기</button>
        </div>

</div>
<script>
    let oEditors = [];
    $(document).ready(function () {
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "boardContent",
            sSkinURI:"${context}/smarteditor2/SmartEditor2Skin.html",
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
        })
        function vaildate(board){
            let msg='Y';
            if(board.boardTitle.length==0||board.boardContent.length==0||board.issueDate==0){
                return '모든 내용을 기입해주세요';
            }
            if(board.fileCount==0){
                return '첨부파일이 한개이상 필요합니다.'
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
                boardTitle:$('#boardTitle').val(),
                boardContent:$('#boardContent').val(),
                issueDate:$('#volunteerDate').val(),
                boardType:'VOLUNTEER',
                fileCount:$('#boardContent').val().split("img src").length
            };
            let msg=vaildate(board);
            if(msg==='Y'){
                $.ajax({
                    url: 'volunteerRevInsert',
                    type: 'post',
                    data: {board:JSON.stringify(board)},
                    success:(result)=>{
                        alert(result.msg)
                        if(result.result==1) {
                            location.href='volunteerRevDetail?bno='+result.bno;
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

    });
</script>
</body>
</html>
