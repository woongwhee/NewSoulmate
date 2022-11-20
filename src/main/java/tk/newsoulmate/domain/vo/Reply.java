package tk.newsoulmate.domain.vo;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Reply {
    private int replyNo;
    @SerializedName("boardNo")
    private int boardNo;
    @SerializedName("noticeNo")
    private int noticeNo;
    @SerializedName("memberNo")
    private int memberNo;
    private String replyWriter;
    @SerializedName("replyContent")
    private String replyContent;

    private ReplyType replyType;
    private Date replyDate;

    public Reply() {
    }

    /**
     * 댓글작성용 생성자
     */
    public Reply(int boardNo, int memberNo, String replyContent, ReplyType replyType) {
        this.boardNo = boardNo;
        this.memberNo = memberNo;
        this.replyContent = replyContent;
        this.replyType = replyType;
    }

    /**
     * 사진댓글 불러오기용 생성자
     */
    public Reply(int replyNo, int boardNo, int memberNo, String replyContent, ReplyType replyType, Date replyDate) {
        this.replyNo = replyNo;
        this.boardNo = boardNo;
        this.memberNo = memberNo;
        this.replyContent = replyContent;
        this.replyType = replyType;
        this.replyDate = replyDate;
    }

    /**
     * 불러오기용 생성자
     */
    public Reply(int replyNo,int memberNo, String replyWriter,String replyContent, Date replyDate) {
        this.replyNo = replyNo;
        this.boardNo=boardNo;
        this.memberNo = memberNo;
        this.replyWriter=replyWriter;
        this.replyContent = replyContent;
        this.replyDate = replyDate;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public ReplyType getReplyType() {
        return replyType;
    }

    public void setReplyType(ReplyType replyType) {
        this.replyType = replyType;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyWriter() {
        return replyWriter;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public void setReplyWriter(String replyWriter) {
        this.replyWriter = replyWriter;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Reply{");
        sb.append("replyNo=").append(replyNo);
        sb.append(", boardNo=").append(boardNo);
        sb.append(", noticeNo=").append(noticeNo);
        sb.append(", memberNo=").append(memberNo);
        sb.append(", replyWriter='").append(replyWriter).append('\'');
        sb.append(", replyContent='").append(replyContent).append('\'');
        sb.append(", replyType=").append(replyType);
        sb.append(", replyDate=").append(replyDate);
        sb.append('}');
        return sb.toString();
    }
}
