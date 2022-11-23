<%--
  Created by IntelliJ IDEA.
  User: gram
  Date: 2022-11-22
  Time: 오후 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>후원관리-보호소별 후원내역 확인</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
          crossorigin="anonymous"></script>

  <%@ include file="/views/template/styleTemplate.jsp"%>
  <link href="<%=request.getContextPath()%>/css/manager/manageSupportHistory.css" rel="stylesheet">


</head>
<body>
<header><%@include file="/views/manager/managerHeader.jsp"%></header>

<div class="headcontainer">
  <div id="right_view">
    <div id="user_information">
      <div class="box">
        <span>보호소명</span>
        <span>
                        <select name="shelterList" id="shelterList" onchange="choice();">
                            <option value="0">전체 보호소명</option>
                            <option value="1">강남보호소</option>
                          <!-- <option value="0">전체 보호소명</option>
                        <% for(: ){ %>
                        <option value="<%=c.()%>">
                            <%=c.()%>
                        </option>
                        <% } %> -->
                        </select></span>

        <span>조회기간</span>
        <span><input type="date"></span>
        <span>~</span>
        <span><input type="date"></span>
        <span><input type="submit" value="조회"></span>
      </div>

      <div id="memberList">
        <table>
          <thead>
          <tr>
            <th width="20px">No</th>
            <th>보호소명</th>
            <th>후원일시</th>
            <th>후원 금액(원)</th>
            <th>후원자</th>
            <th>출금여부</th>
            <th width="20px">출금승인</th>
          </tr>
          </thead>
          <tbody>
          <!--보여질 페이지 수 지정-->
          <c:forEach var="board" items="${}">
            <tr>
              <td>
                <!--번호 ${가져오기}-->
              </td>
              <td>
                <!--보호소명 ${가져오기}-->ds한국동물구조관리협회
              </td>
              <td>
                <!--후원일시 ${가져오기}-->2022-10-22 10:22
              </td>
              <td>
                <!--후원 금액(원)-->20,000
              </td>
              <td>
                <!--후원자-->df
              </td>
              <td>
                <!--출금여부-->완료
              </td>
              <td>
                <!--출금승인-->
                <input type="submit" class="btn btn-primary" data-bs-toggle="modal"
                       data-bs-target="#staticBackdrop" value="승인">
                <!-- Modal -->
                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
                     data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
                     aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">출금 승인
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        보호소명 : <br>
                        은행 : <br>
                        계좌번호 : <br>
                        예금주 : <br>
                        출금신청금액 : <br>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-primary"
                                id="btn-primary">출금 승인하기</button>

                      </div>
                    </div>
                  </div>
                </div>
              </td>
            </tr>

          </c:forEach>
          </tbody>
        </table>

        <div id="pagingForm">
          <!--페이지네이션-->

          <!-- 아래 코드 참고 / 다른 편한 방식으로 해도 돼!!! -->

          <!-- <div align="center" class="paging-area">

                    <% if(currentPage != 1) { %>
                    <button onclick="doPageClick(<%= currentPage-1 %>)">&lt;</button>
                    <% } %>

                    <% for(int i = startPage; i<=endPage; i++) { %>
                    <% if(i != currentPage) { %>
                    <button onclick="doPageClick(<%= i %>)"><%= i %></button>
                    <% } else { %>
                    <button disabled><%= i %></button>
                    <% } %>
                    <% } %>

                    <% if(currentPage != maxPage) { %>
                    <button onclick="doPageClick(<%= currentPage+1 %>)">&gt;</button>
                    <% } %>

                </div>


 -->


        </div>

      </div>
    </div>
  </div>


</body>
</html>