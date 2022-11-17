<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!Doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>환승주인-NewSoulmate</title>
    <%@include file="/views/template/styleTemplate.jsp"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
    <link href="<%=request.getContextPath()%>/css/common/main.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/common/noticeSlide.js"></script>
</head>
<body>
    <header><%@include file="/views/template/menubar.jsp"%></header>
<script>
    $(document).ready(function () {
        $('.slider').slick({
            autoplay: true,
            autoplaySpeed: 2000,
            slidesToShow: 5,
            infinite: true,
            dots: true,
        });
    });
</script>
    <main>
<div id="container">

    <div id="main_top">
        <div id="comment">지금 새로운 주인을 기다리고있어요..</div>
        <div class="slider">
            <%@include file="/views/common/noticeSlide.jsp"%>
        </div>
        <script>
            $(()=>{
                setNoticeLocation();

            })
            function setNoticeLocation(){
                $("#main_top>.slider>post").click(()=>{
                    let dno=$(this).getAttribute("id");
                    location.href="<%=request.getContextPath()%>/noticeDetail?dno="+dno;


                })

            }
        </script>
    </div>
    <div id="main_left">
        <div id="adopt-review">
            <div class="slider">
                <div><img src="" alt="">사진1</div>
                <div><img src="" alt="">사진2</div>
                <div><img src="image/flower3.jpg" alt="">꽃사진3</div>
                <div><img src="image/flower4.jpg" alt="">꽃사진4</div>
                <div><img src="image/flower5.jpg" alt="">꽃사진5</div>
                <div><img src="image/bono.jpg" alt="">보노보노</div>
            </div>
        </div>
        <div id="volunteer-review">
            봉사후기
            <ul>
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
                <li>5</li>
            </ul>
            <button>dd</button>
        </div>
        <div id="adopt-able-animal">
            <table id="box3_table">
                <tr>
                    <td><input type="button" value="입양가능 동물"></td>
                    <td><input type="button" value="입양절차"></td>
                </tr>
            </table>
        </div>
    </div>

    <div id="main_right">
        <div class="main_right_box1">
            채팅
            <!-- table 사용-->
        </div>

        <div class="main_right_box2">
            <table id="box2_table">
                <tr>
                    <td>당신의 도움이 필요합니다</td>
                    <td><input type="button" value="dd"></td>
                    <!--td따로 id부여 수정 필요-->
                </tr>
            </table>
        </div>
    </div>

    <div id="main_bottom">
        sd
    </div>
</div>
    </main>
    <footer><%@include file="/views/template/footer.jsp"%></footer>
</body>

</html>