package tk.newsoulmate.domain.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notice{
    @SerializedName("desertionNo")
    @Expose
    private long desertionNo;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("happenDt")
    @Expose
    private String happenDt;
    @SerializedName("happenPlace")
    @Expose
    private String happenPlace;
    @SerializedName("kindCd")
    @Expose
    private String kindCd;
    @SerializedName("colorCd")
    @Expose
    private String colorCd;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("noticeNo")
    @Expose
    private String noticeNo;
    @SerializedName("noticeSdt")
    @Expose
    private String noticeSdt;
    @SerializedName("noticeEdt")
    @Expose
    private String noticeEdt;
    @SerializedName("popfile")
    @Expose
    private String popfile;
    @SerializedName("processState")
    @Expose
    private String processState;
    @SerializedName("sexCd")
    @Expose
    private String sexCd;
    @SerializedName("neuterYn")
    @Expose
    private String neuterYn;
    @SerializedName("specialMark")
    @Expose
    private String specialMark;
    @SerializedName("careNm")
    @Expose
    private String careNm;
    @SerializedName("careTel")
    @Expose
    private String careTel;
    @SerializedName("careAddr")
    @Expose
    private String careAddr;
    @SerializedName("orgNm")
    @Expose
    private String orgNm;
    @SerializedName("chargeNm")
    @Expose
    private String chargeNm;
    @SerializedName("officetel")
    @Expose
    private String officetel;
    public Notice() {
    }

    public Notice(long desertionNo, String noticeNo, String filename, String happenDt, String happenPlace, String kindCd, String colorCd, String age, String weight, String noticeSdt, String noticeEdt, String popfile, String processState, String sexCd, String neuterYn, String specialMark, String careNm, String careTel, String careAddr, String orgNm, String chargeNm, String officetel) {
        this.desertionNo = desertionNo;
        this.noticeNo = noticeNo;
        this.filename = filename;
        this.happenDt = happenDt;
        this.happenPlace = happenPlace;
        this.kindCd = kindCd;
        this.colorCd = colorCd;
        this.age = age;
        this.weight = weight;
        this.noticeSdt = noticeSdt;
        this.noticeEdt = noticeEdt;
        this.popfile = popfile;
        this.processState = processState;
        this.sexCd = sexCd;
        this.neuterYn = neuterYn;
        this.specialMark = specialMark;
        this.careNm = careNm;
        this.careTel = careTel;
        this.careAddr = careAddr;
        this.orgNm = orgNm;
        this.chargeNm = chargeNm;
        this.officetel = officetel;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public long getDesertionNo() {
        return desertionNo;
    }

    public void setDesertionNo(long desertionNo) {
        this.desertionNo = desertionNo;
    }

    public String getHappenDt() {
        return happenDt;
    }

    public void setHappenDt(String happenDt) {
        this.happenDt = happenDt;
    }

    public String getNoticeSdt() {
        return noticeSdt;
    }

    public void setNoticeSdt(String noticeSdt) {
        this.noticeSdt = noticeSdt;
    }

    public String getNoticeEdt() {
        return noticeEdt;
    }

    public void setNoticeEdt(String noticeEdt) {
        this.noticeEdt = noticeEdt;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }



    public String getHappenPlace() {
        return happenPlace;
    }

    public void setHappenPlace(String happenPlace) {
        this.happenPlace = happenPlace;
    }

    public String getKindCd() {
        return kindCd;
    }

    public void setKindCd(String kindCd) {
        this.kindCd = kindCd;
    }

    public String getColorCd() {
        return colorCd;
    }

    public void setColor(String colorCd) {
        this.colorCd = colorCd;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public String getPopfile() {
        return popfile;
    }

    public void setPopfile(String popfile) {
        this.popfile = popfile;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }


    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public String getNeuterYn() {
        return neuterYn;
    }

    public void setNeuterYn(String neuterYn) {
        this.neuterYn = neuterYn;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    public String getCareNm() {
        return careNm;
    }

    public void setCareNm(String careNm) {
        this.careNm = careNm;
    }

    public String getCareTel() {
        return careTel;
    }

    public void setCareTel(String careTel) {
        this.careTel = careTel;
    }

    public String getCareAddr() {
        return careAddr;
    }

    public void setCareAddr(String careAddr) {
        this.careAddr = careAddr;
    }

//    public String getOrgNm() {
//        return orgNm;
//    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getChargeNm() {
        return chargeNm;
    }

    public void setChargeNm(String chargeNm) {
        this.chargeNm = chargeNm;
    }

    public String getOfficetel() {
        return officetel;
    }

    public void setOfficetel(String officetel) {
        this.officetel = officetel;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "desertionNo=" + desertionNo +
                ", noticeNo='" + noticeNo + '\'' +
                ", filename='" + filename + '\'' +
                ", happenDt=" + happenDt +
                ", happenPlace='" + happenPlace + '\'' +
                ", kindCd='" + kindCd + '\'' +
                ", colorCd='" + colorCd + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ", noticeSdt=" + noticeSdt +
                ", noticeEdt=" + noticeEdt +
                ", popfile='" + popfile + '\'' +
                ", processState='" + processState + '\'' +
                ", sexCd=" + sexCd +
                ", neuterYn=" + neuterYn +
                ", specialMark='" + specialMark + '\'' +
                ", careNm='" + careNm + '\'' +
                ", careTel='" + careTel + '\'' +
                ", careAddr='" + careAddr + '\'' +
                ", orgNm='" + orgNm + '\'' +
                ", chargeNm='" + chargeNm + '\'' +
                ", officetel='" + officetel + '\'' +
                '}';
    }
}
