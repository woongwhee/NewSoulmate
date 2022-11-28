<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.type.MemberGrade" %>
<%@ page import="java.util.List" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-16
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Member> mList = (ArrayList<Member>)request.getAttribute("mList");
    List<Member> filteredList = new ArrayList<>(mList);

    PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

    int currentPage = pageInfo.getCurrentPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();
%>
<html>
<head>
    <title>회원관리-회원리스트</title>
    <%@ include file="/views/template/styleTemplate.jsp" %>
    <link href="<%=request.getContextPath()%>/css/manager/managerMemberList.css" rel="stylesheet">
</head>
<body>
<header>
    <%@include file="/views/manager/managerHeader.jsp" %>
</header>


<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                총 회원 수
                <span id="countMember" style="color: #f45d48;"><%= pageInfo.getListCount() %></span> 명
            </div>

            <div class="box">
                회원등급
                <span id="memberGrade">
                        <select name="selectMemberGrade" id="selectMemberGrade" onchange="changeSelect(this.options[this.selectedIndex].value)">
                            <option value="ALL">전체</option>
                            <option value="USER">일반회원</option>
                            <option value="SHELTER_MANAGER">보호소 관계자</option>
                        </select>
                </span>
                <span id="gray">(선택된 회원만 조회됩니다)</span>
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th>회원번호</th>
                        <th>이름</th>
                        <th>아이디</th>
                        <th>이메일</th>
                        <th>보호소이름</th>
                        <th>등급</th>
                        <th>가입일</th>
                        <th>최근접속일</th>
                        <th>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%if (filteredList == null || filteredList.isEmpty()) {%>
                    <tr>
                        <td colspan="9">존재하는 회원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>

                    <%for (Member m : filteredList) {%>
                    <tr>
                        <td><%=m.getMemberNo() %>
                        </td>
                        <td><%=m.getMemberName() %>
                        </td>
                        <td><%=m.getMemberId() %>
                        </td>
                        <td><%=m.getEmail() %>
                        </td>
                        <td><%=m.getShelterName()%>

                        </td>
                        <td>
                            <%if (m.getMemberGrade() == MemberGrade.SITE_MANAGER) { %>
                            관리자
                            <% }else if (m.getMemberGrade() == MemberGrade.SHELTER_MANAGER) { %>
                            보호소관계자
                            <%} else { %>
                            일반회원
                            <%} %>
                        </td>
                        <td><%=m.getEnrollDate() %>
                        </td>
                        <td><%=m.getResentConnection()%>
                        </td>
                        <td>
                            <div>
                                <%if (m.getMemberGrade()==MemberGrade.SHELTER_MANAGER){%>
                                <button id="updateGradeMemberBtn" onclick="updateGradeMember(<%=m.getMemberNo()%>, '<%=m.getMemberGrade().name()%>')">
                                    등급변경
                                </button>
                                <button id="deleteMemberBtn" onclick="deleteMember(<%=m.getMemberNo()%>)">탈퇴</button>
                                <% } else { %>
                                <button id="deleteMemberBtn2" onclick="deleteMember(<%=m.getMemberNo()%>)">탈퇴</button>
                                <% } %>
                            </div>
                        </td>
                    </tr>
                    <%} %>
                    <%} %>
                    </tbody>
                </table>
            </div>
            <div align="center" class="paging-area"> <!--페이징바-->
                <% if(currentPage != 1) { %>
                <button onclick="doPageClick(<%=currentPage - 1%>)" class="btn btn-secondary btn-sm">&lt;</button>
                <% } %>

                <% for(int i = startPage; i <= endPage; i++) { %>
                <% if(i != currentPage) {%>
                <button onclick="doPageClick(<%= i %>)" class="btn btn-secondary btn-sm"><%= i %></button>
                <% } else { %>
                <button disabled><%=i %></button>
                <% } %>
                <% } %>

                <% if(currentPage != maxPage) { %>
                <button onclick="doPageClick(<%=currentPage + 1%>)" class="btn btn-secondary btn-sm">&gt;</button>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script>

    window.onload = () => {
        $("#selectMemberGrade").val('<%=request.getParameter("filter")%>').prop("selected", true);
    }
    function doPageClick(currentPage) {
        location.href = "${context}/manageMember?page=" + currentPage + "&filter=" + $("#selectMemberGrade option:selected").val();
    }
    function changeSelect(selected) {
        location.href = "${context}/manageMember?page=1&filter=" + selected
    }

    function updateGradeMember(memberNo, memberGrade) {
        if (memberGrade === 'USER' || memberGrade === 'ADMIN') {
            alert("등급을 변경할 수 없는 회원입니다.");
            return;
        } else if (memberGrade === 'SHELTER_MANAGER') {
            memberGrade = 'USER';
        }
        $.ajax({
            url: "<%=request.getContextPath()%>/manage/grade",
            type: 'post',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                'memberNo': memberNo,
                'memberGrade': memberGrade // USER, SHELTER_MANAGER
            }),
            success: function () {
                alert("수정이 완료되었습니다.");
                location.reload();
            },
            error: function () {
                alert("수정 중 오류가 발생하였습니다.(error)");
            }
        });
    }

    function deleteMember(memberNo) {
        if (confirm("정말로 탙퇴처리 하시겠습니까?")) {
            $.ajax({
                url: "<%=request.getContextPath()%>/manage/delete-member",
                type: 'post',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    'memberNo': memberNo,
                }),
                success: function () {
                    alert("탈퇴처리가 완료되었습니다.");
                    location.reload();
                },
                error: function () {
                    alert("탈퇴 처리중 오류가 발생하였습니다.(error)");
                }
            })
        } else {

        }
    }
</script>

</body>
</html>
