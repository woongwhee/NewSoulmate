<%@ page import="tk.newsoulmate.domain.vo.Subscription" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%@ page import="tk.newsoulmate.domain.vo.Volunteer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Subscription> sList = (ArrayList<Subscription>) request.getAttribute("sList");
    ArrayList<Volunteer> vList = (ArrayList<Volunteer>)request.getAttribute("vList");

    PageInfo pi = (PageInfo) request.getAttribute("pi");
    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();

    PageInfo pi2 = (PageInfo) request.getAttribute("pi");
    int currentPage2 = pi.getCurrentPage();
    int startPage2 = pi.getStartPage();
    int endPage2 = pi.getEndPage();
    int maxPage2 = pi.getMaxPage();
%>
<html>
<head>
    <title>메세지함</title>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterMessage.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/shelterManager/shelterMessage.css" rel="stylesheet">
</head>
<body>
<header><%@include file="/views/shelterManager/shelterHeader.jsp"%></header>
<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                <p>입양신청함</p>
            </div>

            <div id="memberList">
                <table class="adoptList-area">
                    <thead>
                    <tr>
                        <th width="20px">신청번호</th>
                        <th>공고번호</th>
                        <th>아이디</th>
                        <th>신청인</th>
                        <th>전화번호</th>
                        <th>신청일자</th>
                        <th>상태</th>
                        <th width="20px">관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if(sList == null || sList.isEmpty()) {%>
                    <tr id="tableEmpty">
                        <td colspan = "7">존재하는 신청내역이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <% for (Subscription s : sList) {%>
                    <tr>
                        <td class="vol-info"><%=s.getSubNo()%></td>
                        <td class="vol-info"><%=s.getAnimalNo()%></td>
                        <td class="vol-info"><%=s.getMemberId()%></td>
                        <td class="vol-info"><%=s.getName()%></td>
                        <td class="vol-info"><%=s.getTelNum()%></td>
                        <td class="vol-info"><%=s.getSubDate()%></td>
                        <c:set var = "subRead" value="<%=s.getSubRead()%>"/>
                        <c:choose>
                            <c:when test="${subRead eq 'N'}">
                                <td class="vol-info">미확인</td>
                            </c:when>
                            <c:otherwise>
                                <td class="vol-info">확인</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <button type="button" class="delete-btn"  data-sno="<%=s.getSubNo()%>">삭제</button>
                        </td>
                    </tr>
                    <% }%>
                    <% } %>

                    </tbody>
                </table>

                <div id="pagingForm1" align="center">
                    <% if(currentPage != 1) { %>
                    <button onclick="doPageClick(<%=currentPage-1%>)" class="btn btn-secondary btn-sm">&lt;</button>

                    <% } %>

                    <% for(int i=startPage; i<=endPage; i++) { %>
                    <% if(i != currentPage) {%>
                    <button onclick="doPageClick(<%=i%>)" class="btn btn-secondary btn-sm"><%=i %></button>
                    <% } else { %>
                    <button disabled><%=i %></button>
                    <% } %>
                    <% } %>

                    <% if(currentPage != maxPage) { %>
                    <button onclick="doPageClick(<%=currentPage+1%>)" class="btn btn-secondary btn-sm">&gt;</button>

                    <% } %>
                </div>

            </div>
            <div class="box2">
                <p>봉사신청함</p>
            </div>

            <div id="memberList2">
                <table class="volunteer-area">
                    <thead>
                    <tr>
                        <th>신청번호</th>
                        <th>아이디</th>
                        <th>신청인</th>
                        <th>보호소명</th>
                        <th>봉사 희망 날짜</th>
                        <th>전화번호</th>
                        <th>상태</th>
                        <th width="20px">관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if(vList == null || vList.isEmpty()) {%>
                    <tr id="tableEmpty2">
                        <td colspan = "7">존재하는 신청내역이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <% for (Volunteer v : vList) {%>
                    <tr>

                        <td class="sub-Info"><%=v.getVolunteerNo()%></td>
                        <td class="sub-Info"><%=v.getMemberId()%></td>
                        <td class="sub-Info"><%=v.getName()%></td>
                        <td class="sub-Info"><%=v.getShelterName()%></td>
                        <td class="sub-Info"><%=v.getStartDate()%></td>
                        <td class="sub-Info"><%=v.getTelNumber()%></td>

                        <c:set var = "volRead" value="<%=v.getVolRead()%>"/>
                        <c:choose>
                            <c:when test="${volRead eq 'N'}">
                                <td class="sub-Info">미확인</td>
                            </c:when>
                            <c:otherwise>
                                <td class="sub-Info">확인</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <!--관리-->
                            <button type="button" class="delete-btn"  data-vno="<%=v.getVolunteerNo()%>">삭제</button>
                        </td>
                    </tr>
                    <% }%>
                    <% } %>

                    </tbody>
                </table>

                <div id="pagingForm2" align="center">
                    <% if(currentPage2 != 1) { %>
                    <button onclick="doPageClick(<%=currentPage2-1%>)" class="btn btn-secondary btn-sm">&lt;</button>

                    <% } %>

                    <% for(int i=startPage2; i<=endPage2; i++) { %>
                    <% if(i != currentPage2) {%>
                    <button onclick="doPageClick(<%=i%>)" class="btn btn-secondary btn-sm"><%=i %></button>
                    <% } else { %>
                    <button disabled><%=i %></button>
                    <% } %>
                    <% } %>

                    <% if(currentPage2 != maxPage2) { %>
                    <button onclick="doPageClick(<%=currentPage2+1%>)" class="btn btn-secondary btn-sm">&gt;</button>

                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(".sub-Info").click(function(e){
        // 클릭시 해당 shelter_no 를 넘김
        //해당 tr 요소의 자손들중 첫번째 td영역의 내용이 필요
        //
        //let volunteerNo = $(this).eq(0).text().trim();
        let volunteerNo = $(e.target).parent().children().eq(0).text().trim();
        // textnode를 가져옴
        console.log(volunteerNo);

        location.href = '<%=request.getContextPath()%>/VolunteerDetail?volunteerNo='+volunteerNo;
    })

    $(".volDelete").on("click",function(e){

        let volNo =$(e.target).data('vno') ;

        console.log(volNo);

        $.ajax({
            type: "POST",
            traditional: true,
            data: {"volNo": volNo},
            url: "DeleteVolunteer",
            success: function (data) {
                if (data >0) {
                    alert("성공적으로 삭제되었습니다");
                    location.reload();
                }
            },
            error: function () {
                alert("삭제 실패");
                location.reload();
            }

        })
    })
</script>

<script>
    // $(".delete").on("click",function(){
    //
    //     let thisRow = $(this).closest("tr");
    //     let addr = thisRow.find("td:eq(0)").val();
    //     console.log(addr);
    // })
    $(".vol-info").click(function(e){
        // 클릭시 해당 shelter_no 를 넘김
        //해당 tr 요소의 자손들중 첫번째 td영역의 내용이 필요
        let subNo = $(e.target).parent().children().eq(0).text().trim()
        // textnode를 가져옴
        console.log(subNo);

        location.href = '<%=request.getContextPath()%>/AdoptDetail?subNo='+subNo;
    })

    $(".subDelete").on("click",function(e){

        let sno =$(e.target).data('sno') ;

        console.log(sno);

        $.ajax({
            type: "POST",
            traditional: true,
            data: {"sno": sno},
            url: "DeleteSubscription",
            success: function (data) {
                if (data >0) {
                    alert("성공적으로 삭제되었습니다");
                    location.reload();
                }
            },
            error: function () {
                alert("삭제 실패");
                location.reload();
            }

        })
    })
</script>

</body>
</html>