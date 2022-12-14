package tk.newsoulmate.web.member.service;

import tk.newsoulmate.domain.dao.ConfirmDao;
import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.dto.request.ManageMemberUpdateGradeRequest;
import tk.newsoulmate.web.member.controller.EmailController;

import java.sql.Connection;

import static tk.newsoulmate.web.common.JDBCTemplet.*;


public class MemberService {

    public int insertMember(Member m) {
        Connection conn = getConnection();
        int result = new MemberDao().insertMember(m, conn);
        if (result > 0) {
            commit();
        } else {
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
        MemberDao md = new MemberDao();
        Member m = md.loginMember(memberId, memberPwd, conn);
        if (m != null) {
//            int result=md.updateResent(m,conn);
//            if(result>0){
//                commit();
//            }else{
//                rollback();
//            }
        }
        close();
        return m;
    }

    public Member findId(String memberName, String Email) {
        Connection conn = getConnection();
        Member m = new MemberDao().findId(conn, memberName, Email);
        close();
        return m;
    }

    /**
     * 입력받은 이메일 아이디 이름과 일치하는 계정이 있으면 랜덤한
     * @param memberName
     * @param memberId
     * @param Email
     * @return
     */
    public int resetPwd(String memberName, String memberId, String Email) {
        Connection conn = getConnection();

        int memberNo= new MemberDao().findUser(conn, memberName, memberId, Email);
        if(memberNo==0){
            close();
            return 0;
        }//--이뒤로는 입력된값이 유효함
        String newPwd=EmailController.rannum();
        int result=new MemberDao().updatePassword(conn,memberNo,newPwd);
        if(result>0) {
            commit();
            close();//미리반납
            result=new EmailController().sendPasswordMail(newPwd,Email);
        }else{
            rollback();
            close();
        }

        return result;

    }

    public int updatePassword(int memberNo,String password) {
        Connection conn = getConnection();
        int result = new MemberDao().updatePassword(conn, memberNo, password);
        close();
        return result;
    }

    public int deleteMember(String memberId, String memberPwd) {

        Connection conn = getConnection();
        int result = new MemberDao().deleteMember(memberId, memberPwd, conn);
        if (result > 0) {
            commit();
        } else {
            rollback(conn);
        }
        close();
        return result;
    }

    public int deleteMember(long memberNo) {

        Connection conn = getConnection();
        int result = new MemberDao().deleteMember(memberNo, conn);
        if (result > 0) {
            commit();
        } else {
            rollback(conn);
        }
        close();
        return result;
    }

    public int updateGrade(ManageMemberUpdateGradeRequest updateGradeReq) {
        Connection conn = getConnection();

        int result = new MemberDao().updateGrade(updateGradeReq, conn);
        if (updateGradeReq.isToUser()) {
            new MemberDao().deleteShelterInfo(updateGradeReq.getMemberNo(), conn);
        }

        if (result > 0) {
            commit();
        } else {
            rollback(conn);
        }
        close();
        return result;
    }


    /**
     * 인증번호를 집어넣고
     * 인증번호 키를 반환하는 함수
     *
     * @return
     */
    public int insertConfirm(String confirmCode) {
        Connection conn = getConnection();
        ConfirmDao confirmDao = new ConfirmDao();
        int confirmNo = confirmDao.selectConfirmNo(conn);
        int result = confirmDao.insertConfirm(conn, confirmNo, confirmCode);
        if (result > 0) {
            commit();
            close();
            return confirmNo;
        } else {
            rollback();
            close();
            return 0;
        }
    }

    public int copareConfirm(int confirmNo,String confirmCode){
        Connection conn = getConnection();
        int result = new ConfirmDao().copareConfirm(conn,confirmNo,confirmCode);
        close();
        return result;

    };

}