<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page import="tk.newsoulmate.domain.vo.GradeUp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Member> mList = (ArrayList<Member>)request.getAttribute("mList");
    ArrayList<GradeUp> gList = (ArrayList<GradeUp>)request.getAttribute("gList");
    ArrayList<Shelter> sList = (ArrayList<Shelter>)request.getAttribute("sList");

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
                총 대기회원 수 <span id="memberTotal">6
                <!--총 대기회원수 불러오기-->
                    </span> 명
            </div>

            <div id="memberList">
                <table>
                    <thead>
                    <tr>
                        <th width="10px"><input type="checkbox" id="" /></th>
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
                    <% for(Member m : mList){%>
                        <%for (GradeUp g : gList){%>
                            <%for(Shelter s : sList){%>
                            <%if(m.getMemberNo() == g.getMemberNo()){%>
                                <%if(m.getShelterNo() == s.getShelterNo())%>
                        <tr>
                            <td>
                                <input type="checkbox" id="" />
                            </td>
                            <td>
                                <!--번호 ${가져오기}-->1
                                <%=g.getGradeNo()%>

                            </td>
                            <td>
                                <!--아이디 ${가져오기}-->ds
                                <%=g.getMemberNo()%>
                            </td>
                            <td>
                                <!--보호소명 ${가져오기}-->한국동물구조관리협회
                                <%=s.getShelterName()%>
                            </td>
                            <td>
                                <!--보호소 사업자번호 ${가져오기}-->df안녕
                                <%=g.getShelterCompNo()%>%>
                            </td>
                            <td>
                                <!--보호소 전화번호 ${가져오기}-->df
                                <%=g.getShelterLandLine()%>
                            </td>
                            <td>
                                <input type="submit" value="확인">
                            </td>
                        </tr>
                                <%}%>
                            <%}%>
                        <%}%>
                    <%}%>


                    </tbody>
                </table>

                <div id="buttonBox">
                    <input type="button" value="승인">
                    <input type="button" value="거부">
                </div>
            </div>



        </div>
    </div>
</div>




</body>

</html>
