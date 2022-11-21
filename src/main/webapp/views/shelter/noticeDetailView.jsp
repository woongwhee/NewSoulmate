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
    <title>환승주인 - 상세보기</title>
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
        <img id="animalImg_1" src="" alt="">
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
                <td>${n.sexCd}</td>
            </tr>
        </table>
    </div>
    <div id="animal-detail">
        <img id="animalImg_2" src="" alt="">
        <div id="comment1">
            울릉이는 실외배변을 하는 아이라 산책이 필수입니다..!
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
            <button id="adopt-apply" onclick="location.href='<%=request.getContextPath()%>/'">입양신청</button>
        </div>
    </div>
    <hr>
    <div id="reply-area">
        <div id="reply-line"></div>
        <div id="reply-list">
            <table id="replyTable">
                <tr>
                    <th id="replyWriter">작성자</th>
                    <th id="replyDate">작성일</th>
                    <th id="replyReport" class="bi bi-exclamation-triangle"onclick=""></th>
                    <th id="replyDelete" class="bi bi-trash" onclick=""></th>
                </tr>
                <tr>
                    <td colspan="4"> 댓글내용</th>
                </tr>
            </table>
            <div id="replyInput">
                <input type="text" id="replyApply" placeholder="댓글을 입력해주세요">
                <button id="reply-btn" onclick="">댓글작성</button>
            </div>
        </div>
    </div>
</div>
<footer><%@include file="/views/template/footer.jsp"%></footer>
</body>
</html>
