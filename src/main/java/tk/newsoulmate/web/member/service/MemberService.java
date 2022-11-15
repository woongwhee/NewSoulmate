package tk.newsoulmate.web.member.service;

import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;

import static tk.newsoulmate.web.common.JDBCTemplet.*;


public class MemberService {

    public int insertMember(Member m) {

        Connection conn = getConnection();
        int result = new MemberDao().insertMember(m, conn);
        if(result > 0) {
            commit();
        }else {
            rollback(conn);
        }
        close();
        return result;

    }


    public int idCheck(String checkId) {

        Connection conn = getConnection();
        int count = new MemberDao().idCheck(conn, checkId);
        close();
        return count;
    }


    public int nicknameCheck(String nickName) {
        Connection conn = getConnection();
        int count = new MemberDao().nicknameCheck(conn, nickName);
        close();
        return count;

    }


    public Member loginMember(String memberId, String memberPwd) {
        Connection conn = getConnection();
        MemberDao md=new MemberDao();
        Member m= md.loginMember(memberId,memberPwd,conn);
        close();
        return m;
    }

    public Member findId(String memberName, String Email) {

        Connection conn = getConnection();
        Member m = new MemberDao().findId(conn, memberName, Email);
        close();
        return m;
    }


    public Member findPwd(String memberName, String memberId, String Email) {

        Connection conn = getConnection();
        Member m = new MemberDao().findPwd(conn, memberName, memberId, Email);
        close();
        return m;

    }


    /*public int updatePassword(PwdReset pwdReset) {
        if (!pwdReset.getPassword().equals(pwdReset.getPasswordConfirm())) {
            return 0;
        }

        Connection conn = JDBCTemplet.getConnection();
        int result = new MemberDao().updatePassword(conn, pwdReset.getMemberId(), pwdReset.getPassword());
        JDBCTemplet.close();
        return result;
    }*/

/*    public Member updatePwdMember(String MemberId, String MemberPwd, String updatePwd) {

        Connection conn = JDBCTemplet.getConnection();

        Member updateMem = null;

        int result = 0;

        result = new MemberDao().updatePwdMember(MemberId, MemberPwd, updatePwd, conn);

        if(result > 0) { // 성공시
            JDBCTemplet.commit(conn);

            // 갱신된 회원 객체 다시 조회해오기
            updateMem = new MemberDao().selectMember(MemberId, conn);


        }else { // 실패시
            JDBCTemplet.rollback(conn);

        }

        JDBCTemplet.close();

        return updateMem;
    }*/


}