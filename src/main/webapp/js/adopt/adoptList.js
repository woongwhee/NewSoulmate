let page=1;
const $loadingImg=$("#loadingImg");
let wait=false;
window.addEventListener('scroll', () => {
    let val = window.innerHeight + window.scrollY;
    if(val >= document.body.offsetHeight&& !wait){
        page++;
        setTimeout(nextPage,2000);
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
            $loadingImg.css('display','block');
            wait=false;
        }
    })

}

