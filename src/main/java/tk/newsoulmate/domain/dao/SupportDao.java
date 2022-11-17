package tk.newsoulmate.domain.dao;

import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SupportDao {

    private Properties prop = new Properties();

    public int insertSupport(Connection conn, int supportNo, int price, int memberNo ) {
        PreparedStatement psmt = null;
        int result = 0;
        String sql = prop.getProperty("insertSupport");
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, supportNo);
            psmt.setInt(2, price);
            psmt.setInt(3, memberNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }





}
