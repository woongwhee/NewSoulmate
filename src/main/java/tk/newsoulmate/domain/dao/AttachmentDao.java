package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Attachment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class AttachmentDao {

    private Properties prop = new Properties();

    public AttachmentDao(){
        try {
            prop.loadFromXML(new FileInputStream(AttachmentDao.class.getResource("/sql/attachment/Attachment-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Attachment selectInquireAttachment(Connection conn, int boardNo) {

        Attachment at = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectInquireAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);

            rset = psmt.executeQuery();

            if (rset.next()) {
                at = new Attachment();
                at.setFileNo(rset.getInt("FILE_NO"));
                at.setOriginName(rset.getString("ORIGIN_NAME"));
                at.setChangeName(rset.getString("CHANGE_NAME"));
                at.setFilePath(rset.getString("FILE_PATH"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return at;


    }
    public int insertAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, at.getOriginName());
            psmt.setString(2, at.getChangeName());
            psmt.setString(3, at.getFilePath());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }

        return result;


    }



















}
