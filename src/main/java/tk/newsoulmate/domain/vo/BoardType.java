package tk.newsoulmate.domain.vo;

import java.util.Arrays;

public enum BoardType {
    VOLUNTEER(1,"봉사후기"),ADOPT(2,"입양후기"),REPORT(3,"신고"),QNA(4,"문의"),FaQ(5,"FaQ");
    public int typeNo;
    public String boardName;
    BoardType(int typeNo,String boardName){
        this.typeNo=typeNo;
        this.boardName=boardName;
    }

    public static BoardType valueOfNo(int typeNo){
        BoardType boardTypes [] = BoardType.values();
        return Arrays.asList(boardTypes).stream().filter(e->e.typeNo==typeNo).findAny().orElse(VOLUNTEER);
    }

    public static BoardType valueOfName(String boardName){
        BoardType boardTypes [] = BoardType.values();
        return Arrays.asList(boardTypes).stream().filter(e->e.boardName==boardName).findAny().orElse(VOLUNTEER);
    }

    }
