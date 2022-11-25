package tk.newsoulmate.domain.vo.response;

import tk.newsoulmate.domain.vo.Member;

public class ManageMemberResponse extends Member {
    private String shelterName;

    public ManageMemberResponse() {
        super();
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

}