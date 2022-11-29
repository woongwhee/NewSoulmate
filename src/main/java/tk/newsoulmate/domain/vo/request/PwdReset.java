package tk.newsoulmate.domain.vo.request;

import com.google.gson.annotations.SerializedName;

public class PwdReset {
    @SerializedName("memberId")
    private String memberId;
    @SerializedName("password")
    private String password;
    @SerializedName("passwordConfirm")
    private String passwordConfirm;

    public PwdReset(String memberId, String password, String passwordConfirm) {
        this.memberId = memberId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

}
