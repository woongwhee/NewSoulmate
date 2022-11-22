package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


    public Attachment selectBoardAttachment(Connection conn, int boardNo) {

        Attachment at = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectBoardAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);
            rset = psmt.executeQuery();
            at=boardAttachmentMapper(rset);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return at;


    }
    public List<Attachment> selectReplyAttachment(Connection conn, int noticeNo) {
        List<Attachment> aList=new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReplyAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, noticeNo);
            rset = psmt.executeQuery();
            Attachment at=null;
            while((at=replyAttachmentMapper(rset))!=null){
                aList.add(at);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return aList;


    }
    public int insertBoardAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertBoardAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, at.getBoardNo());
            psmt.setString(2, at.getOriginName());
            psmt.setString(3, at.getChangeName());
            psmt.setString(4, at.getFilePath());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }

        return result;


    }
 public int insertReplyAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertReplyAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, at.getReplyNo());
            psmt.setString(2, at.getOriginName());
            psmt.setString(3, at.getChangeName());
            psmt.setString(4, at.getFilePath());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }

        return result;

    }

    public int updateInquireAttachment(Attachment at, Connection conn){
        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("updateInquireAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1,at.getOriginName());
            psmt.setString(2,at.getChangeName());
            psmt.setString(3,at.getFilePath());
            psmt.setInt(4,at.getFileNo());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }
    public void deleteInquireAttachment(int boardNo, Connection conn){
        PreparedStatement psmt = null;

        String sql = prop.getProperty("deleteInquireAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);

            psmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }


    }

    private Attachment replyAttachmentMapper(ResultSet rset) throws SQLException {
        Attachment at=null;
        if(rset.next()){
           at=Attachment.replyAttachment(rset.getInt("FILE_NO"),
                    rset.getInt("REPLY_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"),
                    rset.getDate("UPLOAD_DATE"));

        }
        return at;
    }
  private Attachment boardAttachmentMapper(ResultSet rset) throws SQLException {
        Attachment at=null;
        if(rset.next()){
            at=Attachment.fileAttachment(rset.getInt("FILE_NO"),
                    rset.getInt("BOARD_NO"),
                    rset.getString("ORIGIN_NAME"),
                    rset.getString("CHANGE_NAME"),
                    rset.getString("FILE_PATH"),
                    rset.getDate("UPLOAD_DATE"));
        }
        return at;
    }

    public int selectFileNo(Connection conn){
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectFileNo");
        int fileNo=0;
        try {
            psmt = conn.prepareStatement(sql);
            rset=psmt.executeQuery();
            if (rset.next()) {
                fileNo=rset.getInt("NEXTVAL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return fileNo;
    }


    public int insertGradeAttachment(Attachment at, Connection conn) {

        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertGradeAttachment");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,at.getFileNo());
            psmt.setInt(2, at.getBoardNo());
            psmt.setString(3, at.getOriginName());
            psmt.setString(4, at.getChangeName());
            psmt.setString(5, at.getFilePath());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }

        return result;

    }
}
