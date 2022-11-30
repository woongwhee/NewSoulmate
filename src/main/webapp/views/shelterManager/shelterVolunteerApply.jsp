<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Notice" %>
<%@ page import="tk.newsoulmate.domain.vo.Volunteer" %><%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-24
  Time: 오후 3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Volunteer v = (Volunteer)request.getAttribute("v");
%>

<html>
<head>
  <title>봉사신청서확인</title>
  <link href="<%=request.getContextPath()%>/css/shelterManager/shelterVolunteerApply.css" rel="stylesheet">
  <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>
<div class="headcontainer">
  <div id="right_view">
    <div id="volunteerApplyDetail">
      <table class="check-area">
        <tr>
          <th>신청 번호</th>
          <td>${v.volunteerNo}</td>
        </tr>

        <tr>
          <th>신청인</th>
          <td>${v.name}</td>
        </tr>

        <tr>
          <th>신청 아이디</th>
          <td>${v.memberId}</td>
        </tr>

        <tr>
          <th>보호소 이름</th>
          <td>${v.shelterName}</td>
        </tr>
        <tr>
          <th>봉사 희망 날짜</th>
          <td>${v.startDate}</td>
        </tr>
        <tr>
          <th>신청 날짜</th>
          <td>${v.applyDate}</td>
        </tr>
        <tr>
          <th>신청인 전화번호</th>
          <td>${v.telNumber}</td>
        </tr>
        <tr>
          <th>성별</th>
          <td>
            <c:if test="${v.gender eq 'M'}">남자</c:if>
            <c:if test="${v.gender eq 'F'}">여자</c:if>
          </td>
        </tr>
      </table>
      <div id="btn-box">
        <button id="list-btn" onclick="location.href='<%=request.getContextPath()%>/ShelterMessage;'">목록으로 돌아가기</button>
        <button type="button" id="msg-btn" data-toggle="modal" data-target="#msg">
          메세지 보내기
        </button>
      </div>
      <!-- 모달창 -->
      <div class="modal fade" id="msg" tabindex="-1" aria-labelledby="msgLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <div class="modal-title" id="msgLabel">메세지 입력</div>
            </div>
            <div class="modal-body">
              <div id="user-info">
                <p id="user-name">${v.name}</p>
                <p id="user-phone">${v.telNumber}</p>
              </div>
              <textarea id="msg-content">${v.shelterName}입니다. 신청하신 봉사가 접수되었습니다. ${v.startDate}에 방문해주시면 됩니다.-환승주인 WEB발송-</textarea>
            </div>
            <div class="modal-footer">
              <button type="button" id="back-btn" data-dismiss="modal">취소</button>
              <button type="button" id="send-btn">전송하기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  $(function () {
    $(".list-text").text("봉사신청서 확인");
    $('#send-btn').click(sendMsg);
  })
  let msg={
    toMemberNo:'${v.memberNo}',
    telNum:'${v.telNumber}',
    messageContent:null
  }
  function sendMsg(){
    msg.messageContent=$('#msg-content').val();
    if(msg.messageContent.length==0) {
      alert('문자내용을 작성해야합니다.');
      $('#msg-content').focus();
      return;
    }
    $.ajax({
      url:'sendMessage.st',
      type:'post',
      data:{msg:JSON.stringify(msg)},
      success:(result)=>{
        if(result>0){
          alert('메세지가 성공적으로 전송되었습니다.');
          location.href='ShelterMessage';
        }else{
          alert('메세지전송에 실패했습니다.');
        }
      }
    })
  }
</script>

</body>
</html>
