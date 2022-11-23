<%@ page import="tk.newsoulmate.domain.vo.Reply" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="review-content">
        <table class="table">
            <tr><p>${b.boardTitle}</p></tr>
            <tr>
                <td>입양일</td>
                <td>
                <fmt:formatDate value="${b.issueDate}" type="both" pattern="yyyy년 MM월dd 일"/>
                </td>
                <td colspan="2"><button type="button" class="bi bi-exclamation-triangle" data-toggle="modal" data-target="#reportModal" data-kind="board" data-ref="${b.boardNo}"></button>
                    <c:if test="${!empty loginUser and loginUser.memberNo eq b.memberNo}">
                        <button type="button" id="deleteBoard"  class="bi bi-trash"></button>
                        <button type="button" id="updateBoard" class="bi bi-pencil-square">수정하기</button>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>작성자</td>
                <td>${b.nickName}</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
            <td colspan="6">${b.boardContent} </td>
            </tr>
            <c:forEach var="r" items="${rList}">
                <tr>
                    <td>${r.replyWriter}</td>
                    <td>${r.replyContent}</td>
                    <td>
                        <button type="button" class="bi bi-exclamation-diamond" data-toggle="modal" data-target="#reportModal" data-type="reply" data-refNo="${r.replyNo}"></button>
                        <c:if test="${!empty loginUser and loginUser.memberNo eq r.memberNo}">
                        <button class="bi bi-x-circle-fill replyDelete" ref="${r.replyNo}" id="replyDelete"></button>
                        </c:if>
                    </td>
                    <%--댓글 작성자인경우 삭제 --%>
                </tr>
            </c:forEach>
            <c:choose>
                <c:when test="${!empty loginUser}">
                    <tr><td>댓글작성</td>  <td colspan="3"><input type="text" id="replyInput"></td> <td><button type="button" id="replySubmit">작성</button></td></tr>
                </c:when>
                <c:otherwise>
                <tr>
                    <td colspan="5">로그인한 회원만 작성할수 있습니다.</td>
                </tr>
                </c:otherwise>
            </c:choose>


        </table>
        </div>
        <%@ include file="/views/template/report.jsp"%>
    </main>

    <footer>
        <%@ include file="/views/template/footer.jsp"%>
    </footer>
    <c:if test="${!empty loginUser}">
        <script>
            $('#replySubmit').on('click',submitReply);
            function submitReply(){
                let replyJson=JSON.stringify({
                    'memberNo':'${loginUser.memberNo}',
                    'boardNo':'${b.boardNo}',
                    'replyContent':$('#replyInput').val()
                });
                $.ajax({
                    url :'${context}/replyInsert.bo',
                    type:'post',
                    data:{"reply":replyJson},
                    success:(result)=>{
                        if(result>0){
                            alert('댓글등록성공');
                            location.reload();
                        }else{
                            alert('댓글등록실패',result)
                        }
                    },
                    error:(result)=>{
                        console.log(result)
                    }
                });
            }
            $('.replyDelete').click((e)=>{
                let rno=  $(e.target).attr('ref');
                console.log(rno);
                console.log(e.target);
                console.log(e.target);
                console.log($(e.target));
                if(confirm('정말삭제하시겠습니까?')){
                    $.ajax({
                        url:"replyDelete.bo",
                        type:'post',
                        data:{rno:rno},
                        success:(result)=>{
                            if(result>0){
                                alert("댓 삭 성")
                                location.reload()
                            }else{
                                alert("댓글삭제실패")
                            }
                        },
                        error:(e)=>{console.log(e)}

                    })


                }
            })
        <c:if test="${loginUser.memberNo eq b.memberNo}">
            $('#deleteBoard').click(()=>{
                if(confirm('정말삭제하시겠습니까?')){
                    location.href='${context}/adoptRevDelete?bno=${b.boardNo}';
                }
            });
            $('#updateBoard').click(()=>{
                location.href='${context}/adoptRevUpdate?bno=${b.boardNo}';
            });
        </c:if>
    </script>
    </c:if>
</body>
</html>