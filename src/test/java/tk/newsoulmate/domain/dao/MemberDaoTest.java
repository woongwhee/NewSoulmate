package tk.newsoulmate.domain.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static tk.newsoulmate.web.common.JDBCTemplet.*;
import static tk.newsoulmate.web.common.JDBCTemplet.rollback;

class MemberDaoTest {
    Connection conn;
    @BeforeEach
    void setUp() {
        conn= getTestConnection();
    }

    @AfterEach
    void tearDown() {
        rollback();
        close();
    }

    @Test
    void insertMember() {


    }

    @Test
    void loginMember() {
    }

    @Test
    void mapToLoginMember() {
    }

    @Test
    void idCheck() {
    }

    @Test
    void nicknameCheck() {
    }

    @Test
    void findId() {
    }

    @Test
    void findPwd() {
    }

    @Test
    void updatePassword() {
    }
}