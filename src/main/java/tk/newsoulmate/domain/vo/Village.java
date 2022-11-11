package tk.newsoulmate.domain.vo;

public class Village {
    private long cityNo;
    private long villageNo;
    private String villageName;

    public Village(long cityNo, long villageNo, String villageName) {
        this.cityNo = cityNo;
        this.villageNo = villageNo;
        this.villageName = villageName;
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

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
}
