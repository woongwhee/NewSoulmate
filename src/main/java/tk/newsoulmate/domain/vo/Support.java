package tk.newsoulmate.domain.vo;

import java.sql.Date;

import tk.newsoulmate.domain.type.SupportStatus;
import tk.newsoulmate.domain.type.WithdrawStatus;

public class Support {

    private int supportNo;
    private long shelterNo;
    private String shelterName;
    private int memberNo;
    private String memberName;
    private String phoneNumber;
    private String merchantUid;
    private long amount;
    private SupportStatus status;
    private Date payTime;
    private SupportType type;
    private WithdrawStatus withdrawStatus;
    private String withdrawBank;
    private String withdrawAccountNumber;
    private String withdrawAccountName;

    public Support(int supportNo, String shelterName, long amount, Date payTime) {
        this.supportNo = supportNo;
        this.shelterName = shelterName;
        this.amount = amount;
        this.payTime = payTime;
    }

    public Support(int supportNo, String memberName, Date payTime, long amount, String phoneNumber, WithdrawStatus withdrawStatus) {
        this.supportNo = supportNo;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.payTime = payTime;
        this.withdrawStatus = withdrawStatus;
    }

    public Support(int supportNo, long shelterNo, int memberNo, String merchantUid, long amount) {
        this.supportNo = supportNo;
        this.shelterNo = shelterNo;
        this.memberNo = memberNo;
        this.merchantUid = merchantUid;
        this.amount = amount;
    }

    public Support(int supportNo, String merchantUid, String shelterName, Date payTime, long amount, String memberName,
                   WithdrawStatus withdrawStatus, String withdrawBank, String withdrawAccountNumber, String withdrawAccountName) {
        this.supportNo = supportNo;
        this.shelterName = shelterName;
        this.memberName = memberName;
        this.merchantUid = merchantUid;
        this.amount = amount;
        this.payTime = payTime;
        this.withdrawStatus = withdrawStatus;
        this.withdrawBank = withdrawBank;
        this.withdrawAccountNumber = withdrawAccountNumber;
        this.withdrawAccountName = withdrawAccountName;
    }

    public Support(int supportNo, String shelterName, String memberName, String merchantUid, long amount,
                   Date payTime, SupportStatus status, WithdrawStatus withdrawStatus) {
        this.supportNo = supportNo;
        this.shelterName = shelterName;
        this.memberName = memberName;
        this.merchantUid = merchantUid;
        this.amount = amount;
        this.status = status;
        this.payTime = payTime;
        this.withdrawStatus = withdrawStatus;
    }

    public boolean verify(long amount) {
        return this.amount == amount;
    }

    public int getSupportNo() {
        return supportNo;
    }

    public long getShelterNo() {
        return shelterNo;
    }

    public String getShelterName() {
        return shelterName;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public WithdrawStatus getWithdrawStatus() {
        return withdrawStatus;
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
}

enum SupportType {
    WD, DP
}