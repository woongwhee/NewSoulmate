package tk.newsoulmate.web.volunteer.service;

import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.Board;

import java.sql.Connection;

import static tk.newsoulmate.web.common.JDBCTemplet.*;
import static tk.newsoulmate.web.common.JDBCTemplet.rollback;

public class VolunteerService {
    public int selectBoardNo(){
        Connection conn=getConnection();
        int boardNo=new BoardDao().selectBoardNo(conn);
        close();
        return boardNo;
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
