package tk.newsoulmate.domain.vo;

import java.time.Clock;

public class City {
    private long cityNo;
    private String cityName;

    public City(long cityNo, String cityName) {
        this.cityNo = cityNo;
        this.cityName = cityName;
    }

    public long getCityNo() {
        return cityNo;
    }

    public void setCityNo(long cityNo) {
        this.cityNo = cityNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityNo=" + cityNo +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
