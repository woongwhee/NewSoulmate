<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!Doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>환승주인-NewSoulmate</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.css"/>
    <script> $.noConflict();</script>
    <style>
        .slider img {
            max-height: 150px;
            max-width: 80px;
            height: auto;
            margin-top: 30px;
        }
        .slider {
            height: 200px;
            width: 600px;
            margin: 0px auto;
            background-color: rgba(255, 99, 71, 0.708);
            border-radius: 25px;
        }
        .slick-prev:before, .slick-next:before {
            color: darkcyan;
        }
    </style>
</head>
<body>
    <header> <%@include file="/views/template/menubar.jsp"%> </header>

<main>
    <div class="slider">
        <%@include file="/views/common/noticeSlide.jsp"%>
    </div>
    <script src="<%=request.getContextPath()%>/js/common/noticeSlide.js"></script>
</main>
    <footer><%@include file="/views/template/footer.jsp"%></footer></include>

</body>

</html>