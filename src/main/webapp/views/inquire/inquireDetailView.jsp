<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-11
  Time: 오전 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>

<html>
<head>
    <title>문의내역 상세보기</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>

    <div class="outer">
        <br>
        <h2 style="text-align:center;">1:1문의</h2>
        <br>
        <hr>
        <br>

        <table id="detail-area" align="center" style="border: 1px solid black;">

            <tr>
                <th width="100">카테고리</th>
                <td width="70">${b.categoryName}</td>
                <th width="100">제목</th>
                <td width="350">${b.boardTitle}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${b.memberName}</td>
                <th>작성일</th>
                <td>${b.createDate}</td>
            </tr>
            <tr>
                <th>문의내용</th>
                <td colspan="3">
                    <p style="height:200px;">${b.boardContent}</p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <c:if test="${empty at}">
                    <!-- 첨부파일이 없는경우 -->
                    첨부파일된 파일 없음.
                    </c:if>
                    <!-- 첨부파일이 있는경우 -->
                    <c:if test="${!empty at}">
                    <a href="${at.filePath}/${at.changeName}"
                       download="${at.originName}">
                        ${at.originName}
                    </a>
                    </c:if>
                </td>
            </tr>

            <c:forEach var="r" items="${rList}">
                <tr>
                    <td>${r.replyWriter}</td>
                    <td>${r.replyContent}</td>
                    <td>
                        <button type="button" class="bi bi-exclamation-diamond" data-toggle="modal" data-target="#reportModal" data-type="reply" data-refNo="${r.replyNo}"></button>
                        <button class="bi bi-x-circle-fill replyDelete" refNo="${r.replyNo}"></button>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${loginUser.memberGrade.SITE_MANAGER}">

                <tr>
                    <th>답변작성</th>
                    <td>
                        <textarea id="replyInput" rows="3" cols="50" style="resize: none;"></textarea>
                    </td>
                    <td><button id="replySubmit">답변등록</button></td>
                </tr>

            </c:if>
        </table>

        <br>

        <div align="center">
            <a href="${context}/inquire" class="btn btn-secondary btn-sm">목록</a>
            <%-- if문 가능한건지 체크 확인해야함 --%>
            <%-- 현재 로그인한 사용자가 해당 글을 작성한 작성자일 경우에만 보여진다. --%>
            <a id=boardUpdate href="${context}/inquireUpdateForm.bo?bno=${b.boardNo}" class="btn btn-secondary btn-sm">수정</a>
            <a id=boardDelete class="btn btn-danger btn-sm">삭제</a>
        </div>
    </div>

        <script>
            <c:if test="${loginUser.memberGrade.SITE_MANAGER}">
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
                            alert('답변등록성공');
                            location.reload();
                        }else{
                            alert('답변등록실패',result)
                        }
                    },
                    error:(result)=>{
                        console.log(result)
                    }
                });
            }
            </c:if>
            $('.replyDelete').click((e)=>{
              let rno=  $(e.target).attr('refNo');
                console.log(rno);
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
            $('#boardDelete').click(()=>{
                if(confirm('정말삭제하시겠습니까?')){
                    location.href='${context}/inquireDelete.bo?bno=${b.boardNo}'
                }
            })

        </script>

    <%@include file="/views/template/footer.jsp"%>
</body>
</html>
