<%@ page import="tk.newsoulmate.domain.vo.Board" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="tk.newsoulmate.domain.vo.PageInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: 상엽
  Date: 2022-11-08
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
    PageInfo pi = (PageInfo) request.getAttribute("pi");
    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
%>
<html>
<head>
    <title>고객센터</title>
    <%@ include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/inquire/inquireFQ.css" rel="stylesheet">
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
</head>
<body>
    <%@ include file="/views/template/menubar.jsp"%>
    <br><br><br>
    <div id="FQ_bt">
        <button type="button" id="qna_bt" class="btn btn-secondary btn-sm">Q&A</button>
        <button type="button" id="faq_bt" class="btn btn-secondary btn-sm">FAQ</button>
        <br>
    </div>
    <div id="Fcontent" style="display: none">

        <span id="topTextFna"> 자주 묻는 질문 </span>

        <div> <i class="bi bi-patch-question-fill"></i> 유기동물을 발견했어요</div>
        <p> <i class="bi bi-patch-exclamation-fill"></i> 유기동물을 발견하셨다면 120번 또는 해당 구청 담당부서로 연락주세요!</p>

        <div> <i class="bi bi-patch-question-fill"></i> 자원봉사는 어떻게 신청하나요?</div>
        <p> <i class="bi bi-patch-exclamation-fill"></i> 상단바에 봉사페이지를 이용해주세요</p>

        <div> <i class="bi bi-patch-question-fill"></i> 후원금은 어떻게 사용되나요?</div>
        <p> <i class="bi bi-patch-exclamation-fill"></i> 잘 이용됩니다.</p>

        <div> <i class="bi bi-patch-question-fill"></i> 어떤 동물들이 있는지 궁금합니다.</div>
        <p> <i class="bi bi-patch-exclamation-fill"></i> 개, 고양이등 기타 동물들이 있습니다.</p>

        <script>
        <%--    FnQ     --%>
            $(function(){
                $(document).on("click", "#Fcontent div" ,function(){
                    let $p = $(this).next();

                    if($p.css("display") == "none"){
                        $p.siblings("p").slideUp();
                        $p.slideDown(100);

                    }else{
                        $p.slideUp(100);
                    }

                });
            });
        </script>

    </div>
    <div id="Qcontent">
        <div class="topTextQna">1:1 문의</div>
        <br>

<%--        <div id="box1" align="center" style="float: left; margin-left: 400px;"><input type="checkbox" id="" >나의 문의내역 보기</div> --%>
<%--        나의 문의내역 없애기로함--%>

        <c:if test="${!empty loginUser and !loginUser.memberGrade.SITE_MANAGER}">
            <input type="button" name="" value="글쓰기" id="writingQna" onclick="location.href='${context}/inquireEnroll.bo';">
<%--            <a href="${context}/inquireEnroll.bo" class="btn btn-secondary btn-sm">글작성</a>--%>
            <br>
            <br>
        </c:if>
        <table align="center" id="tableQna" class="list-area">
            <thead>
            <tr style="text-align: center">
                <th width="100">답변상태</th>
                <th width="70">글번호</th>
                <th width="300">제목</th>
                <th width="100">글쓴이</th>
                <th width="100">작성일</th>

            </tr>
            </thead>
            <tbody align="center">
            <% if(list==null || list.isEmpty()){ %>
            <tr id="tableEmpty">
                <td colspan="5" >조회된 리스트가 없습니다</td>
            </tr>
            <% } else { %>
                <% for(Board b : list) { %>
            <tr>
                <td><%= b.getResultStatus() %></td>
                <td><%= b.getBoardNo() %></td>
                <td><%= b.getBoardTitle() %></td>
                <td><%= b.getMemberName() %></td>
                <td><%= b.getCreateDate() %></td>
            </tr>
                <% } %>
            <% } %>
            </tbody>

        </table>
        <script>
            $(function(){
                $("#tableQna>tbody>tr").click(function(){
                    // 클릭시 해당 공지사항의 번호를 넘겨야함.
                    // 해당 tr요소의 자손중에서 첫번째 td의 영역의 내용이 필요.
                    if($(this).text()!=$("#tableEmpty").text()) { // 조회된 리스트가 없을경우 클릭방지
                        let bno = $(this).children().eq(1).text(); // 1 => b.getBoardNo()
                        // 현재 내가클릭한 tr의 자손들중 1번째에 위치한 자식의 textnode내용을 가져온다.

                        // 요청할 url?키=밸류&키=밸류&키=밸류
                        // 물음표 뒤의 내용을 쿼리스트링이라고 부른다. => 직접 만들어서 넘겨야함.
                        location.href = "<%=request.getContextPath()%>/inquireDetail?bno=" + bno;
                    }
                });

            });
        </script>
        <br><br>

        <%-- 페이징바 처리--%>
        <div align="center" class="paging-area">

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
        <script>
            function doPageClick(currentPage){
                location.href = "<%=request.getContextPath()%>/inquire?currentPage="+currentPage;
            }
        </script>

    </div>
    <script>
        <%--   FnQ, QnA 변환   --%>
        $(function(){
            $("#qna_bt").on("click", function(){
                $(this).css("color", "#f8f5f2")
                $(this).css("background-color", "#f45d48")
                $("#faq_bt").css("color", "black")
                $("#faq_bt").css("background-color", "lightgray")
                $("#Fcontent").css("display", "none")
                $("#Qcontent").css("display", "block")
            });
        });

        $(function(){
            $("#faq_bt").on("click", function(){
                $(this).css("color", "#f8f5f2")
                $(this).css("background-color", "#f45d48")
                $("#qna_bt").css("color", "black")
                $("#qna_bt").css("background-color", "lightgray")
                $("#Fcontent").css("display", "block")
                $("#Qcontent").css("display", "none")
            });
        });


    </script>
    <br><br>
    <%@include file="/views/template/footer.jsp"%>
</body>
</html>
