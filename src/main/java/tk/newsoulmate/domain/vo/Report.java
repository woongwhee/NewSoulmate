package tk.newsoulmate.domain.vo;

import tk.newsoulmate.domain.vo.type.ReportType;

public class Report {
    private int reportNo;
    private int categoryNo;
    private ReportType refType;
    private int refNo;
    private String reportContent;
    private transient String status;
    public Report(int reportNo, int categoryNo,String reportType, String reportContent) {
        this.reportNo = reportNo;
        this.categoryNo = categoryNo;
        this.refType=ReportType.valueOfName(reportType);
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

    public ReportType getRefType() {
        return refType;
    }

    public void setRefType(ReportType refType) {
        this.refType = refType;
    }

    public int getRefNo() {
        return refNo;
    }

    public void setRefNo(int refNo) {
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
