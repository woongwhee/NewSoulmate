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
        <p> <i class="bi bi-patch-exclamation-fill"></i> 환승주인 > 봉사 > 봉사신청 메뉴에서 봉사희망날짜와 보호소를 선택하신 후 봉사신청서를 작성하실 수 있습니다.
            신청서를 작성해주시면 서류 검토 후 봉사 여부가능이 확정됩니다.</p>

        <div> <i class="bi bi-patch-question-fill"></i> 후원금으로 어떤 동물들이 어떤 도움을 받을 수 있나요?</div>
        <p> <i class="bi bi-patch-exclamation-fill"></i> 반려동물인 개와 고양이 외의 한국의 모든 동물을 위한 활동을 하며,
            각 동물의 사회적 위치에 따른 단계적 전략을 수립하여 동물이 착취의 대상이 아닌 공존할 수 있는 대상으로서 살아갈 수 있도록 합니다.<br>
            직접적인 구조와 구호 활동부터 교육과 문화, 법과 제도 개선 영역에서의 활동을 체계적으로 전개하며, 근본적으로 동물이 부당하게 착취당하지 않는 사회로의 변화를 추구합니다.</p>

        <div> <i class="bi bi-patch-question-fill"></i> 반려동물과 생활한 경험이 없는데 입양을 할 수 있을까요?</div>
        <p> <i class="bi bi-patch-exclamation-fill"></i> 반려의 경험이 없다고하여 환승주인에서의 입양이 안되는 것은 아닙니다.<br><br>
            반려동물은 평균 15년 이상을 사는 소중한 생명입니다. 따라서 성공적인 입양이 되기 위해서 필요한 사전 점검사항이 있습니다.<br>
            동물을 사랑하는 마음과 책임감을 가지고 돌보기 위해 본인의 현재 상황과 주거환경, 이후 결혼이나 이주 계획등에 대해 심사숙고 해 주셔야 합니다.<br>
            또한 원하는 동물에 따라 사전에 충분히 정보를 수집하여 해당 동물의 특징을 인지하고 직·간접적으로 경험을 하시는것이 중요합니다.<br><br>
            예를 들어, 개를 입양하고 싶으시다면 '개'라는 동물의 특징을 아셔야 합니다.<br>
            개들은 짖기도 하고 배변실수도 할 수 있으며, 매일 산책을 해주어야 합니다.<br>
            또한 개는 인간과의 유대가 강한 동물이기 때문에 장시간 혼자 두는 것 보다는 가족과 함께하는 시간이 많은 것이 좋습니다.<br><br>
            누구에게나 처음이 있고, 처음은 언제나 어렵게 느껴지죠.<br>
            평생을 함께할 가족을 들이는 것인 만큼 조금만 마음에 여유를 두고 준비해주세요.<br><br>
            그 이후 당신에게 온 작고 따뜻한 이 생명들은 당신에게 세상 그 누구에게서도 받지못했던 또 다른 사랑과 추억을 아낌없이 줄테니까요!
        </p>

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

        <c:if test="${!empty loginUser and !loginUser.memberGrade.SITE_MANAGER}">
            <input type="button" name="" value="글쓰기" id="writingQna" onclick="location.href='${context}/inquireEnroll.bo';">
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

                        location.href = "${context}/inquireDetail?bno=" + bno;
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
