<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>)request.getAttribute("sList");

    Support support = (Support)request.getAttribute("support");
    Member member = (Member)request.getAttribute("loginUser");
%>

<html>
<head>
    <title>후원하기</title>
    <%@include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/support/supportPayment.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="http://service.iamport.kr/js/iamport.payment-1.1.8.js"></script>
</head>

<body>
<header>
    <%@include file="/views/template/menubar.jsp" %>
</header>

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
                        <%--<input name="amountCheck" type="number" id="inputAmount">--%>
                        <%--input text 값 value로 가져오기?--%>
                    </div>
                </td>
            </tr>
        </table>
    </div>


    <hr>


    <div class="shelter">

        <h2>보호소 리스트</h2>
        <h4>후원할 보호소를 선택해 주세요</h4>

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            보호소 선택
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">


                        <table class="list-area" border="1">
                            <thead>
                            <tr>
                                <th>보호소명</th>
                                <th>보호소 주소</th>
                                <th>보호소 전화번호</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%for (Shelter s : sList) { %>
                            <tr>
                                <td style="display:none">
                                    <%= s.getShelterNo()%>
                                </td>
                                <td>
                                    <%= s.getShelterName() %>
                                </td>
                                <td>
                                    <%= s.getShelterAddress() %>
                                </td>
                                <td>
                                    <%= s.getShelterLandline() %>
                                </td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>




        <br><br>
        <h3>선택된 보호소</h3>
        <span id="selected-shelter"></span>
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
                    <td>
                        <div id="memberId">${loginUser.memberId}</div>
                    </td>
                </tr>
                <tr>
                    <td><label for="">이름</label></td>
                </tr>
                <tr>
                    <td>
                        <div id="memberName">${loginUser.memberName}</div>
                    </td>
                </tr>
                <tr>
                    <td><label for="">휴대전화</label></td>
                </tr>
                <tr>
                    <td>
                        <div id="phone">${loginUser.phone}</div>
                    </td>
                </tr>
                <tr>
                    <td><label for="">이메일</label></td>
                </tr>
                <tr>
                    <td>
                        <div id="email">${loginUser.email}</div>
                    </td>
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
    let selectedShelterNo = 0;
    $(".list-area>tbody>tr").click(function () {
        let shelterNo = $(this).children().eq(0).text().trim();
        selectedShelterNo = shelterNo;
        $("#selected-shelter").html($(this).children().eq(1).text().trim())
    })

    // 1. 서버로부터 후원번호를 받아와야함 (서버에서 하는 이유: amount에대한 기록을 검증용도로 사용 서버로부터 만들어야 중복없이 사용) => url 호출부분
    // 2. 후원번호를 만들어주기 위해서 서버에서 DB에 새로운 row (memberno, shelterno, amount) + 후원번호 생성
    // => 정보가 누락되면 100원 결제해놓고 10000원을 후원한척 할 수 있으니까
    // 3. 후원번호를 기준으로 실제 결제 발생 iamport 받는정보 : imp_uid(Iamport에서 관리하는 식별자), merchant_uid (내가 고유하게 넘겨준)
    // => 이걸 아래 ajax에서 넘겨줌

    // imp_uid 로 아임포트쪽 요청에서 거래정보 조회
    // 서버는 merchant_uid(후원번호) 로 DB 조회
    // 두개의 Price(amount) 를 비교해서 검증후 맞으면 완료(DONE) 상태로 변경

    let paid = 0;

    function requestPay() {
        if (selectedShelterNo == 0) {
            alert("후원할 보호소를 선택해주세요!");
            return;
        }

        var IMP = window.IMP;
        var code = "imp38841066";
        IMP.init(code);
        $.ajax({
            url: "${context}/support/number?shelterNo=" + selectedShelterNo + "&amount=" + $("[name=amountCheck]:checked").val(),
            //url: "/support/verify?impUid=" + rsp.imp_uid + "&merchantUid=" + rsp.merchant_uid,
            type: "post",
            success: function (data) {
                IMP.request_pay({
                    pg: 'html5_inicis', // pg 사 선택
                    pay_method: 'card',
                    merchant_uid: data,
                    name: "환승주인 후원하기",
                    amount: $("[name=amountCheck]:checked").val(),
                    buyer_email: $('#email').val(),
                    buyer_name: $('#memberName').val(),
                    buyer_tel: $('#phone').val(),
                    m_redirect_url: '${context}/supportHistoryPage'
                }, function (rsp) {
                    if (rsp.success) {
                        // 결제가 성공한다면
                        console.log(rsp); // imp_uid, merchant_uid
                        saveInfo(rsp.paid_amount);

                        // 검증
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
                    } else {
                        alert('결제에 실패하였습니다. 에러내용 : ' + rsp.error_msg);
                    }
                });
            },
            error: function () {
                console.log("서버호출실패");
            }
        });
    }

    function alertInfo() {
        alert(paid + "원 후원 감사합니다.");
        location.href = '<%=request.getContextPath()%>/supportHistoryPage';
    }

    function saveInfo(amount) {
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
