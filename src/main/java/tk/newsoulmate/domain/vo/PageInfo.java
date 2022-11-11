package tk.newsoulmate.domain.vo;

public class PageInfo {

        private int listCount;// 현재 총 게시글 갯수
        private int currentPage;// 현재 페이지(즉, 사용자가 요청한 페이지)
        private int pageLimit;//페이지 하단에 보여질 페이징바의 페이지 최대 갯수
        private int boardLimit;//한페이지에 보여질 게시글의 최대 갯수(몇개단위씩)
        private int maxPage;//가장 마지막 페이지가 몇번 페이지 인지 (총 페이지 수)
        private int startPage;//페이지 하단에 보여질 첫페이지
        private int endPage;//페이지 하단에 보여질 마지막 페이지
        private String categoryName;



        public PageInfo() {

            super();
        }
        public PageInfo(int listCount,int currentPage){
            this.listCount=listCount;
            this.currentPage=currentPage;
            this.pageLimit=10;
            this.boardLimit=10;
            this.maxPage=listCount/boardLimit;
            this.startPage=currentPage/boardLimit*boardLimit+1;
            this.endPage=(currentPage/boardLimit+1)*(boardLimit);
            if(endPage>maxPage){
                endPage=maxPage;
            }
        }
        public PageInfo(int listCount,int currentPage,String categoryName){
            this.listCount=listCount;
            this.currentPage=currentPage;
            this.categoryName=categoryName;
            this.pageLimit=10;
            this.boardLimit=10;
            this.maxPage=listCount/boardLimit;
            this.startPage=currentPage/boardLimit*boardLimit+1;
            this.endPage=(currentPage/boardLimit+1)*(boardLimit);
            if(endPage>maxPage){
                endPage=maxPage;
            }
        }
        public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit, int maxPage, int startPage, int endPage, String categoryName) {
            this.listCount = listCount;
            this.currentPage = currentPage;
            this.pageLimit = pageLimit;
            this.boardLimit = boardLimit;
            this.maxPage = maxPage;
            this.startPage = startPage;
            this.endPage = endPage;
            this.categoryName = categoryName;
        }

    public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit, int maxPage, int startPage, int endPage) {
        this.listCount = listCount;
        this.currentPage = currentPage;
        this.pageLimit = pageLimit;
        this.boardLimit = boardLimit;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
    }


    public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getListCount() {
            return listCount;
        }

        public void setListCount(int listCount) {
            this.listCount = listCount;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPageLimit() {
            return pageLimit;
        }

        public void setPageLimit(int pageLimit) {
            this.pageLimit = pageLimit;
        }

        public int getBoardLimit() {
            return boardLimit;
        }

        public void setBoardLimit(int boardLimit) {
            this.boardLimit = boardLimit;
        }

        public int getMaxPage() {
            return maxPage;
        }

        public void setMaxPage(int maxPage) {
            this.maxPage = maxPage;
        }

        public int getStartPage() {
            return startPage;
        }

        public void setStartPage(int startPage) {
            this.startPage = startPage;
        }

        public int getEndPage() {
            return endPage;
        }

        public void setEndPage(int endPage) {
            this.endPage = endPage;
        }


}
