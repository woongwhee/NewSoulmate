let page=1;
const $loadingImg=$("#loadingImg");
let wait=false;
window.addEventListener('scroll', () => {
    let val = window.innerHeight + window.scrollY;
    if(val >= document.body.offsetHeight&& !wait){
        page++;
        $loadingImg.css('display','block');
        nextPage();
    }
});
function nextPage(){
    wait=true;
    $.ajax({
        url:'adoptRevNext',
        data:{'page':page},
        type:'post',
        success:(result)=>{
            $("#adopt-review-area").append(result);
            ++page;
        },
        error:(result)=>{
            console.log(result)
        },
        complete:()=>{
            wait=false;
            $loadingImg.css('display','none');
        }
    })
}

