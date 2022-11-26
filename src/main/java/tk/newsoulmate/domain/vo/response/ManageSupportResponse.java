package tk.newsoulmate.domain.vo.response;

import java.time.LocalDate;

import tk.newsoulmate.domain.vo.type.WithdrawStatus;

public class ManageSupportResponse {

    private final long supportNo;
    private final String merchantUid;
    private final String shelterName;
    private final LocalDate payTime;
    private final long amount;
    private final String supporterName;
    private final WithdrawStatus status;
    private final String withdrawBank;
    private final String withdrawAccountNumber;
    private final String withdrawAccountName;
    public ManageSupportResponse(long supportNo, String merchantUid, String shelterName, LocalDate payTime, long amount, String supporterName,
                                 WithdrawStatus status, String withdrawBank, String withdrawAccountNumber, String withdrawAccountName) {
        this.supportNo = supportNo;
        this.merchantUid = merchantUid;
        this.shelterName = shelterName;
        this.payTime = payTime;
        this.amount = amount;
        this.supporterName = supporterName;
        this.status = status;
        this.withdrawBank = withdrawBank;
        this.withdrawAccountNumber = withdrawAccountNumber;
        this.withdrawAccountName = withdrawAccountName;
    }

    public long getSupportNo() {
        return supportNo;
    }

    public String getMerchantUid(){
        return merchantUid;
    }

    public String getShelterName() {
        return shelterName;
    }

    public LocalDate getPayTime() {
        return payTime;
    }

    public long getAmount() {
        return amount;
    }

    public String getSupporterName() {
        return supporterName;
    }

    public WithdrawStatus getStatus() {
        return status;
    }

    public String getWithdrawBank() {
        return withdrawBank;
    }

    public String getWithdrawAccountNumber() {
        return withdrawAccountNumber;
    }

    public String getWithdrawAccountName() {
        return withdrawAccountName;
    }

    @Override
    public String toString() {
        return "ManageSupportResponse{" +
                "shelterName='" + shelterName + '\'' +
                ", payTime=" + payTime +
                ", amount=" + amount +
                ", supporterName='" + supporterName + '\'' +
                ", status=" + status +
                ", withdrawBank='" + withdrawBank + '\'' +
                ", withdrawAccountNumber='" + withdrawAccountNumber + '\'' +
                ", withdrawAccountName='" + withdrawAccountName + '\'' +
                '}';
    }
}
