let oEditors = [];
$(document).ready(function () {
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "boardContent",
        sSkinURI: context+"/smarteditor2/SmartEditor2Skin.html",
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
        boardTitle:$('#boardTitle').val(),
        boardContent:$('#boardContent').val(),
        issueDate:$('#adoptDate').val(),
        boardType:'ADOPT'
    };
    let msg=vaildate(board);
    if(msg==='Y'){
        $.ajax({
            url: 'adoptRevInsert',
            type: 'post',
            data: {board:JSON.stringify(board)},
            success:(result)=>{
                alert(result.msg)
                if(result.result==1) {
                    location.href=context+'/adoptRevDetail?bno='+result.bno;
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
