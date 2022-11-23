<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>받은 후원함</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>

    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterSupport.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th width="20px">No</th>
                        <th>후원자</th>
                        <th>후원일시</th>
                        <th>후원 금액(원)</th>
                        <th>결제정보</th>
                        <th>전화번호</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!--보여질 페이지 수 지정-->
<%--                    <c:forEach var="board" items="${}">--%>
                        <tr>
                            <td>
                                <!--번호-->1
                            </td>
                            <td>
                                <!--후원자 -->김모모
                            </td>
                            <td>
                                <!--후원일시-->2022-10-22 10:22
                            </td>
                            <td>
                                <!--후원 금액(원)-->20,000
                            </td>
                            <td>
                                <!--결제저어보-->언니안녕..
                            </td>
                            <td>
                                <!--전화번호-->파이팅..
                            </td>
                        </tr>

<%--                    </c:forEach>--%>
                    </tbody>
                </table>

                <div id="modal_all">
                    <!--출금승인-->
                    <input type="submit" class="btn btn-primary" data-bs-toggle="modal"
                           data-bs-target="#staticBackdrop" value="출금 신청">
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
                                    보호소명 <input type="text" class="modal-input" id="" placeholder="내용을 입력해주세요"><br>
                                    은행 <input type="text" class="modal-input" id="" placeholder="내용을 입력해주세요"><br>
                                    계좌번호 <input type="text" class="modal-input" id="" placeholder="내용을 입력해주세요"><br>
                                    예금주 <input type="text" class="modal-input" id="" placeholder="내용을 입력해주세요"><br>
                                    출금신청금액 <input type="text" class="modal-input" id="" placeholder="내용을 입력해주세요"><br>
                                    출금가능금액
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="btn-primary">출금 신청하기</button>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="pagingForm">
                    <!--페이지네이션-->

                </div>
            </div>
        </div>
    </div>

</body>
</html>
