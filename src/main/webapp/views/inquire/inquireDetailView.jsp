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
    <table align="center" id="textQnaWriting">
        <colgroup>
            <col width="100">
            <col width="700">
        </colgroup>
        <tr>
            <th>카테고리</th>
            <td>${b.categoryName}</td>
        </tr>
        <tr>
            <th id="tableTh1">제목</th>
            <td id = "titleQna" class="tableTdAll">${b.boardTitle}</td>
        </tr>
        <tr>
            <th id="tableTh4">작성자</th>
            <td class="tableTdAll">${b.nickName}</td>
        </tr>
        <tr>
            <th id="tableTh5">작성일</th>
            <td class="tableTdAll">${b.createDate}</td>
        </tr>
        <tr>
            <th id="tableTh2">문의내용</th>
            <td class="tableTdAll">
                <p><c:out value="${b.boardContent}" escapeXml="true"/> </p>
            </td>
        </tr>
        <tr>
            <th id="tableTh3">첨부파일</th>
            <td id="fileQna" class="tableTdAll">
                <c:if test="${empty at}">
                    <!-- 첨부파일이 없는경우 -->
                    첨부파일된 파일 없음
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
                <td id="replyWriterQna">${r.replyWriter}</td>
                <td id="replyContentQna">${r.replyContent}</td>
                <td>
                        <%--                        <button type="button" class="bi bi-exclamation-diamond" data-toggle="modal" data-target="#reportModal" data-type="reply" data-refNo="${r.replyNo}"></button>--%>
                    <button type="button" class="bi bi-trash" ref="${r.replyNo}" id="replyDelete"></button>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty rList and loginUser.memberGrade.SITE_MANAGER}">
            <tr>
                <th>답변작성</th>
                <td>
                    <textarea id="replyInput" rows="3" cols="50" style="resize: none;"></textarea>
                </td>
            </tr>
            <tr>
                <td><button id="replySubmit">답변등록</button></td>
            </tr>

        </c:if>
    </table>

    <br>

        <div align="center">

            <a id="boardList" href="inquire" class="btn btn-secondary btn-sm">목록</a>
        <c:if test="${b.memberNo eq loginUser.memberNo}">
        <a id=boardUpdate  href="inquireUpdateForm?bno=${b.boardNo}" class="btn btn-secondary btn-sm">수정</a>
            <a id=boardDelete class="btn btn-danger btn-sm">삭제</a>
        </c:if>
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
    $('#replyDelete').click((e)=>{
        let rno=  $(e.target).attr('refNo');
        console.log(rno);
        if(confirm('정말삭제하시겠습니까?')){
            $.ajax({
                url:"replyDelete",
                type:'post',
                data:{rno:rno},
                success:(result)=>{
                    if(result>0){
                        alert("댓글 삭제 성공")
                        location.reload()
                    }else{
                        alert("댓글 삭제 실패")
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
