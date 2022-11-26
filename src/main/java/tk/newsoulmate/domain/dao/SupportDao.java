package tk.newsoulmate.domain.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import tk.newsoulmate.domain.vo.ManageMember;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.response.ShelterSupportResponse;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.domain.vo.response.SupportCompleteResponse;
import tk.newsoulmate.domain.vo.SupportPage;
import tk.newsoulmate.domain.vo.response.SupportCompleteResponse;
import tk.newsoulmate.domain.vo.type.MemberGrade;
import tk.newsoulmate.domain.vo.type.SupportStatus;
import tk.newsoulmate.domain.vo.type.WithdrawStatus;
import tk.newsoulmate.web.common.JDBCTemplet;

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

    public int updateStatus(Connection conn, String merchantUid, SupportStatus status) {
        PreparedStatement psmt = null;
        int result = 0;
        String sql = prop.getProperty("updateStatus");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, status.name());
            psmt.setString(2, merchantUid);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }

    public int updateWithdrawStatus(Connection conn, String merchantUid, WithdrawStatus wdStatus) {
        PreparedStatement psmt = null;
        int result = 0;
        String sql = prop.getProperty("updateWithdrawStatusByMerchantUid");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, wdStatus.name());
            psmt.setString(2, merchantUid);
            result = psmt.executeUpdate();
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

    public ArrayList<SupportCompleteResponse> findAllOnlyDone(Connection conn, int memberNo, SupportPage page) {
        ArrayList<SupportCompleteResponse> supportList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAll");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, memberNo);
            psmt.setInt(2, page.getEndSupport());
            psmt.setInt(3, page.getStartSupport());
            rset = psmt.executeQuery();
            while(rset.next()) {
                supportList.add(new SupportCompleteResponse(
                        rset.getInt("SUPPORT_NO"),
                        rset.getString("SHELTER_NAME"),
                        rset.getLong("AMOUNT"),
                        rset.getDate("PAY_TIME")
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

    public ArrayList<SupportCompleteResponse> findAllOnlyDoneByDate(Connection conn, int memberNo, LocalDate startDate, LocalDate endDate, SupportPage page) {
        ArrayList<SupportCompleteResponse> supportList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAllByDate");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, memberNo);
            psmt.setString(2, startDate.toString());
            psmt.setString(3, endDate.toString());
            psmt.setInt(4, page.getEndSupport());
            psmt.setInt(5, page.getStartSupport());
            rset = psmt.executeQuery();
            while(rset.next()) {
                supportList.add(new SupportCompleteResponse(
                        rset.getInt("SUPPORT_NO"),
                        rset.getString("SHELTER_NAME"),
                        rset.getLong("AMOUNT"),
                        rset.getDate("PAY_TIME")
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

    public int countOnlyDone(Connection conn, int memberNo) {
        int count = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("count");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, memberNo);
            rset = psmt.executeQuery();
            if (rset.next()) {
                count = rset.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return count;
    }

    public int countOnlyDoneByDate(Connection conn, int memberNo, LocalDate startDate, LocalDate endDate) {
        int count = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("countByDate");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, memberNo);
            psmt.setString(2, startDate.toString());
            psmt.setString(3, endDate.toString());
            rset = psmt.executeQuery();
            if (rset.next()) {
                count = rset.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return count;
    }

    public List<ShelterSupportResponse> findAllOnlyDoneByShelterNo(Connection conn, long shelterNo) {
        ArrayList<ShelterSupportResponse> supportList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAllByShelterNo");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, shelterNo);
            rset = psmt.executeQuery();
            while(rset.next()) {
                supportList.add(new ShelterSupportResponse(
                    rset.getInt("SUPPORT_NO"),
                    rset.getString("MEMBER_NAME"),
                    rset.getDate("PAY_TIME").toLocalDate(),
                    rset.getLong("AMOUNT"),
                    rset.getString("PHONE"),
                    WithdrawStatus.valueOf(rset.getString("WD_STATUS"))
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

    public int withdraw(Connection conn, long supportNo) {
        int result = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("updateWithdrawStatusBySupportNo");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, WithdrawStatus.REQUESTED.name());
            psmt.setLong(2, supportNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return result;
    }
}
