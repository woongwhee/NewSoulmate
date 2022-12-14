
package tk.newsoulmate.domain.dto.response.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("reqNo")
    @Expose
    private Integer reqNo;
    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("resultMsg")
    @Expose
    private String resultMsg;

    public Integer getReqNo() {
        return reqNo;
    }

    public void setReqNo(Integer reqNo) {
        this.reqNo = reqNo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
