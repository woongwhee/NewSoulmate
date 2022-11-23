package tk.newsoulmate.domain.vo;

import tk.newsoulmate.domain.vo.type.MemberGrade;

import java.sql.Date;

public class ManageMember {

    private long shelterNo;
    private String shelterName;
    private int memberNo;
    private String memberId;
    private String memberName;
    private String email;
    private String nickName;
    private MemberGrade memberGrade;
    private Date resentConnection;
    private Date enrollDate;
    public ManageMember() {
        super();
    }

    public ManageMember(long shelterNo, String shelterName, int memberNo, String memberId, String memberName, String email, String nickName, MemberGrade memberGrade, Date resentConnection, Date enrollDate) {
        this.shelterNo = shelterNo;
        this.shelterName = shelterName;
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberName = memberName;
        this.email = email;
        this.nickName = nickName;
        this.memberGrade = memberGrade;
        this.resentConnection = resentConnection;
        this.enrollDate = enrollDate;
    }

    public long getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(long shelterNo) {
        this.shelterNo = shelterNo;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public MemberGrade getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(MemberGrade memberGrade) {
        this.memberGrade = memberGrade;
    }

    public Date getResentConnection() {
        return resentConnection;
    }

    public void setResentConnection(Date resentConnection) {
        this.resentConnection = resentConnection;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }
}
