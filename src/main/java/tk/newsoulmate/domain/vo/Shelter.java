package tk.newsoulmate.domain.vo;

public class Shelter {
    private long shelterNo;
    private String shelterName;
    private String shelterTel;
    private String shelterEmail;
    private String shelterAddress;
    private String shelterLandline;
    private String companyNum;
    private String transferAccount;
    private long cityNo;
    private long villageNo;
    public Shelter(){
        super();
    }
    public Shelter(long shelterNo, String shelterName, String shelterTel, String shelterEmail, String shelterAddress, String shelterLandline, String companyNum, String transferAccount, long cityNo, long villageNo) {
        super();
        this.shelterNo = shelterNo;
        this.shelterName = shelterName;
        this.shelterTel = shelterTel;
        this.shelterEmail = shelterEmail;
        this.shelterAddress = shelterAddress;
        this.shelterLandline = shelterLandline;
        this.companyNum = companyNum;
        this.transferAccount = transferAccount;
        this.cityNo = cityNo;
        this.villageNo = villageNo;
    }

    public Shelter(long shelterNo, String shelterName, String shelterAddress, String shelterLandline, long cityNo, long villageNo) {
        super();
        this.shelterNo = shelterNo;
        this.shelterName = shelterName;
        this.shelterAddress = shelterAddress;
        this.shelterLandline = shelterLandline;
        this.cityNo = cityNo;
        this.villageNo = villageNo;
    }
    public Shelter(String shelterName,String shelterAddress, String shelterLandline){
        this.shelterAddress = shelterAddress;
        this.shelterName = shelterName;
        this.shelterLandline = shelterLandline;
    }

    public Shelter(String shelterName,String shelterAddress, String shelterLandline,long villageNo, long shelterNo){
        this.shelterName=shelterName;
        this.shelterAddress = shelterAddress;
        this.shelterLandline=shelterLandline;
        this.villageNo =villageNo;
        this.shelterNo=shelterNo;
    }

    public Shelter(long shelterNo, String shelterName) {
        this.shelterNo = shelterNo;
        this.shelterName = shelterName;
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

    public String getShelterTel() {
        return shelterTel;
    }

    public void setShelterTel(String shelterTel) {
        this.shelterTel = shelterTel;
    }

    public String getShelterEmail() {
        return shelterEmail;
    }

    public void setShelterEmail(String shelterEmail) {
        this.shelterEmail = shelterEmail;
    }

    public String getShelterAddress() {
        return shelterAddress;
    }

    public void setShelterAddress(String shelterAddress) {
        this.shelterAddress = shelterAddress;
    }

    public String getShelterLandline() {
        return shelterLandline;
    }

    public void setShelterLandline(String shelterLandline) {
        this.shelterLandline = shelterLandline;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public long getCityNo() {
        return cityNo;
    }

    public void setCityNo(long cityNo) {
        this.cityNo = cityNo;
    }

    public long getVillageNo() {
        return villageNo;
    }

    public void setVillageNo(long villageNo) {
        this.villageNo = villageNo;
    }

    @Override
    public String toString() {
        return "{" +
                "shelterNo:" + shelterNo +
                ", shelterName:'" + shelterName + '\'' +
                ", shelterTelL:'" + shelterTel + '\'' +
                ", shelterEmail:'" + shelterEmail + '\'' +
                ", shelterAddress:'" + shelterAddress + '\'' +
                ", shelterLandline:'" + shelterLandline + '\'' +
                ", companyNum:'" + companyNum + '\'' +
                ", transferAccount:'" + transferAccount + '\'' +
                ", cityNo:" + cityNo +
                ", villageNo:" + villageNo +
                '}';
    }
}
