package tk.newsoulmate.web.common.service;

import tk.newsoulmate.domain.dao.*;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.type.BoardType;

import java.sql.Connection;
import java.util.List;

import static tk.newsoulmate.web.common.JDBCTemplet.*;
public class CommonService {
    public List<Notice> selectNoticeThumbNail(int page) {
        Connection conn = getConnection();
        List<Notice> nList = new NoticeDao().selectThumbNail(conn, page);
        close();
        return nList;
    }


    public List<Board> selectReviewList(int page,BoardType bt) {
        Connection conn = getConnection();
        List<Board> vList = new BoardDao().selectReviewList(conn, page,bt);
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

    public List<Attachment> selectBoardThumbNail(int page,int pageLimit,BoardType boardType){
        Connection conn = getConnection();
        int start=PageInfo.StartNum(page,pageLimit);
        int end=PageInfo.EndNum(page,pageLimit);
        List<Attachment> list = new AttachmentDao().selectThumbNailList(conn, boardType,start,end);
        close();
        return list;
    }


}
