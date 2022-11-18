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

        let paid = 0;
        function requestPay() {
            var IMP = window.IMP;
            var code = "imp38841066";
            IMP.init(code);
            $.ajax({
                url: "${context}/support/number?shelterNo=341386201800004&amount=" + $("[name=amountCheck]:checked").val(),
                //url: "/support/verify?impUid=" + rsp.imp_uid + "&merchantUid=" + rsp.merchant_uid,
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
                        m_redirect_url : '${context}/supportPaymentPage'
                    }, function(rsp) {
                        if ( rsp.success ) {
                            console.log(rsp);
                            saveInfo(rsp.paid_amount);
                            $.ajax({
                                url: "${context}/support/verify?impUid=" + rsp.imp_uid + "&merchantUid=" + rsp.merchant_uid,

                                type: "get",
                                success: function () {
                                    //alert("원 후원에 성공했습니다.");
                                    alertInfo();
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
function alertInfo(){
    alert(paid+"원 후원 감사합니다.");
}
function saveInfo(amount){
    paid = amount;
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





</body>
</html>
