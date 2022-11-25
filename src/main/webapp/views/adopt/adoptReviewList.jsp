<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-09
  Time: 오후 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <link href="<%=request.getContextPath()%>/css/board/adoptReviewList.css" rel="stylesheet">
</head>
<body>
    <%@include file="/views/template/menubar.jsp"%>
    <div id="content">
        <div id="adopt-review-head">
            <p>입양후기</p>
            <button onclick="location.href='adoptRevEnroll'">작성하기</button>
        </div>
        <div id="adopt-review-area">
            <c:forEach items="${tList}" var="t">
            <div class="adopt-thum">
                <img class="adopt-thumnail" src="${t.filePath}/${t.changeName}" onclick="location.href='${context}/adoptRevDetail?bno=${t.boardNo}'">
                <p>${t.boardTitle}</p>
            </div>
            </c:forEach>
        </div>
    </div>
<%@ include file="/views/template/footer.jsp"%>
    <script>
        let listCount=${listCount};
        let page=1;
        $(window).scroll(()=>{
            if($(window).scrollTop() + $(window).height() >= $(document).height()) {
                if(listCount>((page-1)*20)){
                    nextPage();
                }
            }
        });
        function nextPage(){
            $.ajax({
                url:'adoptRevNext',
                data:{'page':page},
                type:'post',
                success:(result)=>{
                    $("#adopt-review-area").append(result);
                    ++page;
                },
                error:(result)=>{
                    console.log(result)
                }
            })
        }


    </script>
</body>
</html>
