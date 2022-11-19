package tk.newsoulmate.web.myPage.service;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.dao.MemberDao;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;

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

































}
