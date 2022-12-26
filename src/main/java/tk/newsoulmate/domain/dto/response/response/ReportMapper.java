package tk.newsoulmate.domain.dto.response.response;

import tk.newsoulmate.domain.vo.Report;

public class ReportMapper {
    private Report reportContent;

    public Report getReportContent() {
        return reportContent;
    }

    public void setReportContent(Report reportContent) {
        this.reportContent = reportContent;
    }
}
