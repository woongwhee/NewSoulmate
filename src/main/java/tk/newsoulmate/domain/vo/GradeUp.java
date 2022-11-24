package tk.newsoulmate.domain.vo;

public class GradeUp {

    private int GradeNo;

    private String memberName;
    private String memberId;
    private int memberNo;
    private long shelterNo;
    private int fileNo;
    private String shelterTel;
    private String shelterLandLine;
    private String shelterCompNo;
    private String gradeStatus;
    private Attachment attachment;
    private String shelterAddress;
    private String shelterName;

    public GradeUp(){
        super();
    }


    public GradeUp(int gradeNo, int memberNo, long shelterNo, int fileNo, String shelterTel, String shelterLandLine, String shelterCompNo, String gradeStatus, String shelterAddress) {
        GradeNo = gradeNo;
        this.memberNo = memberNo;
        this.shelterNo = shelterNo;
        this.fileNo = fileNo;
        this.shelterTel = shelterTel;
        this.shelterLandLine = shelterLandLine;
        this.shelterCompNo = shelterCompNo;
        this.gradeStatus = gradeStatus;
        this.shelterAddress = shelterAddress;
    }

    public GradeUp(int gradeNo, int memberNo, long shelterNo, int fileNo, String shelterTel, String shelterLandLine, String shelterCompNo, String shelterAddress) {
        GradeNo = gradeNo;
        this.memberNo = memberNo;
        this.shelterNo = shelterNo;
        this.fileNo=fileNo;
        this.shelterTel = shelterTel;
        this.shelterLandLine = shelterLandLine;
        this.shelterCompNo = shelterCompNo;
        this.shelterAddress = shelterAddress;
    }

    public GradeUp(int memberNo, long shelterNo, int fileNo, String shelterTel, String shelterLandLine, String shelterCompNo,  String shelterAddress) {
        this.memberNo = memberNo;
        this.shelterNo = shelterNo;
        this.fileNo = fileNo;
        this.shelterTel = shelterTel;
        this.shelterLandLine = shelterLandLine;
        this.shelterCompNo = shelterCompNo;
        this.shelterAddress = shelterAddress;
    }

    public GradeUp(int memberNo, long shelterNo, String shelterTel, String shelterLandLine, String shelterCompNo, String shelterAddress) {
        this.memberNo = memberNo;
        this.shelterNo = shelterNo;
        this.shelterTel = shelterTel;
        this.shelterLandLine = shelterLandLine;
        this.shelterCompNo = shelterCompNo;
        this.shelterAddress = shelterAddress;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public int getGradeNo() {
        return GradeNo;
    }

    public void setGradeNo(int gradeNo) {
        GradeNo = gradeNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public long getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(long shelterNo) {
        this.shelterNo = shelterNo;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public String getShelterTel() {
        return shelterTel;
    }

    public void setShelterTel(String shelterTel) {
        this.shelterTel = shelterTel;
    }

    public String getShelterLandLine() {
        return shelterLandLine;
    }

    public void setShelterLandLine(String shelterLandLine) {
        this.shelterLandLine = shelterLandLine;
    }

    public String getShelterCompNo() {
        return shelterCompNo;
    }

    public void setShelterCompNo(String shelterCompNo) {
        this.shelterCompNo = shelterCompNo;
    }

    public String getGradeStatus() {
        return gradeStatus;
    }

    public void setGradeStatus(String gradeStatus) {
        this.gradeStatus = gradeStatus;
    }

    public String getShelterAddress() {
        return shelterAddress;
    }

    public void setShelterAddress(String shelterAddress) {
        this.shelterAddress = shelterAddress;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "GradeUp{" +
                "GradeNo=" + GradeNo +
                ", memberNo=" + memberNo +
                ", shelterNo=" + shelterNo +
                ", fileNo=" + fileNo +
                ", shelterTel='" + shelterTel + '\'' +
                ", shelterLandLine='" + shelterLandLine + '\'' +
                ", shelterCompNo='" + shelterCompNo + '\'' +
                ", gradeStatus='" + gradeStatus + '\'' +
                ", shelterAddress='" + shelterAddress + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}
