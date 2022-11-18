<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <p>
          * 비밀번호를 다시 한 번 입력해주세요.
        </p>

        <form id="" action="" method="post">
          <table>
            <tr>
              <th><input type="password" name="" id="" placeholder=" 비밀번호 입력" required></th>
            </tr>
            <tr>
              <th><button type="button" onclick="">확인</button></th>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
