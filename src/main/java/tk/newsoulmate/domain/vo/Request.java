package tk.newsoulmate.domain.vo;
import tk.newsoulmate.domain.vo.type.Species;
import tk.newsoulmate.web.common.APIKeys;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Request {
    private String serviceKey;

    private Date bgndate;///*유기날짜(검색 시작일) (YYYYMMDD)*/
    private Date enddate; //*유기날짜(검색 종료일) (YYYYMMDD)*/
    private Species species;/*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900)*/
    private Long breedNo;/*품종코드 (품종 조회 OPEN API 참조)*/
    private Long cityNo;/*시도코드 (시도 조회 OPEN API 참조)*/
    private Long villageNo;/*시군구코드 (시군구 조회 OPEN API 참조)*/
    private Long shelterNo; /*보호소번호 (보호소 조회 OPEN API 참조)*/
    private String state; /*상태(전체 : null(빈값), 공고중 : notice, 보호중 : protect)*/
    private String neuter; /*상태 (전체 : null(빈값), 예 : Y, 아니오 : N, 미상 : U)*/
    private Integer pageNo;/*페이지 번호 (기본값 : 1)*/
    private Integer numberOfRows;/*페이지당 보여줄 개수 (1,000 이하), 기본값 : 10*/
    private String _type;
    private static SimpleDateFormat urlDate=new SimpleDateFormat("yyyyMMdd");
    public Request() {
        this.serviceKey= APIKeys.NoticeKey;
        this._type = "JSON";
        this.pageNo=1;
        this.numberOfRows=1;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Date getBgndate() {
        return bgndate;
    }

    public void setBgndate(Date bgndate) {
        this.bgndate = bgndate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Long getBreedNo() {
        return breedNo;
    }

    public void setBreedNo(Long breedNo) {
        this.breedNo = breedNo;
    }

    public Long getCityNo() {
        return cityNo;
    }

    public void setCityNo(Long cityNo) {
        this.cityNo = cityNo;
    }

    public Long getVillageNo() {
        return villageNo;
    }

    public void setVillageNo(Long villageNo) {
        this.villageNo = villageNo;
    }

    public Long getShelterNo() {
        return shelterNo;
    }

    public void setShelterNo(Long shelterNo) {
        this.shelterNo = shelterNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeuter() {
        return neuter;
    }

    public void setNeuter(String neuter) {
        this.neuter = neuter;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public static SimpleDateFormat getUrlDate() {
        return urlDate;
    }

    /**
     * 유효한 유기동물리스트를 위한 세팅
     * @param request
     */
    public void setValid(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -13);
        this.setBgndate(cal.getTime());
        this.setState("protect");
    }
    public static void setUrlDate(SimpleDateFormat urlDate) {
        Request.urlDate = urlDate;
    }

    public URL toUrl() {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        URL url;
        try {
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") +"="+
                    serviceKey); /*Service Key*/
            if (bgndate != null) {
                urlBuilder.append("&" + URLEncoder.encode("bgnde", "UTF-8") + "=" +
                        URLEncoder.encode(urlDate.format(bgndate), "UTF-8"));
            }
            if (enddate != null) {
                urlBuilder.append("&" + URLEncoder.encode("endde", "UTF-8") + "=" +
                        URLEncoder.encode(urlDate.format(enddate), "UTF-8"));
            }
            if (species != null) {
                urlBuilder.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=" +
                        URLEncoder.encode(String.valueOf(species.speciesNo), "UTF-8"));
            }
            if (breedNo != null) {
                urlBuilder.append("&" + URLEncoder.encode("kind", "UTF-8") + "=" +
                        URLEncoder.encode(String.valueOf(breedNo), "UTF-8"));
            }

            if(cityNo!=null){
                urlBuilder.append("&" + URLEncoder.encode("upr_cd", "UTF-8") + "=" +
                        URLEncoder.encode(String.valueOf(cityNo), "UTF-8"));
            }
            if(villageNo!=null){
                urlBuilder.append("&" + URLEncoder.encode("org_cd", "UTF-8") + "=" +
                        URLEncoder.encode(String.valueOf(villageNo), "UTF-8"));
            }
            if(shelterNo!=null){
                urlBuilder.append("&" + URLEncoder.encode("care_reg_no", "UTF-8") + "="
                        + URLEncoder.encode(String.valueOf(shelterNo), "UTF-8"));
            }
            if(state!=null){
                urlBuilder.append("&" + URLEncoder.encode("state", "UTF-8") + "=" +
                        URLEncoder.encode(state, "UTF-8"));
            }
            if(neuter!=null){
                urlBuilder.append("&" + URLEncoder.encode("neuter_yn", "UTF-8") + "=" +
                        URLEncoder.encode(neuter, "UTF-8"));
            }
            if(pageNo!=null) {
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" +
                        URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
            }
            if(numberOfRows!=null){
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" +
                    URLEncoder.encode(String.valueOf(numberOfRows), "UTF-8"));}
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            url=new URL(urlBuilder.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Request{");
        sb.append("serviceKey='").append(serviceKey).append('\'');
        sb.append(", bgndate=").append(bgndate);
        sb.append(", enddate=").append(enddate);
        sb.append(", species=").append(species);
        sb.append(", breedNo=").append(breedNo);
        sb.append(", cityNo=").append(cityNo);
        sb.append(", villageNo=").append(villageNo);
        sb.append(", shelterNo=").append(shelterNo);
        sb.append(", state='").append(state).append('\'');
        sb.append(", neuter='").append(neuter).append('\'');
        sb.append(", pageNo=").append(pageNo);
        sb.append(", numberOfRows=").append(numberOfRows);
        sb.append(", _type='").append(_type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
