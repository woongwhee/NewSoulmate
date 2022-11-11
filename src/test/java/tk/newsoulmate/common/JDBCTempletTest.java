package tk.newsoulmate.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class JDBCTempletTest {

    @Test
    void getConnection() {
        Connection connection= JDBCTemplet.getConnection();
        String sql="SELECT * FROM MEMBER";
        ArrayList<Member> list=new ArrayList<>();

        ResultSet rset=null;
        PreparedStatement psmt=null;
        try {
            psmt=connection.prepareStatement(sql);
            rset=psmt.executeQuery();
            while(rset.next()) {
                Member m = new Member();
                rset.getString("USER_NAME");
                list.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(list.isEmpty());
    }
}