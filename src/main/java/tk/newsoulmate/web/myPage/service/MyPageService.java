package tk.newsoulmate.web.myPage.service;
import tk.newsoulmate.domain.dao.AttachmentDao;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.GradeUpDao;
import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.*;

import java.sql.Connection;
import java.util.ArrayList;

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
    public int selectMyPageBoardListCount(Member loginUser){
        Connection conn = getConnection();

        int listCount = new BoardDao().selectMyPageBoardListCount(conn, loginUser);

        close();
        return listCount;
    }
    public ArrayList<Board> selectMyPageBoardList(PageInfo pi, Member loginUser){
        Connection conn = getConnection();

        ArrayList<Board> list = new BoardDao().selectMyPageBoardList(conn, pi, loginUser);

        close();

        return list;
    }
    public String checkPwd(int memberNo){
        Connection conn = getConnection();

        String memberPwd = new MemberDao().checkPwd(conn,memberNo);

        close();

        return  memberPwd;
    }
    public int gradeUp(GradeUp up, Attachment at){
        Connection conn = getConnection();
      int fileNo = new AttachmentDao().selectFileNo(conn);
      if(fileNo == 0){
          close();
          return fileNo;
      }
      if(at!=null) up.setFileNo(fileNo);
      int result1 = new GradeUpDao().insertGrade(up,conn);
      int result2 = 1;
      if(at!= null){
          at.setFileNo(fileNo);
          result2 = new AttachmentDao().insertGradeAttachment(at,conn);
      }
      if(fileNo >0 && result2 >0 && result1>0){
          commit();
      }else{
          rollback();
      }

      close();

      return fileNo*result1*result2;
    }




}
