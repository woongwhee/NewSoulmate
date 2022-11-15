<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-10
  Time: 오전 4:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <%--<script src="/JS/searchMemberId.js"></script>--%>

</head>
<body>

<%@include file="/views/template/menubar.jsp" %>

<div class="content-wrap" align="center">

    <div class="title-wrap">
        <h2>아이디 찾기</h2>
    </div>

    <form action="<%=request.getContextPath()%>/findId.do" id="myForm" method="get">
        <div class="searchid-wrap">
            <div>

                <div class="search-content">
                    <input type="text" name="searchName" id="searchName" placeholder="이름">
                </div>

                <div class="email-wrap">
                    <div>
                        <label for="memberMail">이메일</label>
                        <input type="text" name="memberMail" id="memberMail" placeholder="*이메일">
                        <button type="button" onclick="sendMail();">인증번호 발송</button>

                        <div id="auth">
                            <div>
                                <input type="text" id="authCode" placeholder="인증번호">
                                <button type="button" class="authBtn" id="authBtn" onclick="authenticationMail()">인증하기
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <span id="timeZone"></span>
                <span id="authMsg"></span>

                <div class="search-content">
                    <button type="submit" class="searchIdBtn" onclick="checkAuth">아이디 찾기</button>
                </div>

            </div>
        </div>
    </form>
</div>


<%@include file="/views/template/footer.jsp" %>


<script>

    $(".searchIdBtn").on("click", function () {
        const memberName = $("#searchName").val();
        const Email = $("#memberMail").val();
        $.ajax({
            url: "findId",
            type: "get",
            data: {
                memberName: memberName,
                Email: Email
            },
            dataType: "json",
            success: function (data) {
                if (data == null) {
                    alert("일치하는 회원정보가 없습니다.");
                } else {
                    alert("아이디 : " + data.memberId);
                }
            },
            error: function () {
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