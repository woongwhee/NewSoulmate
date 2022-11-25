package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.domain.vo.Volunteer;

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



    public int volunteerApplyListCount(Connection conn , long shelterNo) {

        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("volunteerApplyListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1,shelterNo);
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

    public ArrayList<Volunteer> volunteerApplyList(Connection conn, PageInfo pi, long shelterNo) {
        ArrayList<Volunteer> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("volunteerApplyList");

        try {
            psmt = conn.prepareStatement(sql);
            if (pi.getCurrentPage() == 0) {
                return list;
            }
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;

            psmt.setLong(1,shelterNo);
            psmt.setInt(2, startRow);
            psmt.setInt(3, endRow);

            rset = psmt.executeQuery();

            while (rset.next()) {
                Volunteer vo = new Volunteer();
                vo.setVolunteerNo(rset.getInt("VOLUNTEER_NO"));
                vo.setShelterNo(rset.getInt("SHELTER_NO"));
                vo.setMemberNo(rset.getInt("MEMBER_NO"));
                vo.setStartDate(rset.getDate("START_DATE"));
                vo.setTelNumber(rset.getString("TEL_NUMBER"));
                vo.setName(rset.getString("NAME"));
                vo.setMemberId(rset.getString("MEMBER_ID"));
                vo.setShelterName(rset.getString("SHELTER_NAME"));
                vo.setVolRead(rset.getString("VOL_READ"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return list;
        }

    }
