<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/07
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="<%=request.getContextPath()%>/css/common/report.css" rel="stylesheet">
<%--사용시 카테고리 리스트를 가저올것!--%>
<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-title" id="exampleModalLabel">신고하기</div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" id="refType">
                <input type="hidden" id="refNo" >
                    <select name="category" id="rpCategory">
                        <c:forEach items="${cList}" var="c"><option value="${c.categoryNo}">${c.categoryName}</option></c:forEach>
                    </select>
                <br>
                <textarea name="content" id="reportContent"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" id="closeModal" data-dismiss="modal">닫기</button>
                <button type="button" id="submitModal" >신고하기</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/template/report.js" rel="script"></script>