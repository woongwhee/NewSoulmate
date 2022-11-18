package tk.newsoulmate.domain.vo;

import java.util.Arrays;

public enum ReplyType {
    NORMAL(0,"일반"),PICTURE(1,"사진");
    public int TypeNo;
    public String TypeName;

    ReplyType(int typeNo, String typeName) {
        TypeNo = typeNo;
        TypeName = typeName;
    }
    public static ReplyType valueOfNo(int typeNo){
        return Arrays.stream(ReplyType.values()).filter(e->e.TypeNo==typeNo).findAny().orElse(NORMAL);
    }
    public static ReplyType valueOfName(String typeName){
        return Arrays.stream(ReplyType.values()).filter(e->e.TypeName==typeName).findAny().orElse(NORMAL);
    }
}
