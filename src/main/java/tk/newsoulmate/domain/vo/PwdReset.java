package tk.newsoulmate.domain.vo;

public class PwdReset {

    private String memberId;
    private String password;
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
