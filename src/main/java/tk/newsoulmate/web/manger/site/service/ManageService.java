package tk.newsoulmate.web.manger.site.service;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

import java.sql.Connection;
import java.util.ArrayList;

import tk.newsoulmate.domain.dao.AttachmentDao;
import tk.newsoulmate.domain.dao.GradeUpDao;
import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.dao.NoticeDao;
import tk.newsoulmate.domain.dao.SubscriptionDao;
import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.domain.vo.type.MemberGrade;

public class ManageService {

    private MemberDao memberDao = new MemberDao();


    public ArrayList<Member> selectMemberList(PageInfo pageInfo, String filter) {
        Connection conn = getConnection();
        ArrayList<Member> mList;
        if (filter.equals("ALL")) {
            mList = memberDao.selectMemberList(conn, pageInfo);
        } else {
            mList = memberDao.selectMemberListByFilter(conn, pageInfo, MemberGrade.valueOf(filter));
        }
        close();
        return mList;
    }

    public int countMember(String filter) {
        Connection conn = getConnection();
        int count = 0;
        if (filter.equals("ALL")) {
            count = memberDao.count(conn);
        } else {
            count = memberDao.countByFilter(conn, MemberGrade.valueOf(filter));
        }
        close();
        return count;
    }




/*    manageMemberList 페이징바 처리 관련

        public int selectMemberListCount(){
        Connection conn = getConnection();

        int listCount = new MemberDao().selectMemberListCount(conn);

        close();
        return listCount;
    }


    public ArrayList<ManageMember> selectMemberList(PageInfo pi) {
        Connection conn = getConnection();
        ArrayList<ManageMember> mList = memberDao.selectMemberList(conn,pi);
        close();
        return mList;
    }
    */











    public ArrayList<Member> selectManageMember() {
        Connection conn = getConnection();
        ArrayList<Member> mList = memberDao.selectManageMember(conn);
        close();
        return mList;
    }

    public ArrayList<GradeUp> selectGradeUp() {
        Connection conn = getConnection();
        ArrayList<GradeUp> gList = new GradeUpDao().selectAllGrade(conn);

        AttachmentDao at = new AttachmentDao();
        //new AttachmentDao().selectGradeUpAttachment(conn,gList);
        at.selectGradeUpAttachment(conn,gList);
        close();
        return gList;
    }


    public int selectAdoptApplyListCount(){
        Connection conn = getConnection();

        int listCount = new SubscriptionDao().selectAdoptApplyListCount(conn);

        close();
        return listCount;
    }
    public ArrayList<Subscription> selectAdoptApplyList(PageInfo pi){
        Connection conn = getConnection();

        ArrayList<Subscription> list = new SubscriptionDao().selectAdoptApplyList(conn, pi);
        close();
        return list;
    }

    public Subscription selectAdoptApplyDetail(int subNo){
        Connection conn = getConnection();

        Subscription s = new SubscriptionDao().selectAdoptApplyDetail(conn,subNo);
        close();

        return s;
    }


    public Subscription selectAdoptApplyListCheck(int subNo){
        Connection conn = getConnection();

        Subscription s = new SubscriptionDao().selectAdoptApplyDetail(conn,subNo);

        close();

        return s;
    }

    public int changeStatus(String[] memberNo) {
        Connection conn = getConnection();
        int result1 = new GradeUpDao().changeGrade(conn,memberNo);
        int result2 = new MemberDao().changeGrade(conn,memberNo);

        if(result1 == memberNo.length && result2 == memberNo.length){
            commit();
        }else{
            rollback();
        }
        close();
        return (result1+result2)/2;
    }

    public int changeStatusReject(String[] memberNo) {
        Connection conn = getConnection();
        int result1 = new GradeUpDao().changeGrade(conn,memberNo);

        if(result1 == memberNo.length){
            commit();
        }else{
            rollback();
        }
        close();
        return result1;
    }


    public Notice selectNotice(long animalNo) {
        Connection conn=getConnection();
        Notice n=new NoticeDao().selectNotice(conn,animalNo);
        close();
        return n;
    }
    public int changeAdoptApplySubRead(int subNo){
        Connection conn = getConnection();
        int result = new SubscriptionDao().changeAdoptApplySubRead(conn,subNo);
        if(result > 0){
            commit();
        } else{
            rollback();
        }
        close();
        return result;
    }

}
