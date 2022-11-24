<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.type.MemberGrade" %>
<%@ page import="tk.newsoulmate.web.manger.site.service.ManageService" %>
<%@ page import="tk.newsoulmate.web.member.service.MemberService" %>
<%@ page import="tk.newsoulmate.domain.vo.ManageMember" %><%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-16
  Time: 오후 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<ManageMember> mList = (ArrayList<ManageMember>) request.getAttribute("mList");
    ManageService ms = new ManageService();
%>
<html>
<head>
    <title>회원관리-회원리스트</title>
    <link href="<%=request.getContextPath()%>/css/manager/managerMemberList.css" rel="stylesheet">
    <%@ include file="/views/template/styleTemplate.jsp"%>
</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>


<div class="headcontainer">
    <div id="right_view">
        <div id="user_information">

            <div class="box">
                총 회원 수
                <span id="countMember" style="color: #f45d48;"><%= ms.selectCountMember() %></span> 명
            </div>

            <div class="box">
                회원등급
                <span id="memberGrade">


                        <label><input type="checkbox" name="all" value="all"> 전체 </label>
                        <label><input type="checkbox" name="user" value="0"> 일반 회원 </label>
                        <label><input type="checkbox" name="shelter" value="1"> 보호소 관계자 </label>




                        <select name="memberPhone" id="memberPhone">
                            <option value="all">전체</option>
                            <option value="0">일반회원</option>
                            <option value="1">보호소 관계자</option>
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
                    <%if(mList == null || mList.isEmpty()) {%>
                    <tr>
                        <td colspan = "9">존재하는 회원내역이 없습니다.</td>
                    </tr>

                    <%} else { %>

                    <%for (ManageMember m : mList) {%>
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
                            <%if(m.getMemberGrade() == MemberGrade.valueOfNumber(2)) { %>
                            관리자
                            <input type="hidden" name="memberGrade" value=<%=m.getMemberGrade()%>>
                            <%} else if (m.getMemberGrade() == MemberGrade.valueOfNumber(1)){ %>
                            보호소관계자
                            <input type="hidden" name="memberGrade" value=<%=m.getMemberGrade()%>>
                            <%} else { %>
                            일반회원
                            <input type="hidden" name="memberGrade" value=<%=m.getMemberGrade()%>>
                            <%} %>
                        </td>
                        <td><%=m.getEnrollDate() %>
                        </td>
                        <td><%=m.getResentConnection()%>
                        </td>
                        <td>
                            <div>
                                <button>등급변경</button>
                                <button>탈퇴</button>
                            </div>
                        </td>
                    </tr>
                    <%} %>
                    <%} %>
                    </tbody>


                </table>
            </div>



        </div>
    </div>
</div>

<script>








    /*    function updateMember(){
            const memberNo = $("#memberNo").val();
            const memberName = $("#memberName").val();
            const memberId = $("#memberId").val();
            const memberPw = $("#memberPw").val();
            const memberPhone = $("#memberPhone").val();
            const memberMail = $("#memberMail").val();
            const memberLevel = $("#memberLevel").val();
            $.ajax({
                url : "/updateMember.do",
                type : "post",
                data : {
                    memberNo:memberNo,
                    memberName:memberName,
                    memberId:memberId,
                    memberPw:memberPw,
                    memberPhone:memberPhone,
                    memberMail:memberMail,
                    memberLevel:memberLevel
                },
                success : function(data){
                    if(data==1){
                        alert("수정이 완료되었습니다.");
                        location = "/manageMember.do"
                    }else{
                        alert("수정 중 오류가 발생하였습니다\n아이디중복 등을 확인하세요.");
                        location = "/manageMember.do"
                    }
                },
                error :function(){
                    alert("수정 중 오류가 발생하였습니다.(error)");
                    location = "/manageMember.do"
                }
            });
        }

        function deleteMember(){
            const memberNo = $("#memberNo").val();
            const memberName = $("#memberName").val();
            const memberId = $("#memberId").val();
            const memberMail = $("#memberMail").val();

            if(confirm("정말로 탙퇴처리 하시겠습니까?")){
                $.ajax({
                    url : "/deleteMember",
                    type : "get",
                    data:{
                        memberNo:memberNo,
                        memberName:memberName,
                        memberId:memberId,
                        memberMail:memberMail
                    },
                    success : function(data){
                        if(data==1){
                            alert("탈퇴처리가 완료되었습니다.");
                            location = "/manageMember.do"
                        }else{
                            alert("탈퇴 처리중 오류가 발생하였습니다.");
                            location = "/"
                        }
                    },
                    error : function(){
                        alert("탈퇴 처리중 오류가 발생하였습니다.(error)");
                        location = "/"
                    }
                })
            }else{

            }

        }*/



</script>

</body>
</html>
