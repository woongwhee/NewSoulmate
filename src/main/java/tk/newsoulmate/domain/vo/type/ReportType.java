package tk.newsoulmate.domain.vo.type;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public enum ReportType {
    @SerializedName("board")
    BOARD("board",0),
    @SerializedName("reply")
    REPLY("reply",1),
    @SerializedName("notice")
    NOTICE("notice",2);
    public String typeName;
    public int typeNo;
    ReportType(String typeName, int typeNo) {
        this.typeName = typeName;
        this.typeNo = typeNo;
    }
    public static ReportType valueOfNo(int typeNo){
        return Arrays.asList(ReportType.values()).stream().filter(e->e.typeNo==typeNo).findAny().orElse(BOARD);
    }
    public static ReportType valueOfName(String typeName){
        return Arrays.asList(ReportType.values()).stream().filter(e->e.typeName.equals(typeName)).findAny().orElse(BOARD);
    }
}
