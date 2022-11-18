<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="tk.newsoulmate.domain.vo.City" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Village" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2022-11-09
  Time: 오후 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>) request.getAttribute("sList");
    ArrayList<City> cList = (ArrayList<City>)request.getAttribute("cList");
    ArrayList<Village> vList = (ArrayList<Village>)request.getAttribute("vList");
%>

<html>
<head>
    <title>보호소 정렬</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelter/updateShelterList.css" rel="stylesheet">

</head>
<body>
<%@include file="/views/template/menubar.jsp"%>
<div id="content">
    <div id="search">
        <form action="<%=request.getContextPath()%>/updateShelter" method="get">
            <table id="searchBox">
                <tr>
                    <td>시도</td>
                    <th>
                        <select name="cityNo" id="mainCategory" onchange="choice();">
                            <option value="0">--전체--</option>
                            <% for(City c: cList){ %>
                            <option value="<%=c.getCityNo()%>">
                                <%=c.getCityName()%>
                            </option>
                            <% } %>
                        </select>
                    </th>

                    <td>시군구</td>
                    <th>
                        <select name="villageNo" id="subCategory">
                            <option value="0">--전체--</option>
                        </select>
                    </th>
                    <td>
                        <button type="submit">조회</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <table class="list-area">
        <thead>
        <tr>
            <th>보호소명</th>
            <th>보호소 주소</th>
            <th>보호소 전화번호</th>
        </tr>
        </thead>
        <tbody>
        <% if(sList.isEmpty()) { %>
        <tr>
            <td id="noList" colspan="6">조회된 리스트가 없습니다.</td>
        </tr>
        <% }else{ %>
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
        <% } %>
        </tbody>
    </table>
</div>


    <script>

            function choice() {
                // 메인페이지 선택시 서브쿼리 삭제
                $("#subCategory").children().remove();
                // 삭제후 선택 option 넣기
                $.ajax({
                    url: "jqAjaxCity",
                    data: {city: $("#mainCategory").val()},
                    success: function (result) {
                        let str = '<option value="0">전체</option>';
                        for (let i = 0; i < result.length; i++) {
                            str += "<option value='" + result[i].villageNo + "'>" + result[i].villageName + "</option>"
                        }
                        $("#subCategory").html(str);

                    }
                })
            }

    </script>
<script>
    $(".list-area>tbody>tr").click(function(){
        // 클릭시 해당 shelter_no 를 넘김
        //해당 tr 요소의 자손들중 첫번째 td영역의 내용이 필요
        let shelterNo = $(this).children().eq(0).text().trim();
        // textnode를 가져옴
        console.log(shelterNo);

        location.href = '<%=request.getContextPath()%>/Detail?shelterNo='+shelterNo;
    })

</script>
</body>
</html>
