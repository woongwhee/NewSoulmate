<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>
<html>
<head>
    <title>문의내역 상세보기</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/inquire/inquireFQ.css" rel="stylesheet">
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div class="outer">
    <br>
    <div class="topTextQna">1:1 문의</div>
    <br>
    <hr>
    <br>
    <form>
        <table align="center" id="textQnaWriting">
            <tr>
                <th width="100">카테고리</th>
                <td width="500">${b.categoryName}</td>
            </tr>
            <tr>
                <th id="tableTh1" width="100">제목</th>
                <td id = "titleQna">${b.boardTitle}</td>
            </tr>
            <tr>
                <th id="tableTh4">작성자</th>
                <td>${b.nickName}</td>
            </tr>
            <tr>
                <th id="tableTh5">작성일</th>
                <td>${b.createDate}</td>
            </tr>
            <tr>
                <th id="tableTh2">문의내용</th>
                <td id= "contentQna">${b.boardContent}</td>
            </tr>
            <tr>
                <th id="tableTh3">첨부파일</th>
                <td id="fileQna">
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
                        <button class="bi bi-x-circle-fill" id="replyDelete"></button>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty rList}">
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
            <a id=boardDelete href="${context}/inquireDelete.bo?bno=${b.boardNo}" class="btn btn-danger btn-sm">삭제</a>
        </div>

    </form>
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
    $('#replyDelete').click(()=>{
        if(confirm('정말삭제하시겠습니까?')){
            location.href='${context}/replyDelete.bo?bno=${b.boardNo}'
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
