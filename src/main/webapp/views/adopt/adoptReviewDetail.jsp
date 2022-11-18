<%@ page import="tk.newsoulmate.domain.vo.Reply" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
    <header><%@ include file="/views/template/menubar.jsp"%></header>
    <main>
        <p>${b.boardTitle}</p>
        <p>${b.issueDate}</p>
        <button type="button" onclick="report('board',${b.boardNo})" data-toggle="modal" data-target="#reportModal">신고</button>
        <p>${b.boardContent} </p>
        <table>
            <c:forEach var="r" items="${rList}">
                ${r.replyWriter}${r.replyWriter}${r.replycontent}
                <button type="button" onclick="report('reply',${r.replyNo})" data-toggle="modal" data-target="#reportModal">신고</button>
                <button onclick="deleteReply();" >X</button>
            </c:forEach>
        </table>
        <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="rpBoardNo" value="">
                    <input type="hidden" name="rpReplyNo" value="">
                    <select name="category" id="rpCategory">
                        <option value="1">광고</option>
                        <option value="2">도배</option>
                        <option value="3">음란물</option>
                        <option value="4">개인정보침해</option>
                        <option value="5">저작권침해</option>
                        <option value="6">기타</option>
                    </select><br>
                    <textarea name="content" id="reportContent" cols="30" rows="10"></textarea>
                    <button onclick="
                    reportSubmit()"></button>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="resetReport()" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
    </main>

    <footer><%@ include file="/views/template/footer.jsp"%></footer>
    <script>
        /**
         * 신고창
         * @param type
         * @param refNo
         */
        function resetReport(){
            $("#reportContent").val("");
            $("#rpReplyNo").val("");
            $("#rpBoardNo").val("");
        }
        function report(type,refNo){

        }

        function reportSubmit() {
            let reportContent = {
                "categoryNo":$("#rpCategory").val(),
                "boardNo":$("#rpBoardNo").val(),
                "replyNo":$("#rpReplyNo").val(),
                "reportContent":$("reportContent").val()
            };
            $.ajax({
                url : '${context}/report',
                type : 'post',
                data : reportContent,
                success:function (result){
                    console.log("성공스")
                },
                error:(result)=>{


                }
                }
            )
        }
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        })
    </script>
</body>
</html>