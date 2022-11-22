package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            psmt.setInt(1,up.getMemberNo());
            psmt.setLong(2,up.getShelterNo());
            psmt.setInt(3,up.getFileNo());
            psmt.setString(4, up.getShelterTel());
            psmt.setString(5,up.getShelterLandLine());
            psmt.setString(6, up.getShelterCompNo());
            psmt.setString(7, up.getShelterAddress());
            result = psmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(psmt);
        }
        return result;
    }
}
