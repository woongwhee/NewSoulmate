<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-14
  Time: 오후 7:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>payment</title>
  <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script src="http://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>
</head>

<body>

<button onclick="requestPay();">결제하기</button>

<script>

  function requestPay() {
    var IMP = window.IMP;
    var code = "imp38841066";
    IMP.init(code);

    // 결제요청
    IMP.request_pay({
      pg : 'html5_inicis', // pg 사 선택
      pay_method : 'card',
      merchant_uid : 'merchant_' + new Date().getTime(),
      name : "환승주인 후원하기",
      amount : 100,
      buyer_email : 'ahhhaaah@siot.do',
      buyer_name : '후원자',
      buyer_tel : '010-6510-5678',
      buyer_addr : '서울특별시 강남구 도곡동',
      buyer_postcode : '06275',
      m_redirect_url : 'https://www.yourdomain.com/payments/complete'
    }, function(rsp) {
      if ( rsp.success ) {
        var msg = "결제가 완료되었습니다.";
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
      }
      else {
        var msg = '결제에 실패하였습니다. 에러내용 : ' + rsp.error_msg
      }
      alert(msg);
    });
  }


</script>

</body>

</html>

</body>
</html>
