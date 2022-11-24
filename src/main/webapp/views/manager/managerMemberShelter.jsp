<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="tk.newsoulmate.domain.vo.GradeUp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="tk.newsoulmate.domain.vo.Attachment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    ArrayList<GradeUp> gList = (ArrayList<GradeUp>)request.getAttribute("gList");
    //ArrayList<Shelter> sList = (ArrayList<Shelter>)request.getAttribute("sList");


%>
<html>
<head>
    <title>회원관리-보호소 관계자 신청</title>
    <link href="<%=request.getContextPath()%>/css/manager/managerMemberShelter.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                총 대기회원 수 <span id="memberTotal">
                <%=gList.size()%>
                    </span> 명
            </div>

            <div id="memberList">
                <table class="grade">
                    <thead>
                    <tr>
                        <th width="10px"><input type="checkbox" id="selectAll"/></th>
                        <th width="20px">No</th>
                        <th>아이디</th>
                        <th>보호소명</th>
                        <th>보호소 사업자번호</th>
                        <th>보호소 전화번호</th>
                        <th width="20px">첨부파일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!--보여질 페이지 수 지정-->

                    <%for (GradeUp g : gList){%>
                    <tr>
                        <td>
                            <input type="checkbox" id="" name="select" value="<%=g.getMemberNo()%>"/>
                        </td>
                        <td>
                            <%=g.getGradeNo()%>
                        </td>
                        <td>
                            <%=g.getMemberNo()%>
                        </td>
                        <td>
                            <%=g.getShelterName()%>
                        </td>
                        <td>
                            <%=g.getShelterCompNo()%>

                        </td>
                        <td>
                            <%=g.getShelterLandLine()%>
                        </td>
                        <td>
                            <a href="<%=g.getAttachment().getFilePath()%>/<%=g.getAttachment().getChangeName()%>"
                               download="<%=g.getAttachment().getOriginName()%>" >첨부파일</a>
                        </td>
                    </tr>
                    <%}%>

                    </tbody>
                </table>

                <div id="buttonBox">
                    <input type="button" value="승인" id="allow">
                    <input type="button" value="거부" id="reject">
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $("#selectAll").click(function(){
        if($("#selectAll").is(":checked")) $("input[name=select]").prop("checked", true);
        else $("input[name=select]").prop("checked", false);
    });

    $("input[name=select]").click(function() {
        var total = $("input[name=select]").length;
        var checked = $("input[name=select]:checked").length;

        if(total != checked) $("#selectAll").prop("checked", false);
        else $("#selectAll").prop("checked", true);
    });

/*
    $("#allow").click(function(){
        let checkMember = new Array();
        $("#input[name=select]:checkbox").each(function(){
            checkMember.push($(this).value);
        });
        $.ajax({
            type:"POST",
            data:{"checkMember":checkMember},
            url:"/GradeAllow",
            success:function(data){

                alert("성공적으로 승인되었습니다.")
            }

        })
    })
*/


    let temp;

    function test() {
        let checkMember = [];
        checkMember.push("1");
        checkMember.push("2");
        checkMember.push("3");
        checkMember.push("4");
        checkMember.push("5");

        $.ajax({
            url: "<%=request.getContextPath()%>/GradeAllow",
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify([1, 2, 3, 4, 5]),
            success: function (data) {
                console.log(data);
                temp = data
                alert("성공적으로 승인되었습니다.")
            }
        })
    }
</script>









</script>
</body>
</html>