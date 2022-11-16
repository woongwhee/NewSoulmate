package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Reply;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class ReplyDao {
    private static Properties prop=new Properties();
    public ReplyDao(){

        String FilePath=NoticeDao.class.getResource("/sql/reply/Reply-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(FilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public int insertReply(Connection conn, Reply r) {

        int result = 0;

        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertReply");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, r.getReplyNo());
            psmt.setInt(2, r.getBoardNo());
            psmt.setInt(3, Integer.parseInt(String.valueOf(r.getMemberNo())));
            psmt.setString(4, r.getReplyContent());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(psmt);
        }
        return result;
    }
}
