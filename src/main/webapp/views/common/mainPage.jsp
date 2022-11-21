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
            <div id="comment2" onclick="location.href='<%=request.getContextPath()%>/adoptReList'">입양후기+</div>
                <div id="adopt-review-list">
                    <img src="" alt="">
                    <img src="" alt="">
                    <img src="" alt="">
                    <img src="" alt="">
                    <img src="" alt="">
                </div>
        </div>
        <div id="volunteer-review">
            <div id="comment3" onclick="location.href='<%=request.getContextPath()%>/'">봉사후기+</div>
            <div id="volunteer-review-list">
                <ul>
                    <li><a href="#">봉사후기</a></li>
                    <li><a href="#">봉사후기</a></li>
                    <li><a href="#">봉사후기</a></li>
                    <li><a href="#">봉사후기</a></li>
                    <li><a href="#">봉사후기</a></li>
                </ul>
            </div>
            <div id="btn-area">
            <button id="shelterbtn" onclick="location.href='<%=request.getContextPath()%>/shelterList'">전국 유기동물<br>보호소 보러가기</button>
            </div>
        </div>
        <div id="adopt-btn-box">
            <div><button id="adopt-able-animal" onclick="">입양가능 동물</button></div>
            <div><button id="adopt-step" onclick="">입양절차 안내</button></div>
        </div>
    </div>

    <div id="main_right">
        <div class="main_right_box1">
            <div class="wrap">
                <div class="chat ch1">
                    <div class="textbox">입양하고싶은데 절차가 어떻게 되나요?</div>
                </div>
                <div class="chat ch2">
                    <div class="textbox">입양신청서 작성 > 상담 및 동물과의 만남 > 입양 순으로 이루어 집니다.
                                         자세한 내용은 홈페이지 내 입양절차안내 페이지를 통해 확인가능합니다.</div>
                </div>
                <div class="chat ch1">
                    <div class="textbox">Q</div>
                </div>
                <div class="chat ch2">
                    <div class="textbox">A</div>
                </div>
                <div class="chat ch1">
                    <div class="textbox">Q</div>
                </div>
                <div class="chat ch2">
                    <div class="textbox">A</div>
                </div>
            </div>
        </div>

        <div class="main_right_box2">
            <div id="support-box">
                <div  id="comment4">후원유도멘트</div>
                <button id="supportBtn"  onclick="location.href='<%=request.getContextPath()%>/supportpayment'">후원하러 가기</button>
            </div>
        </div>
    </div>

    <div id="main_bottom">
        <div id="graph_1">
            <div class="graph_comment">축종별 유기율</div>
            <div><img src="${context}/image/유기율.png"></div>
        </div>
        <div id="graph_2">
            <div class="graph_comment">유기동물 안락사율</div>
            <div><img src="${context}/image/안락사율.png"></div>
        </div>
    </div>
</div>
    </main>
    <footer><%@include file="/views/template/footer.jsp"%></footer>
</body>

</html>