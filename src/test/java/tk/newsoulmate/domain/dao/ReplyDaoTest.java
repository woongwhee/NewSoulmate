package tk.newsoulmate.domain.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static tk.newsoulmate.web.common.JDBCTemplet.*;

class ReplyDaoTest {
    private ReplyDao rd;
    private Connection conn;
    @BeforeEach
    public void initialize() {
        conn= getTestConnection();
        rd=new ReplyDao();
    }
    @AfterEach
    public void destroy() {
        rollback();
        close();
    }
    @Test
    void insertNoticeReply() {
    }

    @Test
    void selectReplyNo() {
        int result=rd.selectReplyNo(conn);
        Assertions.assertNotEquals(0,result);

    }
}