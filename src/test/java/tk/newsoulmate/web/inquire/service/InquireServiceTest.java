package tk.newsoulmate.web.inquire.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.domain.vo.type.MemberGrade;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

class InquireServiceTest {


    @Test
    void selectListCount() {
        Connection conn= JDBCTemplet.getTestConnection();
        Member m=new Member();
        m.setMemberNo(1);
        m.setMemberGrade(MemberGrade.SITE_MANAGER);
        int count= new BoardDao().selectQnAListCount(conn, BoardType.QNA,m);
        Assertions.assertEquals(count,0);
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void selectQnAList() {
        Connection conn = JDBCTemplet.getTestConnection();
        PageInfo pi= new PageInfo(1,1,"문의");
        Member m=new Member();
        m.setMemberNo(1);
        m.setMemberGrade(MemberGrade.SITE_MANAGER);
        ArrayList<Board> list = new BoardDao().selectQnAList(conn, pi,m);


    }
}