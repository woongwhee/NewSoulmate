package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Board {
    private int boardNo;
    private int memberNo;
    private BoardType boardType;
    private String boardTitle;
    private String boardContent;
    private int readCount;
    private int reportNo;
    private String memberName;
    private String nickName;
    private Date createDate;
    private String resultStatus;
    private int fileCount;

    private int categoryNo;

    private Date issueDate;
    private String memberId;
    private String CategoryName;

    private Board(){}
    /*
        새로운 생성자가 필요할시 생성자를 호출하게 하지말고 펙토리얼메소드를 호출하도록 작성할것
     */
    /**
     * 봉사 후기,입양 후기 글작성용 펙토리얼메소드
     * @param memberNo
     * @param boardNo
     * @param issueDate
     * @param boardType
     * @param boardTitle
     * @param boardContent
     * @return
     */
    public static Board enrollBoard(int memberNo,int boardNo,Date issueDate,BoardType boardType,String boardTitle,String boardContent ){
        Board b=new Board();
        b.setBoardNo(boardNo);
        b.setIssueDate(issueDate);
        b.setBoardTitle(boardTitle);
        b.setBoardContent(boardContent);
        b.setBoardType(boardType);
        b.setMemberNo(memberNo);
        return b;
    }

    /**
     * 신고접수용 펙토리얼메소드
     * @param memberNo
     * @param boardType
     * @param boardTitle
     * @param boardContent
     * @param reportNo 신고분류
     * @return
     */
    public static Board enrollReport(int memberNo,String boardType,String boardTitle,String boardContent,int reportNo){
        Board b=new Board();
        b.setBoardTitle(boardTitle);
        b.setBoardContent(boardContent);
        b.setBoardType(boardType);
        b.setMemberNo(memberNo);
        return b;
    }

    public static Board selectAdoptReviewList(int boardNo, String boardTitle, String member_no, int readCount, Date createDate) {
        Board b = new Board();
        b.setBoardNo(boardNo);
        b.setBoardTitle(boardTitle);
        b.setMemberNo(Integer.parseInt(member_no));
        b.setReadCount(readCount);
        b.setCreateDate(createDate);
        return b;
    }


    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     *  QnA 리스트용 펙토리얼메소드
     * @param resultStatus
     * @param boardNo
     * @param boardTitle
     * @param createDate
     * @param memberName
     * @return
     */
    public static Board selectQnAList(String resultStatus,int boardNo,String boardTitle,Date createDate,String memberName){
        Board b=new Board();
        b.setResultStatus(resultStatus);
        b.setBoardNo(boardNo);
        b.setBoardTitle(boardTitle);
        b.setCreateDate(createDate);
        b.setMemberName(memberName);
        return b;
    }

    public static Board selectMyPageBoardList(int boardNo, String boardTitle, Date createDate, int readCount){
        Board b = new Board();
        b.setBoardNo(boardNo);
        b.setBoardTitle(boardTitle);
        b.setCreateDate(createDate);
        b.setReadCount(readCount);
        return b;
    }

    /**
     * 1:1문의 작성하기
     * @param categoryNo
     * @param boardTitle
     * @param boardContent
     * @param memberNo
     * @return
     */
    public static Board insertInquire(String categoryNo, String boardTitle, String boardContent, String memberNo){
        Board b = new Board();
        b.setCategoryNo(Integer.parseInt(categoryNo));
        b.setBoardTitle(boardTitle);
        b.setBoardContent(boardContent);
        b.setMemberNo(Integer.parseInt(memberNo));
        b.setBoardType(BoardType.QNA);
        return b;
    }

    public static Board updateInquire(int boardNo, String categoryName, String boardTitle, String boardContent){
        Board b = new Board();
        b.setBoardNo(boardNo);
        b.setCategoryName(categoryName);
        b.setBoardTitle(boardTitle);
        b.setBoardContent(boardContent);
        return b;
    }



    /**
     * DB에서 입양후기리스트,봉사후기리스트를 불러오기 위한 생성함수
     * @param boardNo
     * @param boardTitle
     * @return
     */
    public static Board selectThumbList(int boardNo,String boardTitle){
        Board b=new Board();
        b.setBoardTitle(boardTitle);
        b.setBoardNo(boardNo);
        return b;
    }

    /**
     * 리뷰 상세보기용 팩토리얼 메소드
     * @param boardNo
     * @param boardName
     * @param boardTitle
     * @param createDate
     * @param issueDate
     * @param readCount
     * @return
     */
    public static Board selectReviewDetail(int boardNo,String boardName,String boardTitle,Date createDate,Date issueDate,int readCount){
        Board b=new Board();
        b.setBoardNo(boardNo);
        b.setBoardTitle(boardTitle);
        b.setCreateDate(createDate);
        b.setReadCount(readCount);
        b.setIssueDate(issueDate);
        return b;
    }

    /**
     * 후기 상세보기용 팩토리얼메소드
     */
    public static Board selectReviewDetail(int boardNo, String boardTitle, int memberNo, String nickName, Date issueDate , Date createDate, int readCount, String boardContent){
        Board b = new Board();
        b.setBoardNo(boardNo);
        b.setBoardTitle(boardTitle);
        b.setMemberNo(memberNo);
        b.setMemberName(nickName);
        b.setIssueDate(issueDate);
        b.setCreateDate(createDate);
        b.setReadCount(readCount);
        b.setBoardContent(boardContent);
        return b;
    }

    public static Board selectInquireBoard(int boardNo, String categoryName, String boardTitle, String boardContent, String memberId, Date createDate) {
        Board b = new Board();
        b.setBoardNo(boardNo);
        b.setCategoryName(categoryName);
        b.setBoardTitle(boardTitle);
        b.setBoardContent(boardContent);
        b.setMemberId(memberId);
        b.setCreateDate(createDate);


        return b;
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

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardName) {
        this.boardType = BoardType.valueOfName(boardName);
    }
    public void setBoardType(int typeNo) {
        this.boardType = BoardType.valueOfNo(typeNo);
    }
    public void setBoardType(BoardType boardType) {
        this.boardType =boardType;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCategoryName() {
        return CategoryName;
    }
    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
