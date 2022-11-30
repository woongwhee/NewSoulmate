<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Member loginUser = (Member)session.getAttribute("loginUser");
    String[] email = loginUser.getEmail().split("@");
    String firstEmail = email[0];
    String secondEmail = email[1];


%>
<html>
<head>
    <title>회원정보 수정</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/mypage/mypageInfoDetail.css" rel="stylesheet">

</head>
<body>
<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <b> ${loginUser.memberName}님 환영합니다.
            </b> <br><br>


            <form action="${context}/MyPageUpdate"  method="post" name="myPageInfo">

                <div class="form-group">
                    <label for="">아이디</label>
                    <p>
                        ${loginUser.memberId}
                    </p>
                </div>
                <div class="form-group">
                    <label for="">이름</label>
                    <p>
                        ${loginUser.memberName}
                    </p>
                </div>
                <div class="form-group">
                    <label for="phone">전화번호</label>
                    <input type="text" name="phone" id="phone" value="${loginUser.phone}">
                </div>


                <div class="form-group">
                    <label for="">닉네임</label>
                    <input type="text" name="nickName" id="nickName" value="${loginUser.nickName}"  required>
                    <button type="button" id="checkNickname" disabled>중복확인</button>
                </div>

                <div class="form-group" >
                    <label for="">이메일</label>

                    <input type="text" name="email_1" id="email_1" value="<%=firstEmail%>">
                    <input type="text" name="email_2" id="email_2" value="<%=secondEmail%>">

                    <select name="email_3" id="email_3">
                        <option value="1">직접입력</option>
                        <option value="naver.com">naver.com</option>
                        <option value="nate.com">nate.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="hanmail.net">hanmail.net</option>
                    </select>
                    <button type="button" onclick="sendMail()" id="emailCheck" disabled>인증번호 발송</button>
                    <div id="auth"></div>
                    <div id="certified" style="display: none">
                        <input type="text" id="authCode" placeholder="인증번호" disable>
                        <button type="button" class="" id="checkAuthCode" onclick="authenticationMail()">인증번호 확인</button>
                    </div>
                </div>
                <span id="timeZone"></span>
                <span id="authMsg"></span>
                <button type="submit" id="myPageCheck" onclick="return myPageUpdate()" disabled>변경사항 저장하기</button>

            </form>
        </div>
    </div>
</div>
<script>
    // 변경사항이 있을경우 -> 변경된 input value를 servlet에 보내줘야함
    // 변경된 input에 대해서 무조건 유효성 검사 해야함 -> 버튼 클릭을 해야함.
    // 변경하기 클릭시
    // -> 변경사항이 있고 유효성 검사를 하지 않았다면 중복확인 해주세요 / 이메일 인증을 해주세요

    // 전역변수로 let input1Change = 0
    // 각 input  change 를 걸어서 변경된 값을 담고 // let change = 1;
    // 변경하기 클릭시 input1Change = 0 이라면 조건 통과 / input1Change = 1이라면 중복체크를 해주세요.
    // 이메일 인증

    let checkNickname = 0;
    let checkMail = 0;
    let mailCode;
    let intervalId;
    let inputNickName = 0;
    $("#nickName").keyup(function(){
        inputNickName = 1;
            checkNickname=
        $("#checkNickname").removeAttr("disabled");
    })

    $("#email_1,#email_3,#email_2").change(function () {
        inputEmail = 1;
        checkMail=0;
        $("#emailCheck").removeAttr("disabled");
    })

    $(".form-group>input,select").keyup(function () {
        $("#myPageCheck").removeAttr("disabled");
    })




    // 닉네임 중복체크 - 완료
    const nickName = document.querySelector("#nickName");
    const nickReg = /^[a-zA-Z1-9ㄱ-힣]{3,}/;



    $('#checkNickname').click(function() {
        inputNickName = 0;
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


    function sendMail() {
        const memberMail2 = $("#email_1").val() +"@"+$("#email_2").val()
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
    };




    function myPageUpdate(){
        if(inputNickName == 1 && checkNickname == 0){
            alert("닉네임 중복체크를 해주세요.");
            return false;
        }else if(inputEmail == 1 && checkMail == 0){
            alert("이메일 인증 해주세요.");
            return false;
        }
    };

</script>



</body>
</html>