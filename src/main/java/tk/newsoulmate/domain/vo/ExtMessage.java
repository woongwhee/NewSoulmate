package tk.newsoulmate.domain.vo;


import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class ExtMessage{
    private int messageNo;
    private int fromMemberNo;
    @SerializedName("toMemberNo")
    private int toMemberNo;
    private Date messageDate;
    @SerializedName("telNum")
    private String telNum;
    @SerializedName("messageContent")
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
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
