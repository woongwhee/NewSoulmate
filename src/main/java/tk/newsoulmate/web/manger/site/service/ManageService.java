package tk.newsoulmate.web.manger.site.service;

import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.dao.*;


import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ManageService {

    private MemberDao memberDao = new MemberDao();


    public ArrayList<ManageMember> selectMemberList() {
        Connection conn = getConnection();
        ArrayList<ManageMember> mList = memberDao.selectMemberList(conn);
        close();
        return mList;
    }

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

    public int selectCountMember() {
        Connection conn = getConnection();
        int countMember = memberDao.selectCountMember(conn);
        close();
        return countMember;
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

    public int ChangeAdoptApplySubRead(int subNo){
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
        return result1;
    }


    public Notice selectNotice(long animalNo) {
        Connection conn=getConnection();
        Notice n=new NoticeDao().selectNotice(conn,animalNo);
        close();
        return n;
    }
}
