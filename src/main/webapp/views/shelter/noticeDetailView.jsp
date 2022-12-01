<%@ page import="tk.newsoulmate.domain.vo.Notice" %><%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/14
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상세보기 - 환승주인</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
<%
    Notice n=(Notice)request.getAttribute("n");
%>
    <link href="<%=request.getContextPath()%>/css/shelter/noticeDetailView.css" rel="stylesheet">
</head>
<body>
<header><%@include file="/views/template/menubar.jsp"%></header>
<div id="content">
    <div id="animal-info">
        <img id="animalImg_1" src="${n.popfile}" alt="">
        <table id="infoTable">
            <tr>
                <th>공고번호</th>
                <td>${n.desertionNo}</td>
                <th>보호소전화번호</th>
                <td>${n.officetel}</td>
            </tr>
            <tr>
                <th>공고시작일</th>
                <td>${n.noticeSdt}</td>
                <th>공고종료일</th>
                <td>${n.noticeEdt}</td>
            </tr>
            <tr>
                <th>색</th>
                <td>${n.colorCd}</td>
                <th>나이</th>
                <td>${n.age}</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${n.sexCd}</td>
                <th>중성화여부</th>
                <td>${n.neuterYn}</td>
            </tr>
            <tr>
                <th>보호소 이름</th>
                <td>${n.careNm}</td>
                <th>보호소 전화번호</th>
                <td>${n.careTel}</td>
            </tr>
            <tr>
                <th>보호소 주소</th>
                <td colspan="3">${n.careAddr}</td>
            </tr>
        </table>
    </div>
    <div id="animal-detail">
        <img id="animalImg_2" src="${n.popfile}" alt="">
        <div id="comment1">
            ${n.specialMark}
        </div>
    </div>
    <hr>
    <div id="adopt-btn-box2">
        <div id="adopt_comment2">환승주인의 동물들은 모두 입양 전 교육이수 후 첫만남이 가능합니다.
            동물들의 나이는 추정나이로써 실제 나이와 일치하지 않을 수 있습니다<br>
            입양 후 생활패턴이나 환경 등 변화에 따라 성격이 달라질 수 있으니 충분히 고려하신 후 입양신청 부탁드립니다.<br>
        </div>
        <div id="btn-area2">
            <button id="adopt-step" onclick="location.href='<%=request.getContextPath()%>/'">입양절차</button>
            <button id="adopt-apply" onclick="location.href='${context}/adoptApply?dno=${n.desertionNo}'">입양신청</button>
        </div>
    </div>
    <hr>
    <div id="reply-area">
        <div id="reply-line"></div>
        <div id="reply-list">
            <table id="replyTable">
                <c:forEach items="${rList}" var="r">
                <tr>
                <%--//todo: id 에서 class로 바뀜 반복문을 돌아서--%>
                    <th class="replyWriter">${r.replyWriter}</th>
                    <th class="replyDate">${r.replyDate}</th>
                <c:if test="${!empty loginUser}">
                    <th class="replyReport bi bi-exclamation-triangle" data-toggle="modal" data-target="#reportModal" data-kind="notice" data-ref="${r.replyNo}"></th>
                    <c:if test="${r.memberNo eq loginUser.memberNo or loginUser.memberGrade.SITE_MANAGER}">
                    <th class="replyDelete bi bi-trash"></th>
                    </c:if>
                </c:if>
                </tr>
                <tr>
                    <c:if test="${!empty r.at}">
                    <td><img class="replyImg" src="${r.at.filePath}/${r.at.changeName}" alt="${r.at.originName}"></td>
                    </c:if>
                </tr>
                <tr>
                    <td class="replyContent" colspan="4"> ${r.replyContent}</td>
                </tr>
                </c:forEach>
            </table>
            <c:if test="${!empty loginUser}">
            <form action="replyInsert.no" enctype="multipart/form-data" method="post">
            <div id="replyInput">
                <input type="hidden" name="noticeNo" value="${n.desertionNo}">
                <input type="text" name="replyContent"id="replyApply" required placeholder="댓글을 입력해주세요">
                <button type="submit" id="reply-btn">댓글작성</button>
                <input type="file" id="replyFile" name="upfile" placeholder="댓글을 입력해주세요">
            </div>
            </form>
            </c:if>
        </div>
    </div>
</div>
<%@include file="/views/template/report.jsp"%>
<footer><%@include file="/views/template/footer.jsp"%></footer>
<script src="${context}/js/shelter/noticeDetail.js"></script>
</body>
</html>
