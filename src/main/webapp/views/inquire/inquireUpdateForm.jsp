<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-15
  Time: 오후 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>
<%@ page import="java.util.ArrayList" %>

<%
  ArrayList<Category> list = (ArrayList<Category>) request.getAttribute("list");
  Board b = (Board) request.getAttribute("b");
  Attachment at = (Attachment) request.getAttribute("at");
  Category ca = (Category) request.getAttribute("ca");
%>
<html>
<head>
    <title>1:1문의 수정하기</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>
    <div class="outer">
      <br>
      <h2 style="text-align: center;">1:1문의 수정</h2>
      <br>
      <hr>
      <br>

      <form action="<%=request.getContextPath()%>/inquireUpdate.bo" id="update-form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="bno" value="<%=b.getBoardNo()%>">

        <table align="center">
          <tr>
            <th width="100">카테고리</th>
            <td width="500">
              <select name="category">
                <% for(Category c : list) { %>
                <option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>

                <%} %>

              </select>

              <script>
                $(function(){
                  $("#update-form option").each(function(){
                    // 현재 반복 진행중인 option태그의 text값(공통,게임,낚시)과 db에서 가져온 categoryname값이
                    // 일치하는 경우 선택되도록
                    if($(this).text() == "<%=ca.getCategoryName()%>"){
                      // 일치하는경우에만 option태그를 선택상태로 변경
                      $(this).attr("selected", true);
                    }



                  });
                });
              </script>

            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td><input type="text" name="title" required value="<%=b.getBoardTitle() %>"></td>

          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea name="content" id="" cols="30" rows="10" required ><%=b.getBoardContent()%></textarea>
            </td>
          </tr>
          <tr>
            <th>첨부파일</th>
            <td>
              <% if(at != null) { %>
              <%= at.getOriginName() %>
              <!-- 원본파일의 파일번호, 수정명을 hidden으로 넘길것. -->
              <input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
              <input type="hidden" name="originFileName" value="<%= at.getChangeName() %>">

              <% } %>

              <input type="file" name="upfile">
            </td>
          </tr>
        </table>
        <br>

        <div align="center">
          <button>수정하기</button>
        </div>

      </form>
    </div>










    <%@include file="/views/template/footer.jsp"%>
</body>
</html>
