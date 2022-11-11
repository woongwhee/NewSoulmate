package tk.newsoulmate.web.inquire.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.web.common.JDBCTemplet;
import tk.newsoulmate.domain.vo.Board;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class InquireServiceTest {


    @Test
    void selectListCount() {
        Connection conn= JDBCTemplet.getTestConnection();
        int count= new BoardDao().selectListCount(conn,"문의");
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
        ArrayList<Board> list = new BoardDao().selectQnAList(conn, pi);



    }
}