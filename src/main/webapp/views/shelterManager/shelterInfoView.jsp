<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>보호소 정보수정</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterInfoView.css" rel="stylesheet">

</head>
<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>

<!-- *** mypageInfoDetailView과 코드 매우 유사하니 참고해서 만들기!!!!-->

<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <form action="" method="get">
                <div class="form-group">
                    <label for="">보호소코드</label>
                    <p>
                        448527
                        <!--임의 보호소코드-->
                    </p>

                    <label for="">보호소이름</label>
                    <p>
                        참바른 동물병원
                        <!--임의 보호소코드-->
                    </p>

                </div>

                <div class="form-group">
                    <label for="landline">유선 전화번호</label>
                    <input type="password" name="landline" id="landline" placeholder="유선 전화번호 입력">
                    <button type="button" id="checkLandline" onclick="">변경</button>
                </div>

                <div class="form-group">
                    <label for="tel">무선 전화번호</label>
                    <input type="password" name="tel" id="tel" placeholder="무선 전화번호 입력">
                    <button type="button" id="checkTel" onclick="">변경</button>
                </div>

                <div class="form-group">
                    <label for="">주소</label>
                    <input type="text" name="nickName" id="shelterAddress" value="" placeholder="주소 입력" required>
                    <button type="button" id="checkShelterAddress" >변경</button>
                </div>

                <div class="form-group">
                    <label for="">이메일</label>

                    <input type="text" name="email_1" id="email_1" placeholder="이메일 입력">
                    <input type="text" name="email_2" id="email_2" disabled value="">

                    <select name="email_3" id="email_3">
                        <option value="1">직접입력</option>
                        <option value="@naver.com">@naver.com</option>
                        <option value="@nate.com">@nate.com</option>
                        <option value="@gmail.com">@gmail.com</option>
                        <option value="@hanmail.net">@hanmail.net</option>
                    </select>
                    <button type="button" onclick="sendMail()" id="emailCheck" >인증번호 발송</button>
                    <div id="auth"></div>
                    <div id="certified">
                        <input type="text" id="authCode" placeholder="인증번호">
                        <button type="button" class="" id="checkAuthCode" onclick="authenticationMail()" disabled>인증번호 확인</button>
                    </div>
                </div>



                <button type="submit" onclick="" id="shelterCheck" disabled>변경사항 저장하기</button>
                <input type="button" onclick="" id="" value="권한 반납"></button>
            </form>
        </div>
    </div>
</div>


<script>
<!-- 버튼 활성화 (텍스트 미입력시 비활성화)-->
    $(function() {
        $("#landline").on("keyup", function() {
            var flag1 = true;
            flag1 = $(this).val().length > 0 ? false : true;
            $("#checkLandline").attr("disabled", flag1);
        });

        $("#tel").on("keyup", function() {
            var flag1 = true;
            flag1 = $(this).val().length > 0 ? false : true;
            $("#checkTel").attr("disabled", flag1);
        });

        $("#email_1").on("keyup", function() {
            var flag2 = true;
            flag2 = $(this).val().length > 0 ? false : true;
            $("#emailCheck").attr("disabled", flag2);
        });

        $("#authCode").on("keyup", function() {
            var flag = true;
            flag = $(this).val().length > 0 ? false : true;
            $("#checkAuthCode").attr("disabled", flag) && $("#shelterCheck").attr("disabled", flag);
        });

        $("#shelterAddress").on("keyup", function() {
            var flag1 = true;
            flag1 = $(this).val().length > 0 ? false : true;
            $("#checkShelterAddress").attr("disabled", flag1);
        });


    });

</script>


</body>
</html>
