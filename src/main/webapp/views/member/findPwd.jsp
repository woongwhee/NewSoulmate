<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-10
  Time: 오후 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>비밀번호 찾기</title>
</head>
<body>

<%@include file="/views/template/menubar.jsp"%>

<div class="content-wrap" align="center">

    <div class="title-wrap">
        <h2>비밀번호 찾기</h2>
    </div>

    <div class="content-box">
        <div>

            <div class="search-content">
                <input type="text" name="searchId" id="searchId" placeholder="아이디">
            </div>

            <div class="search-content">
                <input type="text" name="searchName" id="searchName" placeholder="이름">
            </div>

            <div class="search-content">
                <input type="text" name="searchMail" id="searchMail1" placeholder="이메일">
                <button onclick="sendMail();" class="authBtn">인증메일전송</button>
            </div>
        </div>

        <div class="search-content">
            <div id="auth">
                <input type="text" id="authCode" placeholder="인증번호" class="input-form">
                <button id="authBtn">인증하기</button>
            </div>
        </div>

        <div class="span-box">
            <span id="timeZone"></span>
            <span id="authMsg"></span>
        </div>

        <div class="search-content">
            <button type="submit" class="findpwBtn">비밀번호 찾기</button>
        </div>
    </div>
</div>

<%@include file="/views/template/footer.jsp"%>

<script>


    $(".findpwdBtn").on("click", function() {
        const memberId = $("#findId").val();
        const memberName = $("#findName").val();
        const memberMail = $("#findMail2").val();
        const result = $(".result");
        result.empty();
        $.ajax({
            url: "/findPwd",
            type: "get",
            data: {
            memberId: memberId,
            memberName: memberName,
            memberMail: memberMail
        },
            dataType: "json",
            success: function(data) {
                console.log(data);
                if (data == null) {
                    result.append("회원정보가 없습니다.")
                } else {
                    result.append("비밀번호 : " + data.memberPw);
                }
                },
            error: function() {
                console.log("서버호출실패");
            }
        });
    });

    // 메일 인증

    let checkMail = 0;
    let mailCode;
    let intervalId;

    function sendMail() {
        const memberMail2 = $("#memberMail").val();
        $.ajax({
            url: "<%= request.getContextPath()%>/sendMail.do",
            data: { memberMail: memberMail2 },
            type: "get",
            success: function(data) {
                if (data != null) {
                    mailCode = "notNull";
                    $("#auth").css("display","flex");
                    authTime();
                }
            }
        });
    }

    // 입력시간 출력
    function authTime() {
        $("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
        intervalId = window.setInterval(function() {
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
        const inputValue = $("#authCode").val();
        if (mailCode != null) {
            $.ajax({
                url: '<%= request.getContextPath()%>/checkAuth',
                type: 'get',
                data: {authCode: inputValue},
                success: (result)=> {
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
        }else{
            $("#authMsg").text("인증시간이 만료되었습니다.");
            checkMail = 0;
        }
        console.log(inputValue);
        console.log(mailCode);
    };




</script>



</body>
</html>
