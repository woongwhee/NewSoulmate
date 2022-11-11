    var $jq = jQuery.noConflict();
    $jq(document).ready(function() {
        $jq('a').click( function(event) {
            $jq(this).hide();
            event.preventDefault();
        });
    });

    $jq(function() {
    $jq( '.slider' ).slick( {
        autoplay: true,
        autoplaySpeed: 2000,
        slidesToShow : 5,
        infinite : true,
        dots : true,
        });
    });
