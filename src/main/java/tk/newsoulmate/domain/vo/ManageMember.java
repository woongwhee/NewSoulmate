package tk.newsoulmate.domain.vo;

import tk.newsoulmate.domain.vo.Member;

public class ManageMember extends Member {
    private String shelterName;

    public ManageMember() {
        super();
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

}