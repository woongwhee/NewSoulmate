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
        <div>
            <h2>회원가입</h2>
        </div>

        <div class="agree">
            <label><input type="checkbox" name="agreeCheckBox" class="allcheck" >이용약관의 내용에 모두 동의합니다.</label>
        </div>

        <div class="agree">
            <label><input type="checkbox" name="agreeCheckBox" class="required_checked">이용약관에 동의합니다.(필수)</label>
        </div>

        <div>
            <div>
                <textarea readonly class="textarea-form">

                    이용약관

                </textarea>
            </div>

            <div class="agree">
                <label><input type="checkbox" name="agreeCheckBox" class="required_checked">개인정보 수집약관의 내용에 동의합니다.(필수)</label>
            </div>

            <div>
                <textarea readonly class="textarea-form">

                    개인정보 수집약관

                </textarea>
            </div>

            <div>
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