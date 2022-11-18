package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Support {

    private int supportNo;
    private long shelterNo;
    private int memberNo;
    private String merchantUid;
    private long amount;
    private SupportStatus status;
    private Date payTime;
    private SupportType type;
    private boolean isWithdraw;

    public Support(int supportNo, long shelterNo, int memberNo, String merchantUid, long amount) {
        this.supportNo = supportNo;
        this.shelterNo = shelterNo;
        this.memberNo = memberNo;
        this.merchantUid = merchantUid;
        this.amount = amount;
    }

    public int getSupportNo() {
        return supportNo;
    }

    public long getShelterNo() {
        return shelterNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public String getMerchantUid() {
        return merchantUid;
    }

    public long getAmount() {
        return amount;
    }

    public SupportStatus getStatus() {
        return status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public SupportType getType() {
        return type;
    }

    public boolean isWithdraw() {
        return isWithdraw;
    }

    @Override
    public String toString() {
        return "Support{" +
            "supportNo=" + supportNo +
            ", shelterNo=" + shelterNo +
            ", memberNo=" + memberNo +
            ", merchantUid='" + merchantUid + '\'' +
            ", amount=" + amount +
            ", status=" + status +
            ", payTime=" + payTime +
            ", type=" + type +
            ", isWithdraw=" + isWithdraw +
            '}';
    }

    public boolean verify(long amount) {
        return this.amount == amount;
    }
}

enum SupportStatus {
    PENDING, DONE
}

enum SupportType {
    WD, DP
}