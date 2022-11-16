package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class AdoptDao {

    private Properties prop = new Properties();

    public AdoptDao(){
        try {
            prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/adopt/Adopt-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int adoptApplyInsert(Connection conn, Subscription sb){
        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("adoptApplyInsert");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,sb.getMemberNo());
            psmt.setLong(2,sb.getShelterNo());
            psmt.setString(3,sb.getAnimalNo());
            psmt.setString(4,sb.getTelNum());
            psmt.setString(5,sb.getName());
            psmt.setString(6,sb.getGender());
            psmt.setString(7,sb.getAdoptReason());
            psmt.setString(8,sb.getAgreement());
            psmt.setString(9,sb.getWhenSick());
            psmt.setString(10,sb.getBigDuty());
            psmt.setString(11,sb.getWishDate());
            psmt.setString(12,sb.getSubRead());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }

    public long shelterNoByName(Connection conn,String animalNo){
        long shelterNo=0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("shelterNoByName");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,animalNo);
            rset = psmt.executeQuery();
            while(rset.next()) {
                shelterNo = rset.getLong("shelterNo");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return shelterNo;

    }


    public int checkAnimal(Connection conn, String animalNo){
        int checkAnimal = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("checkAnimal");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,animalNo);
            rset = psmt.executeQuery();
            while(rset.next()) {
                checkAnimal = rset.getInt("countNo");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return checkAnimal;

    }

}
