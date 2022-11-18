package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Reply {
    private int replyNo;
    private int boardNo;
    private int noticeNo;
    private int memberNo;
    private String replyWriter;
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
    public Reply(int replyNo, int boardNo,int memberNo, String replyWriter,String replyContent, Date replyDate) {
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
}
