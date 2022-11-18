package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Member {
    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String phone;//전화번호
    private String email;//이메일
    private String nickName;//닉네임
    private MemberGrade memberGrade;//유저등급
    private Long shelterNo;
    private Date resentConnection;//최근접속일
    private Date enrollDate;
    public Member() {
    }

    public Member(String memberId, String memberPwd, String memberName, String nickName, String Phone, String Email) {
        super();
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.nickName = nickName;
        this.phone = Phone;
        this.email = Email;
        this.memberGrade=MemberGrade.USER;
    }

    public Member(int memberNo, String memberId, String memberName, String phone, String email, String nickName, MemberGrade memberGrade) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberName = memberName;
        this.phone = phone;
        this.email = email;
        this.nickName = nickName;
        this.memberGrade = memberGrade;
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

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
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

    public Long getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(Long shelterNo) {
        this.shelterNo = shelterNo;
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
