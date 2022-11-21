package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class SupportCompleteResponse {
    private long supportNo;
    private String shelterName;
    private long amount;
    private Date payTime;

    public SupportCompleteResponse(long supportNo, String shelterName, long amount, Date payTime) {
        this.supportNo = supportNo;
        this.shelterName = shelterName;
        this.amount = amount;
        this.payTime = payTime;
    }

    public long getSupportNo() {
        return supportNo;
    }

    public String getShelterName() {
        return shelterName;
    }

    public long getAmount() {
        return amount;
    }

    public Date getPayTime() {
        return payTime;
    }
}
