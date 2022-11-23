<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-23
  Time: 오후 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>후원관리- 전체 후원내역</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>

    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/manager/manageSupportHistory.css" rel="stylesheet">


</head>
<body>
<header>
    <%@include file="/views/manager/managerHeader.jsp" %>
</header>

<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">
            <div class="box">
                <span>보호소명</span>

                <span>조회기간</span>
                <span><input type="date"></span>
                <span>~</span>
                <span><input type="date"></span>
                <span><input type="submit" value="조회"></span>
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th width="20px">후원번호</th>
                        <th>보호소명</th>
                        <th>후원자</th>
                        <th>결제번호</th>
                        <th>후원 금액(원)</th>
                        <th>후원일시</th>
                        <th>결제상태</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>

                        <td>

                        </td>
                    </tr>
                    </tbody>

                </table>

                <div id="pagingForm">
                    <!--페이지네이션-->


                </div>


                -->


            </div>

        </div>
    </div>
</div>


</body>
</html>