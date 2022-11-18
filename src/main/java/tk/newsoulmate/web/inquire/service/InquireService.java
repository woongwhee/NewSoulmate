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
        int boardNo = new BoardDao().selectBoardNo(conn);
        if(boardNo==0){
            close();
            return boardNo;
        }

        if(at!=null) b.setFileCount(1);
        
        b.setBoardNo(boardNo);
        int result1 =new BoardDao().insertQnABoard(b, conn);
        int result2 = 1;
        if(at != null){
            at.setBoardNo(boardNo);
            result2 = new AttachmentDao().insertBoardAttachment(at, conn);
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
        Attachment at = new AttachmentDao().selectBoardAttachment(conn, boardNo);
        close();

        return at;
    }

    public int updateInquireBoard(Board b, Attachment at){
        Connection conn = getConnection();

        int result1 = new BoardDao().updateInquireBoard(conn, b);

        int result2 = 1;

        if(at != null){
            if(at.getFileNo() != 0){
                result2 = new AttachmentDao().updateInquireAttachment(at, conn);
            } else {
//                result2 = new AttachmentDao().insertInquireNewAttachment(at, conn);
            }
        }

        if(result1 > 0 && result2 > 0){
            commit();
        } else {
            rollback(conn);
        }
        close();
        return result1 * result2;

    }

    public int deleteInquireBoard(int boardNo){
        Connection conn = getConnection();

        int result = new BoardDao().deleteInquireBoard(boardNo, conn);

        new AttachmentDao().deleteInquireAttachment(boardNo, conn);

        if(result > 0){
            commit();
        } else {
            rollback(conn);
        }
        close();

        return result;

    }




























}
