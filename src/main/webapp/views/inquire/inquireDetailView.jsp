<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-11
  Time: 오전 2:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>
<% Attachment at= (Attachment) request.getAttribute("at");
    Board b=(Board) request.getAttribute("b");
    Member loginUser = (Member)session.getAttribute("loginUser");
%>
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
                    <p style="height:200px;"><%= b.getBoardContent() %></p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <% if(at == null) { %>
                    <!-- 첨부파일이 없는경우 -->
                    첨부파일된 파일 없음.
                    <% } else { %>
                    <!-- 첨부파일이 있는경우 -->
                    <a href="${at.filePath}/${at.changeName}"
                       download="${at.originName}">
                        ${at.originName}
                    </a>
                    <% } %>
                </td>
            </tr>
<%--            <c:choose >--%>
<%--                <c:when test="${b.resultStatus eq 'Y'}">--%>

<%--                </c:when>--%>
<%--                <c:when test="${!empty loginUser AND (loginUser.memberGreade eq MemberGreade.SITE_MANAGER)}">--%>
<%--                    <tr>--%>
<%--                        <th>답변작성</th>--%>
<%--                        <td>--%>
<%--                            <textarea id="replyInput" rows="3" cols="50" style="resize: none;"></textarea>--%>
<%--                        </td>--%>
<%--                        <td><button id="replySubmit">답변등록</button></td>--%>

<%--                    </tr>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <tr>--%>
<%--                        <th>답변</th>--%>
<%--                        <td><%= b.getResultStatus()%></td>--%>
<%--                    </tr>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>

            <% if(b.getResultStatus().equals("Y")) {%>

            <% } else if (loginUser != null && loginUser.getMemberGrade().isSITE_MANAGER()) { %>
                    <tr>
                        <th>답변작성</th>
                        <td>
                            <textarea id="replyInput" rows="3" cols="50" style="resize: none;"></textarea>
                        </td>
                        <td><button id="replySubmit">답변등록</button></td>

                    </tr>

            <% } else { %>
                    <tr>
                        <th>답변</th>
                        <td><%= b.getResultStatus()%></td>
                    </tr>
            <% } %>

        </table>

        <br>

        <div align="center">
            <a href="<%=request.getContextPath() %>/inquire" class="btn btn-secondary btn-sm">목록</a>

            <% if(loginUser != null && loginUser.getMemberNo() == b.getMemberNo()) { %>
            <%-- if문 가능한건지 체크 확인해야함 --%>
            <%-- 현재 로그인한 사용자가 해당 글을 작성한 작성자일 경우에만 보여진다. --%>
            <a href="<%=request.getContextPath() %>/inquireUpdateForm.bo?bno=<%=b.getBoardNo() %>" class="btn btn-secondary btn-sm">수정</a>
            <a href="<%=request.getContextPath() %>/inquireDelete.bo?bno=<%=b.getBoardNo() %>" class="btn btn-danger btn-sm">삭제</a>

            <% } %>

        </div>

    </div>
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
                        }else{
                            alert('댓글등록실패',result)
                        }
                    },
                    error:(result)=>{
                        console.log(result)
                    }
                });
            }
            <c:if test="${loginUser.memberNo eq b.memberNo}">
            $('#deleteBoard').click(()=>{
                if(confirm('정말삭제하시겠습니까?')){
                    location.href='${context}/adoptRevDelete?bno=${b.boardNo}'
                }
            })
            </c:if>
        </script>
    </c:if>
    <%@include file="/views/template/footer.jsp"%>
</body>
</html>
