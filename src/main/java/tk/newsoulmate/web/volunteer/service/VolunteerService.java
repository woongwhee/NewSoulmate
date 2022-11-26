package tk.newsoulmate.web.volunteer.service;

import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.dao.VolunteerDao;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

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

    public ArrayList<Shelter> volAbleShelter() {
        Connection conn = JDBCTemplet.getConnection();
        ArrayList<Shelter> sList = new ShelterDao().volAbleShelter(conn);
        JDBCTemplet.close();

        return sList;
    }

    public int volApplyInset(Volunteer vol) {
        Connection conn = JDBCTemplet.getConnection();
        int result = new VolunteerDao().volApplyInsert(conn,vol);
        JDBCTemplet.close();

        if(result>0){
            JDBCTemplet.commit();
        }else{
            JDBCTemplet.rollback();
        }
        return result;
    }
}
