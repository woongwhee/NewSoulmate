<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-15
  Time: 오후 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>1:1문의 수정하기</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
  <link href="<%=request.getContextPath()%>/css/inquire/inquireFQ.css" rel="stylesheet">

</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>
    <div class="outer">
      <br>
      <h2 style="text-align: center;">1:1문의 수정</h2>
      <br>
      <hr>
      <br>

      <form action="${context}/inquire/update" id="update-form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="boardNo" value="${b.boardNo}">
        <table align="center"  id="textQnaWriting">
          <tr>
            <th width="100">카테고리 <span>*</span></th>
            <td width="500">
              <select name="categoryNo" id = "categoryQna">
                <c:forEach items="${list}" var="c">
                <option value="${c.categoryNo}">${c.categoryName}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td><input type="text" name="boardTitle"  id = "titleQna" required value="${b.boardTitle}"></td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea name="boardContent"  id="textareaQna"  cols="30" rows="10" required >${b.boardContent}</textarea>
            </td>
          </tr>
          <tr>
            <th>첨부파일</th>
            <td>

              <c:if test="${!empty at}">
              <div id="originFile" name="originFile">${at.originName}
                <input type="hidden" value="none" id="deleteFile" name="deleteFile">
                <button type="button" id="deletebtn" onclick="deleteFiles()"
                        class="btn btn-danger btn-sm" style="border-radius:25px">X</button>
              </div>
              <input type="hidden" name="originFileNo" value="${at.fileNo}">
              <input type="hidden" name="originFileName" value="${at.originName}">
              <input type="file" id="fileQna" name="upfile" accept=".gif, .jpg, .png" onchange="checkSize(this)" style="display: none">
              </c:if>
              <c:if test="${empty at}">
              <input type="hidden" name="deleteFile" id="deleteFile" value="none">
              <input type="file" id="fileQna" name="upfile" accept=".gif, .jpg, .png" onchange="checkSize(this)">
              </c:if>
            </td>
          </tr>
        </table>
        <br>

        <div align="center">
          <a href="${context}/inquire" id="cancelButton" class="btn btn-secondary btn-sm">취소하기</a>
          <button type="submit" class="btn btn-secondary btn-sm">작성하기</button>
        </div>

      </form>
    </div>


    <%@include file="/views/template/footer.jsp"%>
    <script>
      $(function(){
        $("#update-form option").each(function(){
          // 현재 반복 진행중인 option태그의 text값과 db에서 가져온 categoryname값이
          // 일치하는 경우 선택되도록
          if($(this).text() == "${b.categoryName}"){
            // 일치하는경우에만 option태그를 선택상태로 변경
            $(this).attr("selected", true);
          }

        });
      });
      function deleteFiles(){
        $('#originFile').css('display',"none");
        $('#deleteFile').val('delete');
        $("#deletebtn").css('display','none');
        $("#fileQna").css('display','block');
      }
      function checkSize(input) {
        if (input.files && input.files[0].size > (20 * 1024 * 1024)) {
          alert("파일 사이즈가 20mb 를 넘습니다.");
          input.value = null;
        }
      }
    </script>
</body>

</html>
