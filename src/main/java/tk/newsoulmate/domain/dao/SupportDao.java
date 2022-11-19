package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.MemberGrade;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class SupportDao {

    private Properties prop = new Properties();

    public SupportDao(){

        String FilePath=NoticeDao.class.getResource("/sql/support/Support-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(FilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int initializeSupport(Connection conn, long shelterNo, int memberNo, String number, long amount) {
        PreparedStatement psmt = null;
        int result = 0;
        String sql = prop.getProperty("initializeSupport");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, shelterNo);
            psmt.setInt(2, memberNo);
            psmt.setString(3, number);
            psmt.setLong(4, amount);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }

    public Support findByMerchantUid(Connection conn, String merchantUid) {
        PreparedStatement psmt = null;
        Support result = null;
        String sql = prop.getProperty("findByMerchantUid");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, merchantUid);
            ResultSet rset = psmt.executeQuery();
            if (rset.next()) {
                result = mapToSupport(rset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }

    public Support mapToSupport(ResultSet resultSet) throws SQLException {
        int supportNo = resultSet.getInt("SUPPORT_NO");
        long shelterNo = resultSet.getLong("SHELTER_NO");
        int memberNo = resultSet.getInt("MEMBER_NO");
        String merchantUid = resultSet.getString("MERCHANT_UID");
        long amount = resultSet.getLong("AMOUNT");

        return new Support(supportNo, shelterNo, memberNo, merchantUid, amount);
    }

    public ArrayList<Support> selectSupportList(Connection conn) {
        ArrayList<Support> supportList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectSupportList");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while(rset.next()) {
                supportList.add(new Support(
                        rset.getInt("SUPPORT_NO"),
                        rset.getLong("SHELTER_NO"),
                        rset.getString("MERCHANT_UID"),
                        rset.getLong("AMOUNT"),
                        rset.getDate("PAY_TIME"),
                        rset.getString("STATUS")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return supportList;
    }


}
