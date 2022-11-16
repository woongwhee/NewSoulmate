package tk.newsoulmate.web.adopt.sevice;

import tk.newsoulmate.domain.dao.AdoptDao;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class AdoptService {

    public ArrayList<Board> selectAdoptReviewList(PageInfo pi){

        Connection conn = getConnection();

        ArrayList<Board> list = new BoardDao().selectAdoptReviewList(conn,pi);

        close();

        return list;
    }

    public int adoptReviewListCount() {

        Connection conn = getConnection();

        int listCount = new BoardDao().adoptReviewListCount(conn);

        close();

        return listCount;

    }

    public int readCount(int boardNo) {

        Connection conn = getConnection();

        int result = new BoardDao().readCount(conn, boardNo);

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

        int result = new BoardDao().insertReply(conn, r);

        if (result > 0) {
            commit();
        } else {
            rollback(conn);
        }
        return result;
    }

    public ArrayList<Reply> selectReplyList(int boardNo){

        Connection conn = getConnection();
        ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);

        close();

        return list;
    }
    public int adoptApplyInsert(Subscription sb){
        Connection conn = getConnection();
        int result = new AdoptDao().adoptApplyInsert(conn,sb);

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
        int checkAnimal = new AdoptDao().checkAnimal(conn,animalNo);

        return checkAnimal;
    }

    public long shelterNoByName(String animalNo){
        Connection conn = JDBCTemplet.getConnection();

        long shelterNo = new AdoptDao().shelterNoByName(conn,animalNo);

        JDBCTemplet.close();

        return shelterNo;
    }


}


