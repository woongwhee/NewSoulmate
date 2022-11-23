let request={
    bgndate:null,
    enddate:null,
    species:null,
    cityNo:null,
    villageNo:null,
    shelterNo:null,
    state:'protect',
    pageNo:1,
    numberOfRows:40
}
$(()=> {
    noticeSearch();
    $('#bgndate').on('change', () => {
        request.bgndate = $('#bgndate').val();
    });
    $('#enddate').on('change', () => {
        request.enddate = $('#enddate').val();
    });
    $('#bread').on('change', () => {
        request.sepcies = $('#breed').val();
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
        noticeSearch()
    })


})

function noticeSearch() {
    console.log(JSON.stringify(request))
    $.ajax({
        url:'noticeSearch',
        type:'post',
        data:{'request':JSON.stringify(request)},
        success:(result)=>{
            $('#notice-area').html(result);
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


