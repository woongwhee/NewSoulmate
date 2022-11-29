package tk.newsoulmate.domain.vo.type;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public enum ReportType {
    @SerializedName("board")
    BOARD("board","게시글",0),
    @SerializedName("reply")
    REPLY("reply","게시글댓글",1),
    @SerializedName("notice")
    NOTICE("notice","유기동물댓글",2);
    public String typeName;
    private String koreanName;
    public int typeNo;
    ReportType(String typeName,String koreanName, int typeNo) {
        this.typeName = typeName;
        this.koreanName=koreanName;
        this.typeNo = typeNo;
    }
    public static ReportType valueOfNo(int typeNo){
        return Arrays.asList(ReportType.values()).stream().filter(e->e.typeNo==typeNo).findAny().orElse(BOARD);
    }
    public static ReportType valueOfName(String typeName){
        return Arrays.asList(ReportType.values()).stream().filter(e->e.typeName.equals(typeName)).findAny().orElse(BOARD);
    }

    public String getKoreanName() {
        return koreanName;
    }
}
