package tk.newsoulmate.web.manger.site.service;

import tk.newsoulmate.domain.dao.AttachmentDao;
import tk.newsoulmate.domain.dao.GradeUpDao;
import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.domain.vo.Member;


import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ManageService {

    private MemberDao memberDao = new MemberDao();


    public ArrayList<Member> selectMemberList() {
        Connection conn = getConnection();
        ArrayList<Member> mList = memberDao.selectMemberList(conn);
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
}
