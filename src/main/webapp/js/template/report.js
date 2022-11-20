
    function showReport(e){
    resetReport();
    let button = $(e.relatedTarget) // Button that triggered the modal
    let kind = button.data('kind') ;
    let refNo = button.data('ref');
    $("#refNo").val(refNo);
    $("#refType").val(kind);
}

    function resetReport(){
    $("#reportContent").val("");
    $("#refType").val("");
    $("#refNo").val("");
    }

    function submitReport() {
        let reportJson=JSON.stringify({
            "categoryNo":$("#rpCategory").val(),
            "refType": $("#refType").val(),
            "refNo": $("#refNo").val(),
            "reportContent":$("#reportContent").val()
        });
        console.log(reportJson);
        $.ajax({
            url : '/NewSoulmate/report',
            type : 'post',
            data : {report:reportJson},
            success:function (result){
            if(result==0){
                alert('신고접수실패')
            }else{
                console.log("result:"+result);
                alert('신고가 성공적으로 접수되었습니다.')
            }
            resetReport();
            $('#reportModal').modal('hide')
        },
        error:(result)=>{
        console.log(result);
        alert(result);
        }
        });
    }
    $('#reportModal').on('shown.bs.modal',showReport);
    $('#submitModal').on('click',submitReport);
    $('#reportModal').on('hide.bs.modal',resetReport);