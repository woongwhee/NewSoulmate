const request={
    species:null,
    cityNo:null,
    villageNo:null,
    shelterNo:null,
    pageNo:1,
    numberOfRows:40
}
let requestChange=true;
let totalCount=0;
$(()=> {
    noticeSearch();
    getTotalCount();
    $('#species').on('change', () => {
        console.log($('#species').val());
        request.species = $('#species').val();
    });
    $('#cityNo').on('change', () => {
        request.cityNo = $('#cityNo').val();
        request.villageNo = null;
        request.shelterNo = null;
    });
    $('#villageNo').on('change', () => {
        request.villageNo = $('#villageNo').val();
        request.shelterNo = null;
    });
    $('#shelterNo').on('change', () => {
        request.shelterNo = $('#shelterNo').val();
    });
    $('#noticeSearch').click(()=>{
        request.pageNo=1;
        $('#notice-area').html("");
        noticeSearch();
        getTotalCount();
    });
    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() >= $(document).height()) {
            request.pageNo++;
            noticeSearch();
        }
    });
})






/**
 * 조회 조건이 바뀔시 총 조회 수 를 변경시켜주는 함수
 */
function getTotalCount(){
    $.ajax({
        url:'noticeCount',
        type: 'post',
        data:{'request':JSON.stringify(request)},
        success :(result)=>{
            totalCount=result.totalCount;
            let str= '총 조회 수 : '+totalCount;
            $('#count-area').text(str);
            requestChange=false;
        }
    })
}


function noticeSearch() {
    console.log(JSON.stringify(request))
    $.ajax({
        url:'noticeSearch',
        type:'post',
        data:{'request':JSON.stringify(request)},
        success:(result)=>{
            $('#notice-area').append(result);
        }
    })
}


function choice() {
    // 메인페이지 선택시 서브쿼리 삭제
    $("#villageNo").children().remove();
    $("#shelterNo").children().remove();
    $.ajax({
        url: "jqAjaxCity",
        data: {
            city: $("#cityNo").val()},
        success: function (result) {
            console.log(result, "어라?");
            let str = '<option value="" selected disabled hidden></option>';
            for (let i = 0; i < result.length; i++) {
                str += "<option value="+result[i].villageNo+ ">" + result[i].villageName + "</option>"
            }
            $("#villageNo").html(str);
        }
    })
}
function choiceShel() {
    // 메인페이지 선택시 서브쿼리 삭제
    $("#shelterNo").children().remove();

    $.ajax({
        url: "village.ax",
        data: {
            village: $("#villageNo").val()},
        success: function (result) {
            let str = '<option value="" selected disabled hidden></option>';
            for (let i = 0; i < result.length; i++) {
                str += "<option value="+result[i].shelterNo+ ">"
                    + result[i].shelterName + "</option>"
            }
            $("#shelterNo").html(str);
        }
    })
}


