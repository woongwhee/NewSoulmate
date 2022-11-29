<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2022-11-08
  Time: 오후 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>) request.getAttribute("sList");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>보호소리스트</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>

    <link href="<%=request.getContextPath()%>/css/shelter/shelterList.css" rel="stylesheet">
</head>
<body>
<%@ include file="/views/template/menubar.jsp"%>

<div id="content">
    <div id="shelter"></div>
    <table class="list-area">
        <thead>
        <tr>
            <th>보호소명</th>
            <th>보호소 주소</th>
            <th>보호소 전화번호</th>
        </tr>
        </thead>
        <tbody>
        <%for(Shelter s : sList){ %>
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
<script>
    $(function(){
        $.ajax({
            url: "ShelterSearch",
            success: function (result) {
                $("#shelter").html(result);
            }
        })
    })

    $(".list-area>tbody>tr").click(function(){
        // 클릭시 해당 shelter_no 를 넘김
        //해당 tr 요소의 자손들중 첫번째 td영역의 내용이 필요
        let shelterNo = $(this).children().eq(0).text().trim();
        // textnode를 가져옴
        console.log(shelterNo);

        location.href = '<%=request.getContextPath()%>/ShelterDetail?shelterNo='+shelterNo;
    })

</script>
<%@include file="/views/template/footer.jsp"%>
</body>
</html>