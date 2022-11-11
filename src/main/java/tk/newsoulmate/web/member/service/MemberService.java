package tk.newsoulmate.web.member.service;

import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;


public class MemberService {


    public Member loginMember(String memberId, String memberPwd) {

        Connection conn = JDBCTemplet.getConnection();

        Member m = new MemberDao().loginMember(memberId, memberPwd, conn);

        JDBCTemplet.close();

        return m;

    }

    public int insertMember(Member m) {
        Connection conn = JDBCTemplet.getConnection();

        int result = new MemberDao().insertMember(m, conn);

        if(result > 0) {
            JDBCTemplet.commit();
        }else {
            JDBCTemplet.rollback(conn);
        }

        JDBCTemplet.close();

        return result;

    }


    public int idCheck(String checkId) {

        Connection conn = JDBCTemplet.getConnection();

        int count = new MemberDao().idCheck(conn, checkId);

        JDBCTemplet.close();

        return count;
    }


    public int nicknameCheck(String nickName) {

        Connection conn = JDBCTemplet.getConnection();

        int count = new MemberDao().nicknameCheck(conn, nickName);

        JDBCTemplet.close();

        return count;

    }

  /*  public Member searchMemberId(Connection conn, String memberName, String email) {

        Connection conn = JDBCTemplet.getConnection();

        Member m = MemberDao.searchMemberId(conn, memberName, email);

        JDBCTemplet.close(conn);

        return m;
    }

    public Member searchMemberPw(Connection conn, String memberName, String memberId, String email) {

        Connection conn = JDBCTemplet.getConnection();

        Member m = MemberDao.searchMemberPw(conn, memberName, memberId, email);

        JDBCTemplet.close(conn);

        return m;

    }*/



}