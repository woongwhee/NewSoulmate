package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Reply;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public int insertBoardReply(Connection conn, Reply r) {


        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("insertBoardReply");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, r.getBoardNo());
            psmt.setInt(2, r.getMemberNo());
            psmt.setString(3, r.getReplyContent());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(psmt);
        }
        return result;
    }
    public int insertNoticeReply(Connection conn, Reply r) {
        int result = 0;

        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertNoticeReply");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, r.getReplyNo());
            psmt.setInt(2, r.getNoticeNo());
            psmt.setInt(3, r.getMemberNo());
            psmt.setInt(4, r.getReplyType().typeNo);
            psmt.setString(5, r.getReplyContent());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(psmt);
        }
        return result;
    }
    public ArrayList<Reply> selectReplyList(Connection conn, int boardNo) {

        ArrayList<Reply> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReplyList");
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);
            rset = psmt.executeQuery();
            Reply r=null;
            while ((r=nomalReplyMapper(rset))!=null) {
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return list;
    }

    private Reply nomalReplyMapper(ResultSet rset) throws SQLException {
        Reply r=null;
        if(rset.next()){
            r=new Reply(
                    rset.getInt("REPLY_NO"),
                    rset.getInt("BOARD_NO"),
                    rset.getInt("MEMBER_NO"),
                    rset.getString("MEMBER_NAME"),
                    rset.getString("REPLY_CONTENT"),
                    rset.getDate("REPLY_DATE")
            );
        };
        return r;
    }

}
