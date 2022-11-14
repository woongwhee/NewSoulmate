package tk.newsoulmate.web.member.service;

import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;


public class MemberService {

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


    public Member loginMember(String memberId, String memberPwd) {

        Connection conn = JDBCTemplet.getConnection();

        Member m = new MemberDao().loginMember(memberId, memberPwd, conn);

        JDBCTemplet.close();

        return m;

    }


    public Member findId(String memberName, String Email) {

        Connection conn = JDBCTemplet.getConnection();

        Member m = new MemberDao().findId(conn, memberName, Email);

        JDBCTemplet.close();

        return m;
    }


    public Member findPwd(String memberName, String memberId, String Email) {

        Connection conn = JDBCTemplet.getConnection();

        Member m = new MemberDao().findPwd(conn, memberName, memberId, Email);

        JDBCTemplet.close();

        return m;

    }



}