<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>신고접수</title>
    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/manager/managerAdoptApplyList.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/views/manager/managerHeader.jsp" %>
</header>
<div class="headcontainer">
    <div id="right_view">
        <div id="applyList">
            <table class="list-area" style="text-align: center">
                <thead>
                <tr>
                    <th width="50">번호</th>
                    <th width="100">분류</th>
                    <th width="235">내용</th>
                    <th width="235">신고사유</th>
                    <th width="80">처리결과</th>
                    <th width="80"></th>
                    <th width="80"></th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty rList}">
                        <tr id="tableEmpty">
                            <td colspan = "7">존재하는 신고내역이 없습니다.</td>
                        </tr>
                    </c:when>
                    <c:otherwise >
                        <c:forEach items="${rList}" var="rp">
                            <tr>
                                <td>${rp.reportNo}</td>
                                <td>${rp.refType.koreanName}</td>
                                <td>${rp.reportContent}</td>
                                <td>${rp.categoryName}</td>
                                <td>미처리</td>
                                <td><button type="button" class="checkRef" data-rno="${rp.reportNo}">게시글 이동</button></td>
                                <td><button type="button" class="confirm" data-rno="${rp.reportNo}">확인</button></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        <br><br>
    </div>
</div>
<script>
    $(function(){
        $(".list-text").text("신고접수");
    })

    $('.checkRef').click((e) => {
        let reportNo = $(e.target).data('rno');
        window.open('reportDetail?rno=' + reportNo);

    });
    $('.confirm').click((e) => {
        let reportNo = $(e.target).data('rno');
        window.open('reportStatus?rno=' + reportNo);
    })
</script>
<br><br>
</body>
</html>
