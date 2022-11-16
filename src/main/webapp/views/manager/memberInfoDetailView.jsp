<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보 수정</title>
    <link href="<%=request.getContextPath()%>/css/manager/memberInfoDetail.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <%
        Member loginUser = (Member)session.getAttribute("loginUser");
        String memberId = loginUser.getMemberId();
        String memberPwd = loginUser.getMemberPwd();
        String memberName = loginUser.getMemberName();
        String email = loginUser.getEmail();
    %>
</head>
<body>
<header><%@include file="/views/manager/memberHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <b>안녕</b>님 환영합니다. <br><br>
            <form action="" id="" method="get">
                <div class="form-group">
                    <label for="">아이디</label>
                    <p>
                       <%=memberName%>
                    </p>
                </div>

                <div class="form-group">
                    <label for="">비밀번호</label>
                    <input type="password" name="memberPwd" id="" placeholder="비밀번호 입력" required>
                </div>

                <div class="form-group">
                    <label for="">비밀번호 재확인</label>
                    <input type="password" name="memberPwdCheck" id="" placeholder="비밀번호 재입력" required>
                    <button type="button" id="" onclick="">변경</button>
                </div>

                <div class="form-group">
                    <label for="">닉네임</label>
                    <input type="text" name="" id="" placeholder="닉네임 입력" required>
                    <button type="button" id="" onclick="">중복확인</button>
                </div>

                <div class="form-group">
                    <label for="">이메일</label>
                    <input type="text" name="email_1" id="" placeholder="이메일 입력">
                    <select name="email_2" id="">
                        <option value="1">직접입력</option>
                        <option value="@naver.com">@naver.com</option>
                        <option value="@nate.com">@nate.com</option>
                        <option value="@gmail.com">@gmail.com</option>
                        <option value="@hanmail.net">@hanmail.net</option>
                    </select>
                    <button type="button" onclick="">인증번호 발송</button>
                    <div id="certified">
                        <input type="text" id="" placeholder="인증번호">
                        <button type="button" class="" id="" onclick="">인증번호 확인</button>
                    </div>
                </div>
                <button type="submit" onclick="" id="">변경사항 저장하기</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
