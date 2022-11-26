package tk.newsoulmate.web.common.service;

import tk.newsoulmate.domain.dao.*;
import tk.newsoulmate.domain.vo.*;

import java.sql.Connection;
import java.util.List;

import static tk.newsoulmate.web.common.JDBCTemplet.*;
public class CommonService {
    public List<Notice> selectThumbNail(int page) {
        Connection conn = getConnection();
        List<Notice> nList = new NoticeDao().selectThumbNail(conn, page);
        close();
        return nList;
    }

    public Notice selectDetail(long dno) {
        Connection conn = getConnection();
        Notice n = new NoticeDao().selectNotice(conn, dno);
        close();
        return n;
    }


    public List<Board> selectVolunteerThumbNail(int page) {
        Connection conn = getConnection();
        List<Board> vList = new BoardDao().selectVolunteerThumNail(conn, page);
        close();
        return vList;
    }

    public List<Board> selectAdoptReview(int i) {
        Connection conn = getConnection();
        List<Board> vList = new BoardDao().selectVolunteerThumNail(conn, i);
        close();
        return vList;
    }

    public int insertReport(Report r) {
        Connection conn=getConnection();
        int result=new ReportDao().insertReport(conn,r);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        commit();
        return result;
    }

    public int insertBoardReply(Reply r) {
        Connection conn=getConnection();
        int result=new ReplyDao().insertBoardReply(conn,r);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        commit();
        return result;
    }

    /**
     * 댓글 삭제용 메서드
     * @param rno 댓글의 rno
     * @param memberNo 로그인된 유저의 mno
     * @return
     */
    public int deleteReply(int rno, int memberNo) {
        Connection conn=getConnection();
        int result=new ReplyDao().deleteReply(conn,rno,memberNo);
        if(result>0){
            commit();
        }else{
            rollback();
        }
        commit();
        return result;
    }

    public int insertNoticeReply(Reply r,Attachment at) {
        Connection conn=getConnection();
        ReplyDao rd=new ReplyDao();
        int replyNo=rd.selectReplyNo(conn);

        r.setReplyNo(replyNo);

        if(at!=null){
            at.setReplyNo(replyNo);
            replyNo*=new AttachmentDao().insertReplyAttachment(at,conn);
        }
        replyNo*=rd.insertNoticeReply(conn,r);

            if(replyNo>0){
                commit();
            }else{
                rollback();
            }
        close();
        return replyNo;
    }
}
