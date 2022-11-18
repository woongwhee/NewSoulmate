<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>후원결제</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/support/supportPayment.css" rel="stylesheet">
    <script src="http://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>

    <%
        Support support = (Support) request.getAttribute("support");
        Member member = (Member) request.getAttribute("loginUser");
    %>


</head>
<body>


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
        <table id="">
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
                    <div id="chk">
                        <input type="checkbox" value="10,000">10,000원
                        <input type="checkbox" value="20,000">20,000원
                        <input type="checkbox" value="30,000">30,000원
                        <br>
                        <input type="checkbox" value="50,000">50,000원
                        <input type="checkbox" value="100,000">100,000원
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <hr>

    <div class="">
        <h2>결제 정보</h2>
        <p>후원 내역 확인 등 마이페이지 이용 시 꼭 필요한 정보이니, 명확하게 기입해 주세요.</p>
        <form action="" value="" method="post">
            <table id="">
                <tr>
                    <td><label for="">이름</label></td>
                    <td><input type="text" name="memberName" id="memberName" value= ${member.memberName} placeholder="내용을 입력해주세요" ></td>
                </tr>
                <tr>
                    <td><label for="">휴대전화</label></td>
                    <td>
                        <select name="" id="">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="019">019</option>
                        </select>
                        <input type="number" name="" id="" placeholder="-빼고 입력" maxlength="8"
                               oninput="numberMaxLength(this);" >
                    </td>
                </tr>
                <tr>
                    <td><label for="">이메일</label></td>
                    <td><input type="email" name="" id="" ></td>
                </tr>
                <tr>
                    <td><label for="">카드번호</label></td>
                    <td><input type="number" name="" id="" placeholder="내용을 입력해주세요" ></td>
                </tr>
                <tr>
                    <td><label for="">카드주명</label></td>
                    <td><input type="text" name="" id="" placeholder="내용을 입력해주세요" ></td>
                </tr>
                <tr>
                    <td><label for="">생년월일</label></td>
                    <td><input type="number" name="" id="" placeholder="생년월일 6자리 입력" maxlength="6"
                               oninput="numberMaxLength(this)" ></td>
                </tr>
            </table>

            <div class="">
                <label><input type="checkbox" name="" class="">개인정보 처리방침 및 이용약관 동의</label>
            </div>
        </form>

    </div>

    <button onclick="requestPay();">결제하기</button>


    <script>

        $("[id=chk]:checked").val();

        function requestPay() {
            const price = Number($(".value"));
            var IMP = window.IMP;
            var code = "imp38841066";
            IMP.init(code);

            // 결제요청
            IMP.request_pay({
                pg : 'html5_inicis', // pg 사 선택
                pay_method : 'card',
                merchant_uid : 'merchant_' + new Date().getTime(),
                name : "환승주인 후원하기",
                amount : price,
                buyer_email :,
                buyer_name : $('#memberName').val(),
                buyer_tel : ,
                m_redirect_url : ''
            }, function(rsp) {

                if (rsp.success ) {
                    var msg = "결제가 완료되었습니다.";

                    $.ajax({
                        // todo : url db에 저장할 url payment 정보
                            url: 'payment', //request에 로그인 유저가
                            type: 'post',
                            // TODO : db에저장할값들
                            data: {authCode: inputValue},
                            success: (result) => {
                                if (result == 1) {
                                    alert("결제완료")
                                } else if (result == 0) {
                                    // TODO: 환불request api
                                    alert("실패")
                                }
                            }, error: function () {
                            alert("서버요청실패");
                            checkMail = 0;
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




    <script>
        function numberMaxLength(e) {
            if (e.value.length > e.maxLength) {
                e.value = e.value.slice(0, e.maxLength);
            }
        }
    </script>

<%--    <script>
        function submitFormData() {
            var data = {
                'orderer_name': null,
                'recipent_name': null,
                'email': null,
                'post_code': null,
                'recipent_address1': null,
                'recipent_address2': null,
                'recipent_number': null,
                'orderer_number': null,
                'order_request': null,
            }
            data.orderer_name = form.orderer_name.value
            data.recipent_name = form.recipent_name.value
            data.email = form.email.value
            data.post_code = form.post_code.value
            data.recipent_address1 = form.recipent_address1.value
            data.recipent_address2 = form.recipent_address2.value
            data.recipent_number = form.recipent_number.value
            data.orderer_number = form.orderer_number.value
            data.order_request = form.order_request.value
            var url = 'payment/';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    "X-CSRFToken": csrftoken,

                },
                body: JSON.stringify({'data': data})
            }).then((response) => response.json())
                .then((data) => {
                    obj = JSON.parse(data)

                    requestPay(obj['merchant_uid'], obj['name'], obj['amount'], obj['buyer_email'],
                        obj['buyer_name'], obj['buyer_tel'], obj['buyer_addr'], obj['post_code'])
                })
        }


    </script>
        --%>



</body>
</html>
