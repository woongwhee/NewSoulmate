package tk.newsoulmate.web.inquire.service;

import tk.newsoulmate.domain.dao.AttachmentDao;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.CategoryDao;
import tk.newsoulmate.domain.vo.*;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;


public class InquireService {
    public int selectListCount(Member loginUser){
        Connection conn = getConnection();


        int listCount = new BoardDao().selectQnAListCount(conn, BoardType.QNA,loginUser);

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

        int boardNo = new BoardDao().insertBoard(b, conn);
        int result2 = 1;
        if(boardNo>0){
            at.setBoardNo(boardNo);
        }
        if(at != null){
            result2 = new AttachmentDao().insertAttachment(at, conn);
        }

        if(boardNo > 0 && result2 > 0){
            commit();
        } else{
            rollback(conn);
        }
        close();
        return boardNo*result2;
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

    public Attachment selectInquireAttachment(int boardNo){
        Connection conn = getConnection();
        Attachment at = new AttachmentDao().selectInquireAttachment(conn, boardNo);
        close();

        return at;
    }



























}
