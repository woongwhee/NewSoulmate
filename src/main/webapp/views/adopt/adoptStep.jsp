<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-22
  Time: 오후 7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>입양절차</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/common/adoptStep.css" rel="stylesheet">
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div id="content">
    <div id="check-list">
        <div class="title">입양 전 체크리스트</div>
        <div class="comment-box">
            <i class="bi bi-check-all"></i>
            <div class="step-comment">
                모든 동물은 살아가면서 질병에 걸릴 수 있습니다. 평생 책임지고 잘 돌봐줄 수 있나요?
            </div>
        </div>
        <div class="comment-box">
            <i class="bi bi-check-all"></i>
            <div class="step-comment">
                매일 산책시켜주거나 함께 있어줄 수 있는 시간이 충분한가요?<br>
                동물들도 외로움은 느낍니다. 외롭지않게 많은 시간을 같이 있어주세요.
            </div>
        </div>
        <div class="comment-box">
            <i class="bi bi-check-all"></i>
            <div class="step-comment">
                입양 이후 모니터링 관련 정보 제공에 협조해 주실 수 있나요?<br>
                입양 개선을 위해 보호자분의 적극적인 입양후기 공유를 요청 드립니다.<br>
                또한 입양동물 정보제공에도 적극적으로 응해주시기를 바랍니다.
            </div>
        </div>
    </div>
    <div id="step">
        <div class="title">입양 절차</div>
        <div class="comment-box">
            <i class="bi bi-clipboard-heart"></i>
            <div class="step-comment">
                <p>입양 전 정보확인</p>
                환승주인 홈페이지에서 '입양가능 동물'을 확인해주세요.<br>
                입양을 원하는 동물의 상세정보를 꼼꼼하게 읽고 확인해주세요.
            </div>
            <div class="btn-box">
                <button class="btn" onclick="location.href='noticeList'">입양가능동물 보러가기</button>
            </div>
        </div>
        <div class="caret">
            <i class="bi bi-caret-down-fill"></i>
        </div>
        <div class="comment-box">
            <i class="bi bi-postcard-heart"></i>
            <div class="step-comment">
                <p>입양신청</p>
                입양신청서를 작성해주세요.<br>
                신청서에 기입하신 번호로 3일이내 안내문자를 보내드립니다.
            </div>
            <div class="btn-box">
                <button class="btn" onclick='location.href="adoptApply"'>입양신청서 작성하기</button>
            </div>
        </div>
        <div class="caret">
            <i class="bi bi-caret-down-fill"></i>
        </div>
        <div class="comment-box">
            <i class="bi bi-house-heart"></i>
            <div class="step-comment">
                <p>입양 진행</p>
                입양은 1~2회 입양상담 및 동물만남을 통해 진행됩니다.<br>
                (입양 후 파양을 불가능합니다. 만남을 통해 신중하게 결정해주세요.)
            </div>
        </div>
        <div class="caret">
            <i class="bi bi-caret-down-fill"></i>
        </div>
        <div class="comment-box">
            <i class="bi bi-chat-right-heart"></i>
            <div class="step-comment">
                <p>입양 후기 공유</p>
                보호자님과 입양된 동물의 행복한 일상을 입양후기 게시판에 주기적으로 올려주세요.<br>
                입양 대기중인 아이들의 입양과 입양인식 개선에 많은 도움이 됩니다.
            </div>
            <div class="btn-box">
                <button class="btn" onclick="location.href='adoptRevEnroll'">입양후기 작성하기</button>
            </div>
        </div>
    </div>
    <div id="notice">
        <div class="title">유의사항</div>
        <p> * 한 마리에게 여러명의 입양희망자가 생길 경우 회의를 거쳐 최종 보호자를 선정하게 됩니다. 최종 선정이 되지않더라도 양해해주시기 바랍니다.<br>
            * 입양을 진행하면서 각 가정에서 필요한 물품들을 알려드립니다. 필요한 물품들을 입양 전에 준비하여 편안한 가정환경을 만들어주시기 바랍니다.<br>
            * 입양후 파양은 불가능합니다. 가족구성원 모두가 신중하게 생각하시고 충분한 논의 후 입양을 결정해주세요.<br>
        </p>
    </div>
</div>
</body>
<%@include file="/views/template/footer.jsp"%>
</html>
