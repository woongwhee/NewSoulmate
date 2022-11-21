<%@ page import="tk.newsoulmate.domain.vo.Shelter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Shelter> sList = (ArrayList<Shelter>)request.getAttribute("sList");
    Member loginUser = (Member)session.getAttribute("loginUser");
    int memberNo = loginUser.getMemberNo();
%>
<html>
<head>
  <title>보호소관계자 등록신청</title>
  <link href="<%=request.getContextPath()%>/css/mypage/mypageShelter.css" rel="stylesheet">
    <%@include file="/views/template/styleTemplate.jsp" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</head>
<body>

<header><%@include file="/views/myPage/myPageHeader.jsp"%></header>

<div class="headcontainer">
  <div id="right_view">
    <div id="user_information">
      <form action="" id="" method="post">
          <input type="hidden" name="memberNo" value="<%=memberNo%>">
        <div class="form-group">
            <h2>보호소 리스트</h2>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                보호소 선택
            </button>
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">


                            <table class="list-area" border="1">
                                <thead>
                                <tr>
                                    <th>보호소명</th>
                                    <th>보호소 주소</th>
                                    <th>보호소 전화번호</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%for (Shelter s : sList) { %>
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
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
          <br><br>



        <div class="form-group">
            <h3>선택된 보호소</h3>
            <span id="selected-shelter"></span>
        </div>

        <div class="form-group">
          <label for="">보호소 사업자 번호</label>
          <input type="text" name="shelterCompNo" id="shelterCompNo" placeholder="보호소 사업번호('-'빼고 입력)" required>
        </div>

        <div class="form-group">
        </div>

        <div class="form-group">
          <label for="">보호소 전화번호</label>
          <input type="text" name="shelterLandLine" id="" placeholder="'-'빼고 입력">
        </div>

        <div class="form-group">
          <label for="">보호소 관계자 전화번호</label>
          <input type="text" name="shelterTel" id="" placeholder="'-'빼고 입력">
        </div>
        <div class="form-file">
          <input type="file" id="" name="첨부파일">
        </div>
        <button type="submit" onclick="" id="">변경사항 저장하기</button>
      </form>
    </div>
  </div>
</div>
<script>
    let selectedShelterNo = 0;
    $(".list-area>tbody>tr").click(function () {
        let shelterNo = $(this).children().eq(0).text().trim();
        selectedShelterNo = shelterNo;
        $("#selected-shelter").html($(this).children().eq(1).text().trim())
    })



</script>


</body>
</html>
