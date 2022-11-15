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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <%--<script src="/JS/member/memberSignupTerm.js"></script>--%>

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

<%@include file="/views/template/footer.jsp"%>

<script>
    // 전체선택, 전체해제
    $(function(){
        $("[type=checkbox][name=agreeCheckBox]").on("change", function(){
            const check = $(this).prop("checked");

            if($(this).hasClass("allcheck")){
                $("[type=checkbox][name=agreeCheckBox]").prop("checked", check);


            }else{
                const all = $("[type=checkbox][name=agreeCheckBox].allcheck");
                const allcheck = all.prop("checked")
                if(check != allcheck){
                    const len = $("[type=checkbox][name=agreeCheckBox]").not(".allcheck").length;
                    const ckLen = $("[type=checkbox][name=agreeCheckBox]:checked").not(".allcheck").length;
                    if(len === ckLen){
                        all.prop("checked", true);
                    }else{
                        all.prop("checked", false);
                    }
                }
            }

        });

// 동의 항목 모두 체크돼야 회원가입 페이지로 넘어갈 수 있음
        $("#signupBtn").click(function () {

            if ($('.required_checked:checked').length != $('.required_checked').length) {
                alert("동의 항목을 모두 선택해주세요.");
                return;
            }
            $(location).attr("href","${context}/MemberSignup");
        });

    });

</script>



</body>
</html>