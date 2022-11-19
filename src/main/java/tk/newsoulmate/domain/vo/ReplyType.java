package tk.newsoulmate.domain.vo;

import java.util.Arrays;

public enum ReplyType {
    NORMAL(0,"일반"),PICTURE(1,"사진");
    public int typeNo;
    public String typeName;

    ReplyType(int typeNo, String typeName) {
        this.typeNo = typeNo;
        this.typeName = typeName;
    }
    public static ReplyType valueOfNo(int typeNo){
        return Arrays.stream(ReplyType.values()).filter(e->e.typeNo ==typeNo).findAny().orElse(NORMAL);
    }
    public static ReplyType valueOfName(String typeName){
        return Arrays.stream(ReplyType.values()).filter(e->e.typeName ==typeName).findAny().orElse(NORMAL);
    }
}
