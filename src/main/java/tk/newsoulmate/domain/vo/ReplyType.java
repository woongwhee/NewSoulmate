package tk.newsoulmate.domain.vo;

public enum ReplyType {
    NORMAL(0,"일반"),PICTURE(1,"사진");
    public int TypeNo;
    public String TypeName;

    ReplyType(int typeNo, String typeName) {
        TypeNo = typeNo;
        TypeName = typeName;
    }

    public int getTypeNo() {
        return TypeNo;
    }

    public String getTypeName() {
        return TypeName;
    }
}
