<%--
 Created by IntelliJ IDEA.
 User: 상엽
 Date: 2022-11-11
 Time: 오전 2:27
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.Category" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<Category> list = (ArrayList<Category>) request.getAttribute("list");
%>


<html>
<head>
    <title>1:1 문의 등록페이지</title>
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
        <form action="${context}/inquireInsert.bo" method="post" enctype="multipart/form-data">
            <!-- 카테고리, 제목, 내용, 첨부파일을 입력받고, 작성자의 회원번호는 hidden으로 넘기기. -->
<%--            <input type="hidden" name="userNo" value="<%=loginUser.getUserNo() %>">--%>
            <input type="hidden" name="memberNo" value="${loginUser.memberNo}">
            <table align="center" id="textQnaWriting">
                <tr>
                    <th width="100">카테고리 <span>*</span></th>
                    <td width="500">
                    <select name="categoryNo" id = "categoryQna">

                        <% for(Category c : list) { %>
                        <option value="<%=c.getCategoryNo() %>"><%= c.getCategoryName() %></option>

                        <%} %>

                    </select>
                    </td>
                </tr>

                <tr>
                    <th id="tableTh1">제목 <span>*</span></th>
                    <td><input type="text" name="boardTitle" id = "titleQna" required></td>

                </tr>
                <tr>
                    <th id="tableTh2">문의내용 <span>*</span></th>
                    <td>
                        <textarea name="boardContent" id="textareaQna" cols="30" rows="10" style="resize: none" id= "contentQna" required></textarea>
                    </td>
                </tr>
                <tr>
                    <th id="tableTh3">첨부파일</th>
                    <td><input type="file" name="upfile" id="fileQna"></td>
                </tr>
            </table>

            <br>

            <div align="right" id="buttonBoxQna">
<%--                <button type="reset" class="btn btn-secondary btn-sm">취소하기</button>--%>
                <a href="${context}/inquire" class="btn btn-secondary btn-sm" id="cancelButton">취소하기</a>
                <button type="submit" class="btn btn-secondary btn-sm">작성하기</button>

            </div>
        </form>

    </div>
    <%@include file="/views/template/footer.jsp"%>
</body>
</html>
