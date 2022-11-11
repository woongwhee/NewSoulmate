package tk.newsoulmate.domain.vo;

import java.sql.Date;

public class Volunteer {
    private int volunteerNo;
    private int shelterNo;
    private int memberNo;
    private Date startDate;
    private Date applyDate;
    private String name;
    private String telNumber;

    public Volunteer() {
        super();
    }

    public Volunteer(int shelterNo, int memberNo, Date startDate, String name, String telNumber) {
        this.shelterNo = shelterNo;
        this.memberNo = memberNo;
        this.startDate = startDate;
        this.name = name;
        this.telNumber = telNumber;
    }

    public Volunteer(int volunteerNo, int shelterNo, int memberNo, Date startDate, Date applyDate, String name, String telNumber) {
        this.volunteerNo = volunteerNo;
        this.shelterNo = shelterNo;
        this.memberNo = memberNo;
        this.startDate = startDate;
        this.applyDate = applyDate;
        this.name = name;
        this.telNumber = telNumber;
    }

    public int getVolunteerNo() {
        return volunteerNo;
    }

    public void setVolunteerNo(int volunteerNo) {
        this.volunteerNo = volunteerNo;
    }

    public int getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(int shelterNo) {
        this.shelterNo = shelterNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
