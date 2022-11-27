package tk.newsoulmate.domain.vo;

public class SupportPage {
    private final int startSupport;
    private int endSupport;
    private final int page;
    private final int startPage;
    private int endPage;
    private final int maxPage;

    public SupportPage(int page, int totalCount) {
        int pageCount = 10;
        int defaultSize = 15; // 한 페이지에 나오는 게시물 수

        this.page = page;
        this.startSupport = (defaultSize * (page - 1)) + 1;
        this.endSupport = page * defaultSize;

        this.startPage = (page - 1) / pageCount * pageCount + 1;
        this.endPage = this.startPage + pageCount - 1;
        this.maxPage = (int) Math.ceil((double) totalCount / defaultSize);

        if(endPage > maxPage){
            endPage=maxPage;
        }
        if (this.endSupport > totalCount) {
            this.endSupport = totalCount;
        }
    }

    public int getStartSupport() {
        return startSupport;
    }

    public int getEndSupport() {
        return endSupport;
    }

    public int getPage() {
        return page;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    @Override
    public String toString() {
        return "SupportPage{" +
                "startSupport=" + startSupport +
                ", endSupport=" + endSupport +
                ", page=" + page +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", maxPage=" + maxPage +
                '}';
    }
}
