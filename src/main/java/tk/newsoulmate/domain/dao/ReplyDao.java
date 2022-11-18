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
    public int insertImgReply(Connection conn, Reply r) {
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
    public ArrayList<Reply> selectReplyList(Connection conn, int boardNo) {

        ArrayList<Reply> list = new ArrayList<>();

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectReplyList");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);

            rset = psmt.executeQuery();

            while (rset.next()) {

                list.add(new Reply(
                        rset.getInt(1),
                        rset.getInt(2),
                        rset.getString(3),
                        rset.getDate(4)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return list;
    }
}
