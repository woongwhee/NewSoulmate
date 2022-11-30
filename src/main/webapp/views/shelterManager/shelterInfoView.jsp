<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Member loginUser = (Member) session.getAttribute("loginUser");
    Shelter shelter = (Shelter) request.getAttribute("shelter");

    String email = shelter.getShelterEmail();
    String firstEmail = email == null ? "" : email.split("@")[0];
    String secondEmail = email == null ? "" : email.split("@")[1];
%>

<html>
<head>
    <title>보호소 정보수정</title>
    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterInfoView.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/views/shelterManager/shelterHeader.jsp" %>
</header>

<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <div class="form-group">
                <label>보호소코드</label>
                <p>
                    <%=loginUser.getShelterNo()%>
                </p>
                <label>보호소이름</label>
                <p>
                    <%=shelter.getShelterName()%>
                </p>

            </div>
            <div class="form-group">
                <label for="landline">유선 전화번호</label>
                <input type="text" name="landline" id="landline" value="<%=shelter.getShelterLandline()%>">
            </div>

            <div class="form-group">
                <label for="tel">무선 전화번호</label>
                <input type="text" name="tel" id="tel"
                       value="<%if(shelter.getShelterTel() == null) { %><%} else { %><%=shelter.getShelterTel()%><%}%>">
            </div>

            <div class="form-group">
                <label>주소</label>
                <input type="text" name="nickName" id="shelterAddress" value="<%=shelter.getShelterAddress()%>" required>
            </div>

            <div class="form-group">
                <label>이메일</label>

                <input type="text" name="email_1" id="email_1" value="<%=firstEmail%>">
                @
                <input type="text" name="email_2" id="email_2" value="<%=secondEmail%>">

                <select name="email_3" id="email_3">
                    <option value="1">직접입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="nate.com">nate.com</option>
                    <option value="gmail.com">gmail.com</option>
                    <option value="hanmail.net">hanmail.net</option>
                </select>

                <button type="button" onclick="sendMail()" id="emailCheck">인증번호 발송</button>
                <div id="auth"></div>
                <div id="certified" style="display: none">
                    <input type="text" id="authCode" placeholder="인증번호">
                    <button type="button" class="" id="checkAuthCode" onclick="authenticationMail()" disabled>인증번호 확인</button>
                </div>
            </div>
            <span id="timeZone"></span>
            <span id="authMsg"></span>
            <button type="submit" id="myPageCheck" onclick="shelterInfoUpdate()" disabled>변경사항 저장하기</button>
        </div>
    </div>
</div>

<script>
    $(".form-group>input").keyup(function () {
        $("#myPageCheck").removeAttr("disabled");
    })

    function shelterInfoUpdate() {
        if (checkMail === 0) {
            alert("이메일 인증을 해주세요.");
            return;
        }

        let landline = $("#landline").val()
        let tel = $("#tel").val()
        let address = $("#shelterAddress").val()
        let email = $("#email_1").val() + "@" + $("#email_2").val()


        $.ajax({
            url: "<%=request.getContextPath()%>/shelter/update",
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                'shelterNo': <%=shelter.getShelterNo()%>,
                'shelterLandline': landline,
                'shelterTel': tel,
                'shelterAddress': address,
                'shelterEmail': email
            }),
            success: function () {
                alert("보호소 정보 변경 성공!");
                location.reload();
            },
            error: function () {
                alert("서버요청실패");
            }
        });
    }

    $(function () {
        $("#landline").on("keyup", function () {
            var flag1 = true;
            flag1 = $(this).val().length > 0 ? false : true;
            $("#checkLandline").attr("disabled", flag1);
        });

        $("#tel").on("keyup", function () {
            var flag1 = true;
            flag1 = $(this).val().length > 0 ? false : true;
            $("#checkTel").attr("disabled", flag1);
        });

        $("#email_1").on("keyup", function () {
            var flag2 = true;
            flag2 = $(this).val().length > 0 ? false : true;
            $("#emailCheck").attr("disabled", flag2);
        });

        $("#authCode").on("keyup", function () {
            var flag = true;
            flag = $(this).val().length > 0 ? false : true;
            $("#checkAuthCode").attr("disabled", flag) && $("#shelterCheck").attr("disabled", flag);
        });

        $("#shelterAddress").on("keyup", function () {
            var flag1 = true;
            flag1 = $(this).val().length > 0 ? false : true;
            $("#checkShelterAddress").attr("disabled", flag1);
        });
    });

    $('#email_3').change(function(){
        $("#email_3 option:selected").each(function () {

            if($(this).val()== '1'){ //직접입력일 경우
                $("#email_2").val('');                        //값 초기화
                $("#email_2").attr("disabled",false); //활성화
            }else{ //직접입력이 아닐경우
                $("#email_2").val($(this).text());      //선택값 입력
                $("#email_2").attr("disabled",true); //비활성화
            }
        });
    });
    let inputEmail = 0;
    $("#email_1,#email_3").keyup(function () {
        inputEmail = 1;
        $("#emailCheck").removeAttr("disabled");
    })

    let checkMail = 0;
    let mailCode;
    let intervalId;

    function sendMail() {
        const memberMail2 = $("#email_1").val() + "@" + $("#email_2").val()
        $("#certified").show();
        $.ajax({
            url: "<%= request.getContextPath()%>/sendMail.do",
            data: {memberMail: memberMail2},
            type: "get",
            success: function (data) {
                if (data != null) {
                    mailCode = "notNull";
                    $("#auth").css("display", "flex");
                    authTime();
                }
            }
        });
    }

    // 입력시간 출력
    function authTime() {
        $("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
        intervalId = window.setInterval(function () {
            timeCount();
        }, 1000);
    }

    function timeCount() {
        const min = Number($("#min").text());
        const sec = $("#sec").text();
        if (sec == "00") {
            if (min == 0) {
                mailCode = null;
                clearInterval(intervalId);
            } else {
                $("#min").text(min - 1);
                $("#sec").text(59);
            }
        } else {
            const Sec2 = Number(sec) - 1;
            if (Sec2 < 10) {
                $("#sec").text("0" + Sec2);
            } else {
                $("#sec").text(Sec2);
            }
        }
    }

    function authenticationMail() {
        inputEmail = 0;
        const inputValue = $("#authCode").val();
        if (mailCode != null) {
            $.ajax({
                url: '<%= request.getContextPath()%>/checkAuth',
                type: 'get',
                data: {authCode: inputValue},
                success: (result) => {
                    if (result == 1) {
                        $("#authMsg").text("인증에 성공하셨습니다.");
                        clearInterval(intervalId);
                        $("#timeZone").hide();
                        checkMail = 1;
                    } else if (result == 0) {
                        $("#authMsg").text("인증번호가 일치하지 않습니다.");
                        checkMail = 0;
                    }
                },
                error: function () {
                    alert("서버요청실패");
                    checkMail = 0;
                }
            });
        } else {
            $("#authMsg").text("인증시간이 만료되었습니다.");
            checkMail = 0;
        }
        /*console.log(inputValue);
        console.log(mailCode);*/
    };
</script>

</body>
</html>
