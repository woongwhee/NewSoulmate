<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="tk.newsoulmate.domain.vo.City" %>
<%@ page import="tk.newsoulmate.domain.vo.Village" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2022-11-08
  Time: 오후 6:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>) request.getAttribute("sList");
    ArrayList<City> cList = (ArrayList<City>)request.getAttribute("cList");
    ArrayList<Village> vList = (ArrayList<Village>)request.getAttribute("vList");

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
    <style>
        .list-area{
            border: 1px solid black;
            text-align:center;
        }
    </style>
</head>
<body>
<%@include file="/views/template/menubar.jsp"%>

    <form action="<%=request.getContextPath()%>/updateShelter" method="get">
        <table>
            <tr>
            <td>시도</td>
                <td>
                    <select name="cityNo" id="mainCategory" onchange="choice();">
                        <option value="0">--전체--</option>
                        <% for(City c: cList){ %>
                            <option value="<%=c.getCityNo()%>"><%=c.getCityName()%></option>
                        <% } %>
                    </select>
                </td>

                <td>시군구</td>
                <td>
                    <select name="villageNo" id="subCategory">
                        <option value="0">--전체--</option>
                    </select>
                </td>
            </tr>
            <tr>
                    <input type="submit" value="조회">
            </tr>
        </table>

    <script>

            function choice() {
                // 메인페이지 선택시 서브쿼리 삭제
                $("#subCategory").children().remove();
                $.ajax({
                    url: "jqAjaxCity",
                    data: {city: $("#mainCategory").val()},
                    success: function (result) {
                        console.log(result, "어라?");
                        let str = '<option value="0">전체</option>';
                        for (let i = 0; i < result.length; i++) {
                            str += "<option value=" + result[i].villageNo + ">" + result[i].villageName + "</option>"
                        }
                        $("#subCategory").html(str);

                    }
                })
            }

            //1 . /shelter/updateShelter 비동기로 요청
            //2. 응답정보를 html로 받아오고
            //3. succes시 tbody\태그에 html메서드 사용해서 데이터뿌려주기.


    </script>
    </form>
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
                    <td style="display:none"><%= s.getShelterNo()%></td>
                    <td><%= s.getShelterName() %></td>
                    <td><%= s.getShelterAddress() %></td>
                    <td><%= s.getShelterLandline() %></td>
                    </tr>
            <% } %>

        </tbody>
    </table>
    <script>
        $(".list-area>tbody>tr").click(function(){
            // 클릭시 해당 shelter_no 를 넘김
            //해당 tr 요소의 자손들중 첫번째 td영역의 내용이 필요
            let shelterNo = $(this).children().eq(0).text();
            // textnode를 가져옴
            console.log(shelterNo);

            location.href = '<%=request.getContextPath()%>/Detail?shelterNo='+shelterNo;
        })

</script>

    <jsp:include page="../template/footer.jsp"/>

</body>
</html>