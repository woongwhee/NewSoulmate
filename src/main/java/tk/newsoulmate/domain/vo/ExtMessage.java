package tk.newsoulmate.domain.vo;


import java.sql.Date;

public class ExtMessage{
    private int messageNo;
    private int fromMemberNo;
    private int toMemberNo;
    private Date messageDate;
    private String TelNum;
    private String messageContent;
    public ExtMessage() {
    }

    public int getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(int messageNo) {
        this.messageNo = messageNo;
    }

    public int getFromMemberNo() {
        return fromMemberNo;
    }

    public void setFromMemberNo(int fromMemberNo) {
        this.fromMemberNo = fromMemberNo;
    }

    public int getToMemberNo() {
        return toMemberNo;
    }

    public void setToMemberNo(int toMemberNo) {
        this.toMemberNo = toMemberNo;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getTelNum() {
        return TelNum;
    }

    public void setTelNum(String telNum) {
        TelNum = telNum;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
