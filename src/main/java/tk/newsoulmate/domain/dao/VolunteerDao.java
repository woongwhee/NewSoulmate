package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class VolunteerDao {

    private Properties prop = new Properties();

    public VolunteerDao() {
        try {
            prop.loadFromXML(new FileInputStream(VolunteerDao.class.getResource("/sql/volunteer/Volunteer-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int volunteerApplyListCount(Connection conn, long shelterNo) {

        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("volunteerApplyListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, shelterNo);
            rset = psmt.executeQuery();
            if (rset.next()) {
                listCount = rset.getInt("cnt");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return listCount;
    }

    public ArrayList<Volunteer> volunteerApplyList(Connection conn, PageInfo pi2, long shelterNo) {
        ArrayList<Volunteer> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("volunteerApplyList");

        try {
            psmt = conn.prepareStatement(sql);
            if (pi2.getCurrentPage() == 0) {
                return list;
            }
            int startRow = (pi2.getCurrentPage() - 1) * pi2.getBoardLimit() + 1;
            int endRow = startRow + pi2.getBoardLimit() - 1;

            psmt.setLong(1, shelterNo);
            psmt.setInt(2, startRow);
            psmt.setInt(3, endRow);

            rset = psmt.executeQuery();

            while (rset.next()) {
                Volunteer vo = new Volunteer();
                vo.setVolunteerNo(rset.getInt("VOLUNTEER_NO"));
                vo.setShelterNo(rset.getLong("SHELTER_NO"));
                vo.setMemberNo(rset.getInt("MEMBER_NO"));
                vo.setStartDate(rset.getDate("START_DATE"));
                vo.setTelNumber(rset.getString("TEL_NUMBER"));
                vo.setName(rset.getString("NAME"));
                vo.setMemberId(rset.getString("MEMBER_ID"));
                vo.setShelterName(rset.getString("SHELTER_NAME"));
                vo.setVolRead(rset.getString("VOL_READ"));
                list.add(vo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return list;
    }

    public int volApplyInsert(Connection conn, Volunteer vol) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("volApplyInsert");


        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, vol.getShelterNo());
            psmt.setInt(2, vol.getMemberNo());
            psmt.setDate(3, vol.getStartDate());
            psmt.setString(4, vol.getTelNumber());
            psmt.setString(5, vol.getName());
            psmt.setString(6, vol.getGender());

            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(psmt);
        }

        return result;
    }

    public Volunteer selectVolunteer(Connection conn, int volunteerNo) {
        Volunteer v = new Volunteer();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectVolunteer");


        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, volunteerNo);
            rset = psmt.executeQuery();
            while (rset.next()) {
                v.setVolunteerNo(rset.getInt("VOLUNTEER_NO"));
                v.setShelterNo(rset.getLong("SHELTER_NO"));
                v.setMemberNo(rset.getInt("MEMBER_NO"));
                v.setStartDate(rset.getDate("START_DATE"));
                v.setTelNumber(rset.getString("TEL_NUMBER"));
                v.setApplyDate(rset.getDate("APPLY_DATE"));
                v.setName(rset.getString("NAME"));
                v.setGender(rset.getString("GENDER"));
                v.setShelterName(rset.getString("SHELTER_NAME"));
                v.setVolRead(rset.getString("VOL_READ"));
                v.setMemberId(rset.getString("MEMBER_ID"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return v;
    }

    public int ReadVolunteer(Connection conn, int volunteerNo) {
        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("ReadVolunteer");
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,volunteerNo);

            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(psmt);
        }

        return result;
    }

    public int deleteVolunteer(Connection conn, int volunteerNo) {
        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("deleteVolunteer");
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,volunteerNo);

            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(psmt);
        }
        System.out.println(result);
        return result;
    }
}
