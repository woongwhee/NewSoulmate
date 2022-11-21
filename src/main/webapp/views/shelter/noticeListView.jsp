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
    <div id="search">
        <label for="date">날짜</label>
        <div id="date">
            <input type="date" id="bgndate" name="bgndate">
            <input type="date" id="enddate" name="enddate">
            <p>(날짜는 접수일 기준입니다.)</p>
        </div>
        <div id="shelter">
            <select name="cityNo" id="cityNo" onchange="choice();">
                <option value="0">--전체--</option>

                <c:forEach items="${cList}" var="c">
                <option value="${c.cityNo}">
                    ${c.cityName}
                </option>
                </c:forEach>
            </select>
            <select name="villageNo" id="villageNo" onchange="choice();">
                <option value="0">--전체--</option>
            </select>
            <select name="shelterNo" id="shelterNo">
                <option value="0">--전체--</option>
            </select>
        </div>
        <label for="bread">품종</label>
        <select name="bread" id="bread">
            <option value="" selected disabled hidden></option>
            <option value="DOG">개</option>
            <option value="CAT">고양이</option>
            <option value="ANOTHER">기타</option>
        </select>
        <label for="neuter">중성화여부</label>
        <select name="neuter" id="neuter">
            <option value="" selected disabled hidden></option>
            <option value="Y">예</option>
            <option value="N">아니오</option>
            <option value="U">미상</option>
        </select>
        <button type="button" id="noticeSearch">조회</button>
    </div>
    <div id="notice-area">
<%--        <c:forEach items="${nList}" var="n">--%>
<%--        <div class="notice">--%>
<%--            <img class="notice-photo" src="${n.popfile}" alt="" onclick="location.href='noticeDetail?dno=${n.desertionNo}'">--%>
<%--        </div>--%>
<%--        </c:forEach>--%>
    </div>
</div>
    <script>
        let request={
            bgndate:null,
            enddate:null,
            sepcies:null,
            cityNo:null,
            villageNo:null,
            shelter:null,
            state:'protect',
            pageNo:1,
            numberOfRows:40
        }
        $(()=> {
            $.ajax({
                url: "ShelterSearch",
                success: function (result) {
                    $("#shelter").html(result);
                }
            })
            noticeSearch();
            $('#bgndate').on('change', () => {
                request.bgndate = $('#bgndate').val();
            });
            $('#enddate').on('change', () => {
                request.enddate = $('#enddate').val();
            });
            $('#sepcies').on('change', () => {
                request.sepcies = $('#sepcies').val();
            });
            $('#cityNo').on('change', () => {
                request.cityNo = $('#cityNo').val();
            });
            $('#villageNo').on('change', () => {
                request.villageNo = $('#villageNo').val();
            });
            $('#shelterNo').on('change', () => {
                request.shelterNo = $('#shelterNo').val();
            });
            $('#noticeSearch').click(()=>{
                request.pageNo=1;
                noticeSearch()
            })


        })

        function noticeSearch() {
            $.ajax({
                url:'noticeSearch',
                type:'post',
                data:{'request':JSON.stringify(request)},
                success:(result)=>{
                    $('#notice-area').html(result);
                }
            })
        }
        function choice() {
            // 메인페이지 선택시 서브쿼리 삭제
            $("#villageNo").children().remove();
            $.ajax({
                url: "jqAjaxCity",
                data: {
                    city: $("#mainCategory").val()},
                    success: function (result) {
                    console.log(result, "어라?");
                    let str = '<option value="0">전체</option>';
                    for (let i = 0; i < result.length; i++) {
                        str += "<option value="+result[i].villageNo+ ">" + result[i].villageName + "</option>"
                    }
                    $("#villageNo").html(str);
                }
            })
        }
        function choiceShel() {
            // 메인페이지 선택시 서브쿼리 삭제
            $("#shelterNo").children().remove();
            $.ajax({
                url: "village.ax",
                data: {
                    village: $("#villageNo").val()},
                    success: function (result) {
                        console.log(result, "어라?");
                        let str = '<option value="0">전체</option>';
                        for (let i = 0; i < result.length; i++) {
                            str += "<option value="+result[i].shelter+ ">" + result[i].shelterName + "</option>"
                        }
                        $("#shelterNo").html(str);
                }
            })
        }


</script>

    <%@ include file="/views/template/footer.jsp"%>

</body>
</html>