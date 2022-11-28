    // var $jq = jQuery.noConflict();
    // $jq(document).ready(function() {
    //     $jq('a').click( function(event) {
    //         $jq(this).hide();
    //         event.preventDefault();
    //     });
    // });

    $(function() {
    $('.slider').slick( {
        autoplay: true,
        autoplaySpeed: 2000,
        slidesToShow : 5,
        slideToggle:5,
        infinite : false,
        dots : true
        });
    });
    $('.slider').on('edge',getNoticeNext);
    let noticePage=1;
    function getNoticeNext(){
        $.ajax({
                url: 'noticeNext',
                type:'post',
                date: {page:noticePage++},
                success:(result)=>$('.slice').slick('slickAdd',result)
            }
        )

    }

    $(()=>{
        setNoticeLocation();
    })
    function setNoticeLocation(){
        $(".notice-img").click(e=>location.href="noticeDetail?dno="+$(e.target).prop('id'));
    }
    $('.adopt-thumnail').click(e=>location.href='adoptRevDetail?bno='+$(e.target).data('bno'));
    $('#adopt-able-animal').click(()=>$(location).attr('href','noticeList'));
    $('#adopt-step').click(()=>$(location).attr('href','adoptStep'));