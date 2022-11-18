package tk.newsoulmate.domain.vo;

public class Report {
    private int reportNo;
    private int categoryNo;
    private int boardNo;
    private int replyNo;
    private String reportContent;
    private String status;
    public Report(int reportNo, int categoryNo, int boardNo, int replyNo, String reportContent) {
        this.reportNo = reportNo;
        this.categoryNo = categoryNo;
        this.boardNo = boardNo;
        this.replyNo = replyNo;
        this.reportContent = reportContent;
    }

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
