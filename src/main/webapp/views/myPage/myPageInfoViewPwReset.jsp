<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  String memberPwd = (String)request.getAttribute("memberPwd");

%>
<html>
<head>
  <title>비밀번호 변경</title>

  <%@ include file="/views/template/styleTemplate.jsp"%>
  <link href="<%=request.getContextPath()%>/css/mypage/mypageInfo.css" rel="stylesheet">


</head>
<body>
<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>
<div class="headcontainer">
  <div id="right_view">
    <div class="information">
      <p>
        개인정보 수정을 위해서는 본인 확인이 필요합니다.
      </p>

      <!--비밀번호 잘못 입력시 뜨는 창-->
      <span id="pwChkMsg"></span>

      <table>
        <tr>
          <th><input type="password" name="" onkeyup="window.event.keyCode == 13 ? checkPwd() : ''" id="memberPw" placeholder=" 비밀번호 입력" required></th>
        </tr>
        <tr>
          <th><button type="button" class="asds" id="submitButton"  onclick="checkPwd()">확인</button></th>
        </tr>
      </table>
    </div>
  </div>
</div>
<script>
  $(function(){
    $(".list-text").text("비밀번호 변경");
  })

  function checkPwd() {
    let pwChkMsg = document.querySelector("#pwChkMsg");
    let memberPw = $("#memberPw").val();
    $.ajax({
      url: "${context}/ajaxCheckPwd",
      type: "get",
      data: {
        memberPwd: memberPw
      },
      dataType: "json",
      success: function (data) {
        if (data == 1) {
          location.href = '<%=request.getContextPath()%>/mypagePwResetPage';
        } else {
          pwChkMsg.innerText = " * 비밀번호를 다시 한 번 입력해주세요."
        }
      }
    })
  }

</script>
</body>
</html>
