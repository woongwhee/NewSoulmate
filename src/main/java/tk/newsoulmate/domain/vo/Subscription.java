package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Subscription {

    private int subNo;
    private int memberNo;
    private long shelterNo;
    private Long animalNo;
    private String telNum;
    private String name;
    private String gender;
    private String adoptReason;
    private String agreement;
    private String whenSick;
    private String bigDuty;
    private String wishDate;
    private String subRead;
    private Date subDate;

    private String memberId;

    public Subscription(){
        super();
    }

    public Subscription(int subNo, Long animalNo, String memberId, String name, String telNum, Date subDate, String subRead) {
        this.subNo = subNo;
        this.animalNo = animalNo;
        this.telNum = telNum;
        this.name = name;
        this.subRead = subRead;
        this.subDate = subDate;
        this.memberId = memberId;
    }

    public Subscription(int subNo, int memberNo, long shelterNo, long animalNo, String telNum, String name, String gender, String adoptReason, String agreement, String whenSick, String bigDuty, String wishDate, String subRead, Date subDate) {
        this.subNo = subNo;
        this.memberNo = memberNo;
        this.shelterNo = shelterNo;
        this.animalNo = animalNo;
        this.telNum = telNum;
        this.name = name;
        this.gender = gender;
        this.adoptReason = adoptReason;
        this.agreement = agreement;
        this.whenSick = whenSick;
        this.bigDuty = bigDuty;
        this.wishDate = wishDate;
        this.subRead = subRead;
        this.subDate = subDate;
    }

    public int getSubNo() {
        return subNo;
    }

    public long getAnimalNo() {
        return animalNo;
    }

    public void setAnimalNo(long animalNo) {
        this.animalNo = animalNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public long getShelterNo() {
        return shelterNo;
    }


    public String getTelNum() {
        return telNum;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAdoptReason() {
        return adoptReason;
    }

    public String getAgreement() {
        return agreement;
    }

    public String getWhenSick() {
        return whenSick;
    }

    public String getBigDuty() {
        return bigDuty;
    }

    public String getWishDate() {
        return wishDate;
    }

    public String getSubRead() {
        return subRead;
    }



    public String getMemberId() {
        return memberId;
    }

    public void setSubNo(int subNo) {
        this.subNo = subNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setShelterNo(long shelterNo) {
        this.shelterNo = shelterNo;
    }


    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAdoptReason(String adoptReason) {
        this.adoptReason = adoptReason;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public void setWhenSick(String whenSick) {
        this.whenSick = whenSick;
    }

    public void setBigDuty(String bigDuty) {
        this.bigDuty = bigDuty;
    }

    public void setWishDate(String wishDate) {
        this.wishDate = wishDate;
    }

    public void setSubRead(String subRead) {
        this.subRead = subRead;
    }



    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    @Override
    public String
    toString() {
        return "subscription{" +
                "subNo=" + subNo +
                ", memberNo=" + memberNo +
                ", shelterNo='" + shelterNo + '\'' +
                ", animalNo=" + animalNo +
                ", telNum='" + telNum + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", adoptReason='" + adoptReason + '\'' +
                ", Agreement='" + agreement + '\'' +
                ", whenSick='" + whenSick + '\'' +
                ", bigDuty='" + bigDuty + '\'' +
                ", wishDate=" + wishDate +
                ", subRead='" + subRead + '\'' +
                ", subDate=" + subDate +
                '}';
    }
}
