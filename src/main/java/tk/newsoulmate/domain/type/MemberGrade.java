package tk.newsoulmate.domain.type;

import java.util.Arrays;

/**
 *
 */
public enum MemberGrade {
    USER("일반",0),SHELTER_MANAGER("보호소관계자",1),SITE_MANAGER("사이트관리자",2);
    public String gradeName;
    public int gradeNumber;

    private MemberGrade(String greedName, int greedNumber){
        this.gradeNumber =greedNumber;
        this.gradeName =greedName;
    }
    public boolean isUSER(){
        if(this==null){return false;}
        return this==USER;
    }
    public boolean isSHELTER_MANAGER(){
        if(this==null){return false;}
        return this==SHELTER_MANAGER;
    }public boolean isSITE_MANAGER(){
        if(this==null){return false;}
        return this==SITE_MANAGER;
    }
    public static MemberGrade valueOfNumber(int gradeNumber){
        MemberGrade memberGrades [] = MemberGrade.values();
        return Arrays.asList(memberGrades).stream().filter(e->e.gradeNumber==gradeNumber).findAny().orElse(USER);
    }

    public static MemberGrade valueOfName(String gradeName){
        MemberGrade memberGrades [] = MemberGrade.values();
        return Arrays.asList(memberGrades).stream().filter(e->e.gradeName.equals(gradeName)).findAny().orElse(USER);
    }
}
