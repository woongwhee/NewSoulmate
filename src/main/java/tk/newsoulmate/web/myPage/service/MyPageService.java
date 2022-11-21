package tk.newsoulmate.web.myPage.service;
import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.Member;

import java.sql.Connection;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class MyPageService {

    public Member updateMember(Member m){

        Connection conn = getConnection();

        int result = new MemberDao().updateMember(conn,m);

        Member updateMem = null;

        if(result > 0){
            commit();
            // 갱신된 회원객체 다시 조회
            updateMem = new MemberDao().loginMember(m.getMemberNo(),conn);
        }else{
            rollback();
        }
        close();
        return updateMem;
    }

    public String checkPwd(int memberNo){
        Connection conn = getConnection();

        String memberPwd = new MemberDao().checkPwd(conn,memberNo);

        close();

        return  memberPwd;
    }

}
