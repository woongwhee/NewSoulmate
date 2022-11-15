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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>

<body>

<%@include file="/views/template/menubar.jsp"%>

<div class="content-wrap" align="center">
    <div class="title-wrap">
        <h2>로그인</h2>
    </div>
    <div class="content-box">
        <form id="loginForm" action="<%=request.getContextPath()%>/login.do" method="post">
            <table class="logintable">
                <tr>
                    <th><label for="loginId">아이디</label></th>
                    <td><input class="input-form" type="text" name="memberId" id="loginId" placeholder="아이디"></td>
                </tr>
                <tr>
                    <th><label for="loginPw">비밀번호</label></th>
                    <td> <input class="input-form" type="password" name="memberPwd" id="loginPw"placeholder="비밀번호"></td>
                </tr>
                <tr>
                    <td><input type="checkbox" id="saveId"><label for="saveId">아이디 저장</label></td>
                    <td><button onclick="submitLogin()" class="loginBtn">로그인하기</button></td>
                </tr>
                <tr>
                    <td><a href="views/member/findId.jsp">아이디찾기</a>/<a href="views/member/findPwd.jsp">비밀번호 찾기</a></td>
                    <th><a href="views/member/memberSignupTerm.jsp">신규 회원가입</a></th>
                </tr>
            </table>
        </form>
    </div>
</div>

<%@include file="/views/template/footer.jsp"%>

<script>
    <%
        Object errorMsgObj = request.getAttribute("errorMsg");
		if (errorMsgObj != null) {
			String errorMsg = (String) errorMsgObj;
    %>
    alert("<%=errorMsg%>");
    <%
        }
    %>

    <%
        Object alertMsgObj = request.getAttribute("alertMsg");
		if (alertMsgObj != null) {
			String alertMsg = (String) alertMsgObj;
    %>
    alert("<%=alertMsg%>");
    <%
        }
    %>

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
            let index = documnet.cookie.indexOf("saveId="); // saveId = admin; path =/; max-age = 5660;
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

