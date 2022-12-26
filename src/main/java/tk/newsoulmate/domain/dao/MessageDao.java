package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.ExtMessage;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class MessageDao {
    private static Properties prop = new Properties();

    public MessageDao() {

        String FilePath = NoticeDao.class.getResource("/sql/message/Message-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(FilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertMessage(Connection conn, ExtMessage msg) {
        PreparedStatement psmt=null;
        int result=0;
        String sql=prop.getProperty("insertMessage");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,msg.getFromMemberNo());
            psmt.setInt(2,msg.getToMemberNo());
            psmt.setString(3,msg.getTelNum());
            psmt.setString(4,msg.getMessageContent());
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }
}
