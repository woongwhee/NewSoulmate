package tk.newsoulmate.domain.vo;

import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.domain.vo.type.ReportType;

public class Report {
    private int reportNo;
    private int categoryNo;
    private String categoryName;
    private ReportType refType;
    private long refNo;
    private String reportContent;
    private String status;
    private BoardType boardType;
    public Report(){
        super();
    }
    public Report(int reportNo, int categoryNo,String reportType, String reportContent) {
        this.reportNo = reportNo;
        this.categoryNo = categoryNo;
        this.refType=ReportType.valueOfName(reportType);
        this.reportContent = reportContent;
    }

    public Report(int reportNo,String categoryName, int refType, long refNo,  String reportContent) {
        this.reportNo = reportNo;
        this.categoryName = categoryName;
        this.refType = ReportType.valueOfNo(refType);
        this.refNo = refNo;
        this.reportContent = reportContent;
    }

    public Report(int reportNo, long refNo, int typeNo) {
        this.reportNo = reportNo;
        this.refNo = refNo;
        this.boardType = BoardType.valueOfNo(typeNo);
    }

    public Report(int reportNo, int categoryNo, int refType, long refNo, String boardTitle, String reportContent, String status, String boardType) {
        this.reportNo = reportNo;
        this.categoryNo = categoryNo;
        this.refType = ReportType.valueOfNo(refType);
        this.refNo = refNo;
        this.reportContent = reportContent;
        this.status = status;
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

    public ReportType getRefType() {
        return refType;
    }

    public void setRefType(ReportType refType) {
        this.refType = refType;
    }

    public long getRefNo() {
        return refNo;
    }

    public void setRefNo(long refNo) {
        this.refNo = refNo;
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


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Report{");
        sb.append("reportNo=").append(reportNo);
        sb.append(", categoryNo=").append(categoryNo);
        sb.append(", refType=").append(refType);
        sb.append(", refNo=").append(refNo);
        sb.append(", reportContent='").append(reportContent).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
