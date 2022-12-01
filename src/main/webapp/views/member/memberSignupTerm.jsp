<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-09
  Time: 오후 5:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 약관</title>


    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/member/signupTerm.css" rel="stylesheet">
</head>
<body>



<%@include file="/views/template/menubar.jsp"%>

<div class="term-wrapper" align="center">
    <div>
        <div class="title-wrap">
            <p>회원가입 약관동의</p>
        </div>
        <div class="agree">
            <input type="checkbox" name="agreeCheckBox" id="agree1" class="required_checked"><label for="agree1">이용약관에 동의합니다.(필수)</label>
        </div>

        <div>
            <div>
                <textarea readonly class="textarea-form">
여러분을 환영합니다.
환승주인 서비스 및 후원(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 환승주인 서비스의 이용과 관련하여 환승주인 서비스를 제공하는 환승주인과 이를 이용하는 환승주인 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 환승주인 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.

환승주인 서비스를 이용하시거나 환승주인 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.

다양한 환승주인 서비스를 즐겨보세요.

환승주인 서비스에는 기본적으로 본 약관이 적용됩니다만 환승주인가 다양한 서비스를 제공하는 과정에서 부득이 본 약관 외 별도의 약관, 운영정책 등을 적용하는 경우(예, 환승주인후원 등)가 있습니다. 그리고 환승주인이 제공하는 특정 서비스의 경우에도 해당 운영 회사가 정한 고유의 약관, 운영정책 등이 적용될 수 있습니다. 이러한 내용은 각각의 해당 서비스 초기 화면에서 확인해 주시기 바랍니다.
                </textarea>
            </div>

            <div class="agree">
                <input type="checkbox" name="agreeCheckBox" class="required_checked"><label>개인정보 수집약관의 내용에 동의합니다.(필수)</label>
            </div>

            <div>
                <textarea readonly class="textarea-form">
개인정보보호법에 따라 환승주인에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.

1. 수집하는 개인정보
이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 환승주인 서비스를 회원과 동일하게 이용할 수 있습니다.

회원가입 시점에 환승주인가 이용자로부터 수집하는 개인정보는 아래와 같습니다.
- 회원 가입 시에 ‘아이디, 비밀번호, 이름, 이메일 닉네임' 필수항목으로 수집합니다.

서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다. 또한 이미지 및 음성을 이용한 검색 서비스 등에서 이미지나 음성이 수집될 수 있습니다.
구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나,
2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다. 서비스 이용 과정에서 위치정보가 수집될 수 있으며,
환승주인에서 제공하는 위치기반 서비스에 대해서는 '환승주인 위치기반서비스 이용약관'에서 자세하게 규정하고 있습니다.
이와 같이 수집된 정보는 개인정보와의 연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.

2. 수집한 개인정보의 이용
환승주인 및 환승주인 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.


                </textarea>
            </div>

            <div class="agree">
                <input type="checkbox" name="agreeCheckBox" class="allcheck" ><label>이용약관의 내용에 모두 동의합니다.</label>
            </div>
            <div id="agree-btn-box">
                <button id="cancelBtn"><a href="index.jsp">취소</a></button>
                <button id="signupBtn">가입하기</button>
            </div>
        </div>
    </div>
</div>

<script src="${context}/js/member/memberSignupTerm.js"></script>

<%@include file="/views/template/footer.jsp"%>


</body>
</html>