<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Notice" %><%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-24
  Time: 오후 3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Subscription s = (Subscription) request.getAttribute("s");
  Notice n = (Notice)request.getAttribute("n");
%>

<html>
<head>
  <title>입양신청서확인</title>
  <link href="<%=request.getContextPath()%>/css/shelterManager/shelterAdoptApply.css" rel="stylesheet">
  <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>

<div class="headcontainer">
  <div id="right_view">
    <div id="adoptApplyDetail">
      <table class="check-area" style="text-align: center">
        <tr>
          <th>공고 번호</th>
          <td>${n.desertionNo}</td>
        </tr>

        <tr>
          <th>신청인</th>
          <td>${s.name}</td>
        </tr>

        <tr>
          <th>보호소 이름</th>
          <td>${n.careNm}</td>
        </tr>
        <tr>
          <th>보호소 연락처</th>
          <td>${n.officetel}</td>
        </tr>
        <tr>
          <th>입양 희망일</th>
          <td>${s.wishDate}</td>
        </tr>
        <tr>
          <th>신청인 전화번호</th>
          <td>${s.telNum}</td>
        </tr>
        <tr>
          <th>신청일자</th>
          <td>${s.subDate}</td>
        </tr>
        <tr>
          <th>성별</th>
          <td>
            <c:if test="${s.gender eq 'M'}">남자</c:if>
            <c:if test="${s.gender eq 'F'}">여자</c:if>
          </td>
        </tr>
      </table>

      <div id="adoptApplyListView">
        <div class="adoptApplyAll">입양을 결정하게 된 이유</div>
        <div class="adoptApplyAnswer">${s.adoptReason}</div>
        <div class="adoptApplyAll">가족 구성원의 반대가 없었는지?</div>
        <div class="adoptApplyAnswer">${s.familyAgreement}</div>
        <div class="adoptApplyAll">입양해 간 아이가 많이 아프다면?</div>
        <div class="adoptApplyAnswer">${s.whenSick}</div>
        <div class="adoptApplyAll">평생 사랑으로 책임질 수있는지?</div>
        <div class="adoptApplyAnswer">${s.bigDuty}</div>
      </div>


      <div id="btn-box">
        <form action="${context}/AdoptApplyRead" type="post">
          <input type="hidden" name="sno" value="${s.subNo}">
          <input type="hidden" name="subRead" value="${s.subRead}">
          <button id="list-btn" onclick="">처리여부</button>
        </form>
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
                <p id="user-name">${s.name}</p>
                <p id="user-phone">${s.telNum}</p>
              </div>
              <textarea id="msg-content">${n.careNm}입니다. 입양신청이 접수되었습니다. ${s.wishDate}에 방문해주시면 됩니다.-환승주인 WEB발송-</textarea>
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
    $(".list-text").text("입양신청서 확인");
    $('#send-btn').click(sendMsg);
  })

  let msg={
    toMemberNo:'${s.memberNo}',
    TelNum:'${s.telNum}',
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
