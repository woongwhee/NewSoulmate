<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  String memberPwd = (String)request.getAttribute("memberPwd");

%>
<html>
<head>
    <title>회원정보 수정</title>
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
        <p id="pwChkMsg">

        </p>

        <form id="" action="" method="post">
          <table>
            <tr>
              <th><input type="password" name="" id="checkPwd" placeholder=" 비밀번호 입력" required></th>
            </tr>
            <tr>
              <th><button type="button" onclick="">확인</button></th>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
<script>
  $("#checkPwd").onclick(function (){
    const pwChkMsg = document.querySelector("#pwChkMsg");
    if(<%=memberPwd%> = $("#checkPwd").val()){  // 비밀번호 일치시

      location.href='<%=request.getContextPath()%>/myPage';

    }else{ // 비밀번호 입력 잘못했을시
      pwReChkMsg.innerText = " * 비밀번호를 다시 한 번 입력해주세요."
    }
  })
</script>
</body>
</html>
