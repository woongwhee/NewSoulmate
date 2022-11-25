package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class GradeUpDao {

    private Properties prop = new Properties();

    public GradeUpDao() {
        String fileName = GradeUpDao.class.getResource("/sql/gradeUp/GradeUp-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int insertGrade(GradeUp up, Connection conn) {
        int result = 0;
        PreparedStatement psmt = null;
        String insert = prop.getProperty("insertGrade");

        try {
            psmt = conn.prepareStatement(insert);
            psmt.setInt(1, up.getMemberNo());
            psmt.setLong(2, up.getShelterNo());
            psmt.setInt(3, up.getFileNo());
            psmt.setString(4, up.getShelterTel());
            psmt.setString(5, up.getShelterLandLine());
            psmt.setString(6, up.getShelterCompNo());
            psmt.setString(7, up.getShelterAddress());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }

    public ArrayList<GradeUp> selectAllGrade(Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        ArrayList<GradeUp> gList = new ArrayList<GradeUp>();
        String sql = prop.getProperty("selectUnReadGrade");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while (rset.next()) {
                GradeUp up = new GradeUp();
                up.setGradeNo(rset.getInt("GRADE_NO"));
                up.setMemberNo(rset.getInt("MEMBER_NO"));
                up.setMemberName(rset.getString("MEMBER_NAME"));
                up.setMemberId(rset.getString("MEMBER_ID"));
                up.setShelterNo(rset.getLong("SHELTER_NO"));
                up.setFileNo(rset.getInt("FILE_NO"));
                up.setShelterTel(rset.getString("SHELTER_TEL"));
                up.setShelterLandLine(rset.getString("SHELTER_LANDlINE"));
                up.setShelterCompNo(rset.getString("SHELTER_COMP_NO"));
                up.setShelterAddress(rset.getString("SHELTER_ADDRESS"));
                up.setShelterName(rset.getString("SHELTER_NAME"));
                gList.add(up);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(psmt);
            JDBCTemplet.close(rset);
        }
        return gList;
    }

    public int changeGrade(Connection conn, String[] memberNo) {
        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("changeGrade");

        try {
            psmt = conn.prepareStatement(sql);

            for (int i = 0; i < memberNo.length; i++) {
                psmt.setString(1, memberNo[i]);
                result += psmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(psmt);
        }

        return result;
    }
}

