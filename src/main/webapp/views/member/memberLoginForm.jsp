<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/11/07
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>로그인</title>

    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/member/memberLoginForm.css" rel="stylesheet">

</head>

<body>

<%@include file="/views/template/menubar.jsp"%>
<div class="content-wrap" align="center">
    <div class="title-wrap">
        <p>로그인</p>
    </div>
    <div class="content-box">
        <form id="loginForm" action="${context}/login.do" method="post">
            <table class="logintable">
                <tr>
                    <th><label for="loginId">아이디</label></th>
                    <td><input class="input-form" type="text" name="memberId" id="loginId" >
                    </td>
                </tr>
                <tr>
                    <th><label for="loginPw">비밀번호</label></th>
                    <td> <input class="input-form" type="password" name="memberPwd" id="loginPw"></td>
                </tr>
            </table>
            <div id="save-id-box">
                <input type="checkbox" id="saveId"><label for="saveId" class="save-label">아이디 저장</label>
            </div>
            <div id="login-btn-box">
                <button onclick="submitLogin()" class="loginBtn">로그인하기</button>
            </div>
            <div id="find-box">
                <a href="${context}/findId">아이디찾기</a>
                <a href="${context}/findPwd">비밀번호찾기</a>
                <a href="${context}/memberSignupTerm">회원가입</a>
            </div>
        </form>
    </div>
</div>

<%@include file="/views/template/footer.jsp"%>

<script>

    $(function(){
        getCookie();
    });

    function submitLogin(){

        let memberId = $("input[name=memberId]").val();
        console.log(memberId);

        if($("#saveId").is(":checked")){
            document.cookie = "saveId="+memberId+"; path=/; max-age="+60*60*24*7;
        }else{
            document.cookie = "saveId="+memberId+"; path=/; max-age="+0;
        }
        let form = $("#login-form");
        form.submit();
    }

    function getCookie() {
        let value = "";
        if(document.cookie.length > 0) {
            let index = document.cookie.indexOf("saveId="); // saveId = admin; path =/; max-age = 5660;
            if(index != -1) { // 쿠키값이 있다면
                index += "saveId=".length;
                let end = document.cookie.indexOf(";", index);
                console.log(index, end);
                if(end == -1) {
                    value = document.cookie.substring(index);

                }else {
                    value = document.cookie.substring(index,end);

                }
                $("input[name=memberId]").val(value);
                $("#saveId").attr("checked","true");
            }
        }
    }



</script>




</body>
</html>

