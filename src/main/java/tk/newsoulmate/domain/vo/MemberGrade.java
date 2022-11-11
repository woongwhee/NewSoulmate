package tk.newsoulmate.domain.vo;

/**
 *
 */
public enum MemberGrade {
    USER("사용자",0),SHELTER_MANAGER("보호소관계자",1),SITE_MANAGER("사이트관리자",2);
    public String gradeName;
    public int gradeNumber;
    private MemberGrade(String greedName, int greedNumber){
        this.gradeNumber =greedNumber;
        this.gradeName =greedName;
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public String getGradeName() {
        return gradeName;
    }
}
