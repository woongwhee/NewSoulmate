package tk.newsoulmate.web.adopt.sevice;

import tk.newsoulmate.domain.dao.*;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class AdoptService {

    public ArrayList<Board> selectAdoptReviewList(PageInfo pi){

        Connection conn = getConnection();

        ArrayList<Board> list = new BoardDao().selectList(conn,BoardType.ADOPT,pi);

        close();

        return list;
    }

    public int selectListCount() {

        Connection conn = getConnection();

        int listCount = new BoardDao().boardListCount(conn, BoardType.ADOPT);

        close();

        return listCount;

    }

    public int increaseReadCount(int boardNo) {

        Connection conn = getConnection();

        int result = new BoardDao().increaseCount(conn, boardNo);

        if(result > 0) {
            commit();
        }else {
            rollback(conn);
        }
        close();

        return result;

    }

    public Board selectAdoptReviewDetail(int boardNo) {
        Connection conn = getConnection();
        Board b = new BoardDao().selectAdoptReviewDetail(conn, boardNo);
        close();

        return b;
    }

    public int insertReply(Reply r) {
        Connection conn = getConnection();
        int result = new ReplyDao().insertBoardReply(conn, r);

        if (result > 0) {
            commit();
        } else {
            rollback(conn);
        }
        return result;
    }

    public ArrayList<Reply> selectReplyList(int boardNo){

        Connection conn = getConnection();
        ArrayList<Reply> list = new ReplyDao().selectReplyList(conn, boardNo);
        close();
        return list;
    }

    /**
     * 입양신청을 db에 저장하단. 전달한다.
     * @param sb
     * @return 결과를 0 , 1 로반환한다.
     */
    public int insertSubscription(Subscription sb){
        Connection conn = getConnection();
        int result = new SubscriptionDao().insertSubscription(conn,sb);

        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();

        return result;
    }

    public int checkAnimal(String animalNo){
        Connection conn = getConnection();
        int checkAnimal = new NoticeDao().checkAnimal(conn,animalNo);
        close();
        return checkAnimal;
    }

    public long shelterNoByName(String animalNo){
        Connection conn = JDBCTemplet.getConnection();
        long shelterNo = new ShelterDao().shelterNoByName(conn,animalNo);
        JDBCTemplet.close();

        return shelterNo;
    }
    public int selectBoardNo(){
        Connection conn=getConnection();
        int boardNo=new BoardDao().selectBoardNo(conn);
        close();
        return boardNo;
    }


    public int insertAttachment(Attachment at) {
        Connection conn = getConnection();
        int result = new AttachmentDao().insertBoardAttachment(at,conn);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
        return result;

    }

    public int insertBoard(Board board) {
        Connection conn = getConnection();
        int result = new BoardDao().insertReviewBoard(board,conn);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
        return result;

    }
}


