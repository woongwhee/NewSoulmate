<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>) request.getAttribute("sList");

    Support support = (Support) request.getAttribute("support");
    Member member = (Member) request.getAttribute("loginUser");
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
            <p>후 원 하 기</p>
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
        <p>후원 정보</p>
        <table id="support-amount">
            <tr>
                <td>
                    <div class="support_list">후원 항목</div>
                </td>
                <td>
                    <div class="support_answer"> 일반후원</div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="support_list">후원 금액</div>
                </td>
                <td>
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-danger">
                            <input type="radio" name="amountCheck" value="5000"> 5000원
                        </label>
                        <label class="btn btn-danger">
                            <input type="radio" name="amountCheck" value="10000"> 10,000원
                        </label>
                        <label class="btn btn-danger">
                            <input type="radio" name="amountCheck" value="20000"> 20,000원
                        </label>
                        <label class="btn btn-danger">
                            <input type="radio" name="amountCheck" value="30000"> 30,000원
                        </label>
                        <label class="btn btn-danger">
                            <input type="radio" name="amountCheck" value="50000"> 50,000원
                        </label>
                        <label class="btn btn-danger">
                            <input type="radio" name="amountCheck" value="100000"> 100,000원
                        </label>
                    </div>
                </td>
            </tr>
        </table>
    </div>


    <hr>


    <div class="shelter">

        <p>보호소 리스트</p>
        <table>
            <tr>
                <td> <div class="support_list">후원할 보호소를 선택해 주세요</div></td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        보호소 선택
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">보호소 선택</h5>
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
                </td>
            </tr>
            <tr>
                <td>
                    <div class="support_list">선택된 보호소</div>
                </td>
                <td>
                    <span class="support_answer" id="selected-shelter"></span>
                </td>
            </tr>
        </table>
    </div>


    <hr>
    <div class="payment">
        <p>결제 정보</p>
        <form action="" value="" method="post">
            <table id="">
                <tr>
                    <td><label for="" class="support_list">아이디</label></td>
                    <td>
                        <div id="memberId" class="support_answer">${loginUser.memberId}</div>
                    </td>
                </tr>
                <tr>
                    <td><label for="" class="support_list">이름</label></td>
                    <td>
                        <div id="memberName" class="support_answer">${loginUser.memberName}</div>
                    </td>
                </tr>
                <tr>
                    <td><label for="" class="support_list">휴대전화</label></td>
                    <td>
                        <div id="phone" class="support_answer"> ${loginUser.phone}</div>
                    </td>
                </tr>
                <tr>
                    <td><label for="" class="support_list">이메일</label></td>
                    <td>
                        <div id="email" class="support_answer">${loginUser.email}</div>
                    </td>
                </tr>
            </table>

            <div id="required_checkedBox">
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
        $("#selected-shelter").html($(this).children().eq(1).text().trim());
        $(".modal-footer>button").click();
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
            alert("후원할 보호소를 선택해주세요.");
            return;
        }
        var IMP = window.IMP;
        var code = "imp38841066";
        IMP.init(code);
        $.ajax({
            url: "${context}/support/number?shelterNo=" + selectedShelterNo + "&amount=" + $("[name=amountCheck]:checked").val(),
            //1. 후원번호 생성요청을 해서 서버로부터 받음 데이터를 받는곳:supportnumberCreateController
            type: "post",
            success: function (data) {
                IMP.request_pay({
                    pg: 'html5_inicis', // pg 사 선택
                    pay_method: 'card',
                    merchant_uid: data, // 3. 받은 응답 후원번호 실제로 pg사 카드결제가 되는 번호
                    name: "환승주인 후원하기",
                    amount: $("[name=amountCheck]:checked").val(),
                    m_redirect_url: '${context}/supportHistoryPage'
                }, function (rsp) {
                    if (rsp.success) {
                        // 결제가 성공한다면
                        /*console.log(rsp);*/ // imp_uid, merchant_uid
                        saveInfo(rsp.paid_amount);
                        // 검증
                        $.ajax({
                            url: "${context}/support/verify?impUid=" + rsp.imp_uid
                                + "&merchantUid=" + rsp.merchant_uid,
                            // 4. 실제로 결제요청이 일어나면 식별자가 impuid를 돌려줌 callbacksusseecxx
                            // imp_uid는 import에서 생성된 번호아 merchant_uid는 내가 만든 번호 이 금액이 같은지 검증하는게 verify - supportVerifyController
                            type: "get",
                            success: function () {
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
        }
    });


    $("#payBtn").click(function () {
        if ($("[name=amountCheck]:checked").val() == null){
            alert("후원 금액을 선택해주세요.");
        }
    });


</script>

<footer>
    <%@include file="/views/template/footer.jsp" %>
</footer>

</body>
</html>
