// 링크 안넘어 가는 문제로 js파일 분리 보류중

// 전체선택, 전체해제
$(function(){
    $("[type=checkbox][name=agreeCheckBox]").on("change", function(){
        const check = $(this).prop("checked");

        if($(this).hasClass("allcheck")){
            $("[type=checkbox][name=agreeCheckBox]").prop("checked", check);


        }else{
            const all = $("[type=checkbox][name=agreeCheckBox].allcheck");
            const allcheck = all.prop("checked")
            if(check != allcheck){
                const len = $("[type=checkbox][name=agreeCheckBox]").not(".allcheck").length;
                const ckLen = $("[type=checkbox][name=agreeCheckBox]:checked").not(".allcheck").length;
                if(len === ckLen){
                    all.prop("checked", true);
                }else{
                    all.prop("checked", false);
                }
            }
        }

    });

// 동의 항목 모두 체크돼야 회원가입 페이지로 넘어갈 수 있게
    $("#signupBtn").click(function () {

        if ($('.required_checked:checked').length != $('.required_checked').length) {
            alert("동의 항목을 모두 선택해주세요.");
            return;
        }
        $(location).attr("href", getContextPath()+"/MemberSignup");
    });

    function getContextPath(){
        let hostIndex = location.href.indexOf( location.host ) + location.host.length;
        let contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
        return contextPath;
    }
});