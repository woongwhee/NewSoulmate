
function insertReply() {
    $.ajax({
        url : "rInsert.bo",
        data : {
            content : $("#replyContent").val(),
            bno : this.bno
        },
        type : "post",
        success : function(result) {
            if(result > 0){
                replyList();
                $("#replyContent").val("");
            }
        }, error : function() {
            alert("댓글작성실패");
        }
    })
}

function replyList(){
    $.ajax({
        url : "rList.bo",
        data : {bno: this.bno},
        success : (list) => {

            let result = "";
            for(let i of list){
                result += "<tr>"
                    +"<td>"+i.memberNo+"</td>"
                    +"<td>"+i.replyNo+"</td>"
                    +"<td>"+i.replyDate+"</td>"
                    +"<td>"+i.replyContent+"</td>"
                    + "</tr>";
            }
            $("#reply-area thead").html(result);
        },
        error : function() {
            alert("댓글조회실패");
        }
    });

}