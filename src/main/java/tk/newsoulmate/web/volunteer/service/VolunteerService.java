package tk.newsoulmate.web.volunteer.service;

import tk.newsoulmate.domain.dao.*;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class VolunteerService {

    public List<Attachment> selectVolunteerReviewList(int page, int pageLimit){
        Connection conn = getConnection();
        int start= PageInfo.StartNum(page,pageLimit);
        int end=PageInfo.EndNum(page,pageLimit);
        List<Attachment> list = new AttachmentDao().selectThumbNailList(conn, BoardType.VOLUNTEER,start,end);
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

    public Board selectReviewDetail(int boardNo) {
        Connection conn = getConnection();
        Board b = new BoardDao().selectReviewDetail(conn, boardNo);
        close();

        return b;
    }

    public ArrayList<Reply> selectReplyList(int boardNo){

        Connection conn = getConnection();
        ArrayList<Reply> list = new ReplyDao().selectReplyList(conn, boardNo);
        close();
        return list;
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

    public List<Category> selectCategoryList(){
        Connection conn=getConnection();
        List<Category> cList=new CategoryDao().selectCategoryList(conn, BoardType.REPORT);
        close();
        return cList;
    }

    public int deleteBoard(int bno) {
        Connection conn = getConnection();
        int result=new AttachmentDao().deleteBoardAttachment(bno,conn);
        result *= new BoardDao().deleteBoard(conn,bno);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
        return result;

    }

    public int updateBoard(Board board) {
        Connection conn= getConnection();
        int result=new BoardDao().updateReviewBoard(conn,board);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();

        return result;
    }
    public List<Attachment> selectAttachmentList(int bno) {
        Connection conn=getConnection();
        List<Attachment> aList=new AttachmentDao().selectBoardAttachment(conn,bno);
        close();
        return aList;
    }

    public int deleteAttachment(int fileNo) {
        Connection conn=getConnection();
        int result= new AttachmentDao().deleteAttachment(fileNo,conn);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        close();
        return result;
    }

    public int deleteAttachmentList(List<Attachment> dList) {
        Connection conn=getConnection();
        int result= new AttachmentDao().deleteAttachmentList(dList,conn);
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
