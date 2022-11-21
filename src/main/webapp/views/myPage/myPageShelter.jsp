<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>보호소관계자 등록신청</title>
  <link href="<%=request.getContextPath()%>/css/mypage/mypageShelter.css" rel="stylesheet">
</head>
<body>

<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>

<div class="headcontainer">
  <div id="right_view">
    <div id="user_information">
      <form action="" id="" method="post">
        <div class="form-group">
          <label for="">보호소명</label>
          <input type="text" name="" id="shelterName" placeholder="보호소명" required>
        </div>

        <div class="form-group">
          <label for="">비밀번호</label>
          <input type="password" name="" id="" placeholder="비밀번호 입력" required>
        </div>

        <div class="form-group">
          <label for="">보호소 사업자 번호</label>
          <input type="text" name="" id="" placeholder="보호소 사업번호('-'빼고 입력)" required>
        </div>

        <div class="form-group">
          <label for="">사업자 대표명</label>
          <input type="text" name="" id="" placeholder="사업자대표명" required>
        </div>

        <div class="form-group">
          <label for="">보호소 전화번호</label>
          <input type="text" name="" id="" placeholder="'-'빼고 입력">
        </div>

        <div class="form-group">
          <label for="">보호소 관계자 전화번호</label>
          <input type="text" name="" id="" placeholder="'-'빼고 입력">
        </div>
        <div class="form-file">
          <input type="file" id="" name="첨부파일">
        </div>
        <button type="submit" onclick="" id="">변경사항 저장하기</button>
      </form>
    </div>
  </div>
</div>

</body>
</html>
