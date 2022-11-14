<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!Doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>환승주인-NewSoulmate</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/slide.css">
    <script src="<%=request.getContextPath()%>/js/common/noticeSlide.js"></script>
</head>
<body>
    <header> <%@include file="/views/template/menubar.jsp"%> </header>

    <main>
        <div class="slider">
            <div class="wrapper">
                <%@include file="/views/common/noticeSlide.jsp"%>
            </div>
        </div>

        //CSS

        <script>
            $('.post-wrapper').slick({
                slidesToShow: 5,
                slidesToScroll: 1,
                autoplay: true,
                autoplaySpeed: 2000,
            });
        </script>
    </main>
    <footer><%@include file="/views/template/footer.jsp"%></footer>

</body>

</html>