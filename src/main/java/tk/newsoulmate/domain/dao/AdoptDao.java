package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Subscription;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class AdoptDao {

    private Properties prop = new Properties();

    public AdoptDao(){
        try {
            prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/board/Board-Mapper.xml").getPath()));
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
            psmt.setLong(3,sb.getAnimalNo());
            psmt.setString(4,sb.getTelNum());
            psmt.setString(5,sb.getName());
            psmt.setString(6,sb.getGender());
            psmt.setString(7,sb.getAdoptReason());
            psmt.setString(8,sb.getAgreement());
            psmt.setString(9,sb.getWhenSick());
            psmt.setString(10,sb.getBigDuty());
            psmt.setString(11,sb.getWishDate());
            psmt.setString(12,sb.getSubRead());
            psmt.setString(13,sb.getSubDate());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }


}
