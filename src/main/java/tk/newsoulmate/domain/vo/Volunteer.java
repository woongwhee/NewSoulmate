package tk.newsoulmate.domain.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Volunteer {

    private  String gender;
    private  String volRead;
    private  String shelterName;
    private String memberId;
    private int volunteerNo;
    private long shelterNo;
    private int memberNo;

    private String memberName;
    private Date startDate;
    private Date applyDate;
    private String name;
    private String telNumber;

    public Volunteer() {
        super();
    }

    public Volunteer(long shelterNo, int memberNo, Date startDate, String name, String telNumber) {
        this.shelterNo = shelterNo;
        this.memberNo = memberNo;
        this.startDate = startDate;
        this.name = name;
        this.telNumber = telNumber;
    }

    public Volunteer(int volunteerNo, long shelterNo, int memberNo, Date startDate, Date applyDate, String name, String telNumber) {
        this.volunteerNo = volunteerNo;
        this.shelterNo = shelterNo;
        this.memberNo = memberNo;
        this.startDate = startDate;
        this.applyDate = applyDate;
        this.name = name;
        this.telNumber = telNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getVolunteerNo() {
        return volunteerNo;
    }

    public String getVolRead() {
        return volRead;
    }

    public void setVolRead(String volRead) {
        this.volRead = volRead;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setVolunteerNo(int volunteerNo) {
        this.volunteerNo = volunteerNo;
    }

    public long getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(long shelterNo) {
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
