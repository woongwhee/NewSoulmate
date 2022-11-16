package tk.newsoulmate.web.inquire.service;

import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.CategoryDao;
import tk.newsoulmate.domain.vo.*;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;


public class InquireService {
    public int selectListCount(Member loginUser){
        Connection conn = getConnection();

        String categoryName = "문의";

        int listCount = new BoardDao().selectQnAListCount(conn, categoryName,loginUser);

        close();

        return listCount;
    }
    public ArrayList<Board> selectQnAList(PageInfo pi, Member loginUser){
        Connection conn = getConnection();

        ArrayList<Board> list = new BoardDao().selectQnAList(conn, pi,loginUser);

        close();

        return list;
    }

    public ArrayList<Category> selectCategoryList(){
        Connection conn = getConnection();

        ArrayList<Category> list = new CategoryDao().selectCategoryList(conn);

        close();

        return list;
    }

    public int insertInquire(Board b, Attachment at){
        Connection conn = getConnection();

        int result1 = new BoardDao().insertBoard(b, conn);

        int result2 = 1;

        if(at != null){
            result2 = new BoardDao().insertAttachment(at, conn);
        }

        if(result1 > 0 && result2 > 0){
            commit();
        } else{
            rollback(conn);
        }
        close();
        return result1*result2;
    }

    public int increaseCount(int boardNo) {
        Connection conn = getConnection();

        int result = new BoardDao().increaseCount(conn, boardNo);

        if(result > 0){
            commit();
        } else {
            rollback(conn);
        }
        close();

        return result;
    }

    public Board selectInquireBoard(int boardNo,Member loginUser){
        Connection conn = getConnection();

        Board b = new BoardDao().selectInquireBoard(conn, boardNo,loginUser);

        close();

        return b;
    }

}
