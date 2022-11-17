<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
    Member loginUser = (Member)session.getAttribute("loginUser");
    String memberId = loginUser.getMemberId();
    String memberPwd = loginUser.getMemberPwd();
    String memberName = loginUser.getMemberName();
    String nickName = loginUser.getNickName();
    String email = loginUser.getEmail();
%>--%>
<html>
<head>
    <title>회원정보 수정</title>
    <link href="<%=request.getContextPath()%>/css/mypage/mypageInfoDetail.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/mypage/myPageHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <b>안녕</b>님 환영합니다. <br><br>


            <form action=""  method="get">
                <div class="form-group">
                    <label for="">아이디</label>
                    <p>

                    </p>
                </div>

                <div class="form-group">
                    <label for="memberPwd">비밀번호</label>
                    <input type="password" name="memberPwd" id="memberPwd" placeholder="비밀번호 입력" required>
                    <span id="pwChkMsg"></span>
                </div>

                <div class="form-group">
                    <label for="memberPwRe">비밀번호 재확인</label>
                    <input type="password" name="memberPwRe" id="memberPwRe" placeholder="비밀번호 재입력" required>
                    <span id="pwReChkMsg"></span>
                    <button type="button" id="checkPwd" onclick="">변경</button>
                </div>

                <div class="form-group">
                    <label for="">닉네임</label>
                    <input type="text" name="nickName" id="nickName" value="122334" placeholder="<%--<%=nickName%>--%>" required>
                    <button type="button" id="checkNickname">중복확인</button>
                </div>

                <div class="form-group" >
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
                    <button type="button" onclick="sendMail()">인증번호 발송</button>
                    <div id="auth"></div>
                    <div id="certified">
                        <input type="text" id="authCode" placeholder="인증번호">
                        <button type="button" class="" id="" onclick="authenticationMail()">인증번호 확인</button>
                    </div>
                </div>
                <span id="timeZone"></span>
                <span id="authMsg"></span>
                <button type="submit" onclick="" id="">변경사항 저장하기</button>
            </form>
        </div>
    </div>
</div>
<script>


        //비밀번호 유효성 검사 - 완료
        const memberPw = document.querySelector("#memberPwd");
        const memberPwRe = document.querySelector("#memberPwRe");

        memberPw.addEventListener("change", function () {

            const inputPw = memberPw.value;
            const pwReg = /^[a-zA-Z0-9]{6,}$/;
            const pwChkMsg = document.querySelector("#pwChkMsg");

            const inputPwRe = memberPwRe.value;
            const pwReChkMsg = document.querySelector("#pwReChkMsg");

            if (pwReg.test(inputPw)) {
                pwChkMsg.innerText = "사용 가능한 비밀번호 입니다."
                checkPwd = 1;
            } else {
                pwChkMsg.innerText = "사용 불가능한 비밀번호 입니다."
                checkPwd = 0;
            }
            if (inputPwRe != "") {
                if (inputPw == inputPwRe) {
                    pwReChkMsg.innerText = "비밀번호가 일치합니다."
                    checkPwdRe = 1;
                } else {
                    pwReChkMsg.innerText = "비밀번호가 일치하지않습니다."
                    checkPwdRe = 0;
                }
            } else {

            }
        });


        // 비밀번호 일치 검사 - 완료
        memberPwRe.addEventListener("change", function () {
            const inputPw = memberPw.value;
            const inputPwRe = memberPwRe.value;
            const pwReChkMsg = document.querySelector("#pwReChkMsg");
            if (inputPw == inputPwRe) {
                pwReChkMsg.innerText = "비밀번호가 일치합니다."
                checkPwdRe = 1;
            } else {
                pwReChkMsg.innerText = "비밀번호가 일치하지않습니다."
                checkPwdRe = 0;
            }
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


    // 닉네임 중복체크 - 완료
    const nickName = document.querySelector("#nickName");
    const nickReg = /^[a-zA-Z1-9ㄱ-힣]{3,}/;

    $('#checkNickname').click(function() {

        let nickNames = $('#nickName').val();

        if(nickReg.test(nickNames)){
            $.ajax({
                url : '<%= request.getContextPath()%>/ajaxCheckNickname.do',
                type: 'get',
                data : { nickName: nickNames },
                dataType : 'json',
                success: function(result) {

                    if (result == 1) {
                        alert("이미 사용중인 닉네임 입니다.");
                        checkNickname = 0;
                    } else if (result == 0) {
                        alert("사용가능한 닉네임 입니다.");
                        checkNickname = 1;
                    }
                },
                error : function(){
                    alert("서버요청실패");
                    checkNickname = 0;
                }
            })
        }else {
            alert("닉네임은 3자 이상이어야 합니다.")
            checkNickname = 0;
        }
    });


    // 이메일 인증
    let checkMail = 0;
    let mailCode;
    let intervalId;

    function sendMail() {
        const memberMail2 = $("#email_1").val() + $("#email_2").val()
        console.log(memberMail2);

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
