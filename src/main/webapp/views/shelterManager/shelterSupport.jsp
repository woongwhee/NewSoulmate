<%@ page import="java.util.List" %>
<%@ page import="tk.newsoulmate.domain.vo.Transfer" %>
<%@ page import="tk.newsoulmate.domain.vo.Support" %>
<%@ page import="static tk.newsoulmate.domain.vo.type.WithdrawStatus.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Support> supports = (List<Support>) request.getAttribute("supports");
    String shelterName = (String) request.getAttribute("shelterName");
    Transfer latestTransfer = request.getAttribute("latestTransfer") != null
            ? (Transfer) request.getAttribute("latestTransfer") : null;
%>

<html>

<head>
    <title>받은 후원함</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>

    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterSupport.css" rel="stylesheet">
</head>


<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>

<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <span id="shelterName"><%=shelterName%></span>님의 후원내역

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th>후원번호</th>
                        <th>후원자</th>
                        <th>후원일시</th>
                        <th>후원 금액(원)</th>
                        <th>전화번호</th>
                        <th>출금여부</th>
                        <th>출금</th>
                    </tr>
                    </thead>

                    <tbody>
                    <%if(supports == null || supports.isEmpty()) {%>
                    <tr>
                        <td colspan = "7">존재하는 후원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>
                    <%for(int i = 0; i < supports.size(); ++i) { %>
                    <tr>
                        <td><%=supports.get(i).getSupportNo()%></td>
                        <td><%=supports.get(i).getMemberName()%></td>
                        <td><%=supports.get(i).getPayTime() %></td>
                        <td><%=supports.get(i).getAmount() %></td>
                        <td><%=supports.get(i).getPhoneNumber() %></td>
                        <td>
                            <%switch (supports.get(i).getWithdrawStatus()) {
                                case PENDING: %>출금가능
                            <%break; case REQUESTED:%>출금 대기중
                            <% break; case DONE:%>출금완료 <% } %>
                        </td>
                        <td>
                            <% if (supports.get(i).getWithdrawStatus().equals(PENDING)) { %>
                            <input type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#staticBackdrop" value="출금 신청"
                                   onclick="setWithdrawInfo(<%=supports.get(i).getSupportNo()%>,
                                       <%=supports.get(i).getAmount()%>)">
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                    <% } %>
                    </tbody>
                </table>
            </div>

        </div>

        <div id="modal_all">
            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                 tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">출금 신청
                            </h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            보호소명 : <%=shelterName%><br>
                            <% if (latestTransfer == null) { %>
                            은행 <input type="text" class="modal-input" id="bank" placeholder="은행을 입력해주세요"><br>
                            계좌번호 <input type="text" class="modal-input" id="accountNumber" placeholder="계좌번호를 입력해주세요"><br>
                            예금주 <input type="text" class="modal-input" id="accountName" placeholder="예금주를 입력해주세요"><br>
                            <% } else { %>
                            은행 <input type="text" class="modal-input" id="bank" placeholder="은행을 입력해주세요" value="<%=latestTransfer.getBank()%>"><br>
                            계좌번호 <input type="text" class="modal-input" id="accountNumber" placeholder="계좌번호를 입력해주세요" value="<%=latestTransfer.getAccountNumber()%>"><br>
                            예금주 <input type="text" class="modal-input" id="accountName" placeholder="예금주를 입력해주세요" value="<%=latestTransfer.getName()%>"><br>
                            <% } %>
                            출금신청금액 : <span id="withdraw-amount"></span> 원<br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="btn-primary" onclick="withdraw()">출금 신청하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="pagingForm">
            <!--페이징바-->

        </div>

    </div>
</div>
</body>

<script>
    $(function (){
        $(".list-text").text("받은 후원함");
    })

    let supportNo = 0
    function setWithdrawInfo(_supportNo, _amount) {
        supportNo = _supportNo
        $("#withdraw-amount").html(_amount)
    }

    function withdraw() {
        let bank = $("#bank").val();
        let accountNumber = $("#accountNumber").val();
        let accountName = $("#accountName").val();
        if (bank == "" || accountNumber == "" || accountName == "") {
            alert("미입력된 정보가 있습니다.");
            return;
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/support/withdraw",
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                'supportNo': supportNo,
                'bank': bank,
                'accountNumber': accountNumber,
                'accountName': accountName
            }),
            success: function () {
                alert("출금 성공!");
                location.reload();
            },
            error: function () {
                alert("서버요청실패");
            }
        });
    }



</script>
</html>


