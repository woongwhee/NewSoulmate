<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.*" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2022-11-08
  Time: 오후 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>유기동물목록</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelter/noticeList.css" rel="stylesheet">
</head>
<body>
<%@ include file="/views/template/menubar.jsp"%>


<div id="content">
    <div id="animal-search-box">
        <div id="search-box-table-div">
            <table id="search-box-table">
                <tr>
                    <th>시도</th>
                    <td>
                        <select name="cityNo" id="cityNo" onchange="choice();">
                            <option value="0">--전체--</option>
                            <c:forEach items="${cList}" var="c">
                                <option value="${c.cityNo}">${c.cityName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>시군구</th>
                    <td>
                        <select name="villageNo" id="villageNo" onchange="choiceShel();">
                            <option value="" selected disabled hidden>--전체--</option>
                        </select>
                    </td>
                    <th>보호센터</th>
                    <td>
                        <select name="shelterNo" id="shelterNo">
                            <option value="" selected disabled hidden>--전체--</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>품종</th>
                    <td>
                        <select name="species" id="species">
                            <option value="" selected disabled hidden>--전체--</option>
                            <option value="DOG">개</option>
                            <option value="CAT">고양이</option>
                            <option value="ANOTHER">기타</option>
                        </select>
                    </td>
                    <th>중성화여부</th>
                    <td>
                        <select name="neuter" id="neuter">
                            <option value="" selected disabled hidden>--전체--</option>
                            <option value="Y">예</option>
                            <option value="N">아니오</option>
                            <option value="U">미상</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <div id="noticeList-btn-box">
            <button type="button" id="noticeSearch">조회</button>
        </div>
    </div>
    <div id="count-area">총 조회 수 : </div>
    <div id="notice-area">

    </div>
</div>
    <script src="${context}/js/shelter/noticeList.js"></script>

    <footer><%@ include file="/views/template/footer.jsp"%></footer>

</body>
</html>