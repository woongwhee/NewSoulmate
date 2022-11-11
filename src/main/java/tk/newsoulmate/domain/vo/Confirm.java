package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Confirm {
    private int confirmNo;
    private int memberNo;
    private Date confirmTime;
    private int confirmCode;
    public Confirm(int memberNo, Date confirmTime, int confirmCode) {
        this.memberNo = memberNo;
        this.confirmTime = confirmTime;
        this.confirmCode = confirmCode;
    }
    public Confirm(int confirmNo, int memberNo, Date confirmTime, int confirmCode) {
        this.confirmNo = confirmNo;
        this.memberNo = memberNo;
        this.confirmTime = confirmTime;
        this.confirmCode = confirmCode;
    }

    public int getConfirmNo() {
        return confirmNo;
    }

    public void setConfirmNo(int confirmNo) {
        this.confirmNo = confirmNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public int getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(int confirmCode) {
        this.confirmCode = confirmCode;
    }
}
