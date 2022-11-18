<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>후원하기</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/support/supportPayment.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="http://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>

    <%
        Support support = (Support) request.getAttribute("support");
        Member member = (Member) request.getAttribute("loginUser");
    %>


</head>
<body>

<header><%@include file="/views/template/menubar.jsp"%></header>


<div id="container">

    <div class="support_notice">
        <div id="notice_left_box">
            <h1>후원하기</h1>
        </div>
        <div id="notice_right_box">
            <p>"모든 생명은 보호받고 존중받을 권리가 있습니다"<br>
                환승주인에서는 사람과 동물이 생태적으로, 윤리적 조화를 이루며 살아가는 세상을 만들기 위해<br>
                다방면에서 활동을 펼쳐가고 있습니다.
            </p>
        </div>
    </div>

    <div id="hr_1">
        <hr>
    </div>

    <div class="support-wrap">
        <h2>후원 정보</h2>
        <table id="support-amount">
            <tr>
                <td>
                    <h4>후원 항목</h4>
                </td>
                <td>일반후원</td>
            </tr>
            <tr>
                <td>
                    <h4>후원 금액</h4>
                </td>
                <td>
                    <div>
                        <input name="amountCheck" type="radio" value="10000">10,000원
                        <input name="amountCheck" type="radio" value="20000">20,000원
                        <input name="amountCheck" type="radio" value="30000">30,000원
                        <br>
                        <input name="amountCheck" type="radio" value="50000">50,000원
                        <input name="amountCheck" type="radio" value="100000">100,000원
                        <input name="amountCheck" type="radio" value="100">100원
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <hr>

    <div class="">
        <h2>결제 정보</h2>
        <br>
        <form action="" value="" method="post">
            <table id="">
                <tr>
                    <td><label for="">아이디</label></td>
                </tr>
                <tr>
                    <td><div id="memberId">${loginUser.memberId}</div></td>
                </tr>
                <tr>
                    <td><label for="">이름</label></td>
                </tr>
                <tr>
                    <td><div id="memberName">${loginUser.memberName}</div></td>
                </tr>
                <tr>
                    <td><label for="">휴대전화</label></td>
                </tr>
                <tr>
                    <td><div id="phone">${loginUser.phone}</div></td>
                </tr>
                <tr>
                    <td><label for="">이메일</label></td>
                </tr>
                <tr>
                    <td><div id="email">${loginUser.email}</div></td>
                </tr>

            </table>

            <div class="">
                <label><input type="checkbox" name="" class="required_checked">개인정보 처리방침 및 이용약관 동의</label>
            </div>
        </form>

    </div>

    <button id="payBtn" onclick="requestPay();">결제하기</button>
</div>

    <script>
        // imp_uid 로 아임포트쪽 요청에서 거래정보 조회
        // 그러면 서버는 merchant_uid(후원번호) 로 DB 조회
        // 그리고 그 두개의 Price(amount) 를 비교해서 검증후 맞으면 완료 상태로 변경

        function requestPay() {
            var IMP = window.IMP;
            var code = "imp38841066";
            IMP.init(code);

            $.ajax({
                url: "${context}/support/number?shelterNo=341386201800004&amount=" + $("[name=amountCheck]:checked").val(),
                //url: "${context}/support/verify?impUid=" + rsp.imp_uid + "&merchantUid=" + rsp.merchant_uid,
                type: "post",
                success: function(data) {
                    IMP.request_pay({
                        pg : 'html5_inicis', // pg 사 선택
                        pay_method : 'card',
                        merchant_uid : data,
                        name : "환승주인 후원하기",
                        amount : $("[name=amountCheck]:checked").val(),
                        buyer_email : $('#email').val(),
                        buyer_name : $('#memberName').val(),
                        buyer_tel : $('#phone').val(),
                        buyer_addr : '서울특별시 강남구 도곡동',
                        buyer_postcode : '06275',
                        m_redirect_url : 'https://www.yourdomain.com/payments/complete'
                    }, function(rsp) {
                        if ( rsp.success ) {
                            console.log(rsp);

                            $.ajax({
                                url: "${context}/support/verify?impUid=" + rsp.imp_uid + "&merchantUid=" + rsp.merchant_uid,
                                type: "get",
                                success: function () {
                                    alert(rsp.paid_amount + " 원 후원에 성공했습니다!")
                                },
                                error: function () {
                                    alert("후원에 실패했습니다!")
                                }
                            });
                        }
                        else {
                            alert('결제에 실패하였습니다. 에러내용 : ' + rsp.error_msg);
                        }
                    });
                },
                error: function() {
                    console.log("서버호출실패");
                }
            });
        }
    </script>

    <script>
        $("#payBtn").click(function () {
            if ($('.required_checked:checked').length != $('.required_checked').length) {
                alert("개인정보항목에 동의해주세요.");
                return;
            }
        });
    </script>



    <%-- <script>

         function requestPay() {

            /* const price = Number($(".value"));*/
             var IMP = window.IMP;
             var code = "imp38841066";
             IMP.init(code);

             // 결제요청
             IMP.request_pay({
                 pg : 'html5_inicis', // pg 사 선택
                 pay_method : 'card',
                 merchant_uid : 'merchant_' + new Date().getTime(),
                 name : "환승주인 후원하기",
                 amount : $("[id=chk]:checked").val(),

                 supporter_email :$('#Email').val(),
                 supporter_name : $('#memberName').val(),
                 supporter_phone : $('#Phone').val(),
                 m_redirect_url : ''
             }, function(rsp) {

                 if (rsp.success ) {
                     var msg = "결제가 완료되었습니다.";

                     $.ajax({
                         // todo : url db에 저장할 url payment 정보
                             url: '${supportHistory}', //request에 로그인 유저가
                             type: 'post',
                             // TODO : db에저장할값들
                             data: {authCode: inputValue},
                             success: (result) => {
                                 if (result == 1) {

                                     var msg = "결제가 완료되었습니다.";
                                     msg += '고유ID : ' + rsp.imp_uid;
                                     msg += '상점 거래ID : ' + rsp.merchant_uid;
                                     msg += '결제 금액 : ' + rsp.paid_amount;
                                     msg += '카드 승인번호 : ' + rsp.apply_num;
                                     alert("결제완료")
                                     supporter_email: buyer_email,
                                         supporter_name: ${loginUser.memberName},
                                         supporter_phone: buyer_phone,
                                     s_msg: s_msg,
                                     o_shipno: rsp.merchant_uid,
                                     o_paidAmount: rsp.paid_amount,
                                     o_paytype: rsp.pay_method


                                 } else if (result == 0) {
                                     // TODO: 환불request api
                                     alert("실패")
                                 }
                             }, error: function () {
                             alert("서버요청실패");
                         }
                         })
                 }
                 else {
                     var msg = '결제에 실패하였습니다. 에러내용 : ' + rsp.error_msg
                 }
                 alert(msg);
             });
         }


     </script>



     <script>

         // 2번


         $("#place-order").click(function(){
             for(var i=0; i<document.purchaseinput.elements.length; i++)
             {
                 if(document.purchaseinput.elements[i].value == "")
                 {
                     alert("모든 값을 입력 하셔야 합니다. ");
                     document.purchaseinput.elements[i].focus();
                     return false;
                 }
             }
             let m_email = $("#m_email").val();
             let s_name = $("#s_name").val();
             let s_addr = $("#roadFullAddr").val();
             let s_phone = $("#s_phone").val();
             let s_msg = $("#s_msg").val();
             let s_zipNo = $("#s_zipNo").val();
             //alert(m_email +s_name +s_addr +s_phone +s_msg +s_zipNo);

             var IMP = window.IMP; // 생략가능
             IMP.init('imp38841066');	//아임포트 관리자계정
             //결제 시스템을 실행시키는 함수
             IMP.request_pay({
                 pay_method: 'card',
                 merchant_uid: 'merchant_' + new Date().getTime(),
                 name: '주문명 : 환승주인 후원하기',
                 amount: 100,	//테스트 완료 후 가격정보 넣기
                 buyer_email: m_email,
                 buyer_name: s_name,
                 buyer_tel: s_phone,
                 buyer_addr: s_addr,
                 buyer_postcode: s_zipNo
             }, function (rsp) {
                 console.log(rsp);
                 if (rsp.success) {
                     var msg = '결제가 완료되었습니다.';
                     msg += '고유ID : ' + rsp.imp_uid;
                     msg += '상점 거래ID : ' + rsp.merchant_uid;
                     msg += '결제 금액 : ' + rsp.paid_amount;
                     msg += '카드 승인번호 : ' + rsp.apply_num;
                     let purchaseVo = {
                         m_email: m_email,
                         s_name: s_name,
                         s_addr: s_addr,
                         s_phone: s_phone,
                         s_msg: s_msg,
                         s_zipNo: s_zipNo,
                         o_shipno: rsp.merchant_uid,
                         o_paidAmount: rsp.paid_amount,
                         o_paytype: rsp.pay_method
                     }
                     // 컨트롤러에 데이터를 전달하여 DB에 입력
                     // 결제내역을 사용자에게 보여주기 위해 필요함.
                     $.ajax({
                         url : "supporthistory",
                         type : "get",
                         data : purchaseVo,
                         dataType : "text",
                         success : function(result){
                             if(result == "y") {
                                 alert(msg);
                                 location.href = "orderComplete.do";
                             }else{
                                 alert("데이터베이스입력실패");
                                 return false;
                             }
                         },
                         error : function(a,b,c){}
                     });
                 } else {
                     var msg = '결제에 실패하였습니다.';
                     msg += '에러내용 : ' + rsp.error_msg;
                 }
                 alert(msg);
             });
         });



     </script>--%>






    <%--<script>

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

                    console.log(rsp);
                }
                else {
                    var msg = '결제에 실패하였습니다. 에러내용 : ' + rsp.error_msg
                }
                alert(msg);
            });
        }


    </script>--%>




    <%--   <script>

           function requestPay() {
               var IMP = window.IMP;
               var code = "imp38841066";
               IMP.init(code);

               // 결제요청
               IMP.request_pay({

                   pg : 'kakaopay',
                   pay_method : 'card', //생략 가능
                   merchant_uid: 'merchant_' + new Date().getTime(), // 상점에서 관리하는 주문 번호
                   name : "환승주인 후원하기",
                   amount : 100,
                   buyer_email : 'newsoulmatemaster@gmail.com',
                   buyer_name : 'memberName',
                   buyer_tel : 'Phone',
                   buyer_addr : '서울특별시 강남구 삼성동',
                   buyer_postcode : '12345'

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

       </script>--%>





</body>
</html>
