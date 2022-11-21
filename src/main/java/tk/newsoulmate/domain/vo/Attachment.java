package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Attachment {
	
	private int fileNo;
	private int boardNo;
	private int replyNo;
	private String originName;
	private String boardTitle;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int filelevel;
	private String status;
	
	private Attachment() {
		super();
	}

	public static Attachment getInstance(String originName, String changeName, String filePath){
		Attachment at=new Attachment(originName,changeName,filePath);
		return at;
	}
    private Attachment(String originName, String changeName, String filePath) {
		this.originName=originName;
		this.changeName=changeName;
		this.filePath=filePath;

    }

    /**
	 * 게시글용 첨부파일 팩토리얼
	 * @param fileNo
	 * @param boardNo
	 * @param originName
	 * @param changeName
	 * @param filePath
	 * @param uploadDate
	 * @return
	 */
	public static Attachment fileAttachment(int fileNo, int boardNo, String originName, String changeName, String filePath, Date uploadDate) {
		Attachment at=new Attachment();
		at.setFileNo(fileNo);
		at.setBoardNo(boardNo);
		at.setOriginName(originName);
		at.setChangeName(changeName);
		at.setFilePath(filePath);
		at.setUploadDate(uploadDate);
		return at;
	}

	/**
	 * 댓글 첨부파일용 생성 팩토리얼메서드
	 * @param fileNo
	 * @param replyNo
	 * @param originName
	 * @param changeName
	 * @param filePath
	 * @param uploadDate
	 * @return
	 */
	public static Attachment replyAttachment(int fileNo, int replyNo, String originName, String changeName, String filePath, Date uploadDate) {
		Attachment at=new Attachment();
		at.setFileNo(fileNo);
		at.setBoardNo(replyNo);
		at.setOriginName(originName);
		at.setChangeName(changeName);
		at.setFilePath(filePath);
		at.setUploadDate(uploadDate);
		return at;
	}
	public static Attachment getThumnailInstance(int boardNo, String originName, String boardTitle, String changeName, String filePath){
		Attachment at=new Attachment(boardNo, originName, boardTitle, changeName, filePath);
		return at;
	}

	private Attachment(int boardNo, String originName, String boardTitle, String changeName, String filePath) {
		this.boardNo = boardNo;
		this.originName = originName;
		this.boardTitle = boardTitle;
		this.changeName = changeName;
		this.filePath = filePath;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getFilelevel() {
		return filelevel;
	}

	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}


	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return filelevel;
	}

	public void setFilelevel(int filelevel) {
		this.filelevel = filelevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refNo=" + boardNo + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", filelevel=" + filelevel
				+ ", status=" + status + "]";
	}
	
}
