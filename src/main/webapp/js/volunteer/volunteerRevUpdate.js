function vaildate(board){
    let msg='Y';
    if(board.boardTitle.length==0||board.boardContent.length==0||board.issueDate==0){
        return '모든 내용을 기입해주세요';
    }
    if(board.fileCount==0){
        return '첨부파일이 한개 이상 필요합니다.'
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
            url: 'volunteer/update',
            type: 'post',
            data: {board:JSON.stringify(board)},
            success:(result)=>{
                alert(result.msg)
                if(result.result==1) {
                    location.href='${context}/volunteerRevDetail?bno='+result.bno;
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

