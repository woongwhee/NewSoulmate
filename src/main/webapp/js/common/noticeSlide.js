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
        infinite : true,
        dots : true
        });
    });
