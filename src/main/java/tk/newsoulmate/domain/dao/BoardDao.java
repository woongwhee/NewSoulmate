package tk.newsoulmate.domain.dao;

import oracle.jdbc.proxy.annotation.Pre;
import tk.newsoulmate.domain.vo.*;

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

public class BoardDao {
    private Properties prop = new Properties();

    public BoardDao(){
        try {
            prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/board/Board-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int adoptReviewListCount(Connection conn) {

        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset =  null;
        String sql = prop.getProperty("selectListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,"ADOPT");

            rset = psmt.executeQuery();

            if(rset.next()) {
                listCount = rset.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return listCount;
    }

    public ArrayList<Board> selectAdoptReviewList(Connection conn , PageInfo pi){

        ArrayList<Board> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("adoptReviewList");
        try {
            psmt = conn.prepareStatement(sql);
            int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
            int endRow = startRow + pi.getBoardLimit() -1;
            psmt.setInt(1, startRow);
            psmt.setInt(2, endRow);
            rset = psmt.executeQuery();

            while(rset.next()) {
                list.add(Board.selectAdoptReviewList(rset.getInt("BOARD_NO") ,
                        rset.getString("BOARD_TITLE"),
                        rset.getString("MEMBER_NO"),
                        rset.getInt("READ_COUNT"),
                        rset.getDate("CREATE_DATE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return list;
    }

    public int readCount(Connection conn, int boardNo) {

        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("readCount");
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(psmt);
        }
        return result;
    }



    public ArrayList<Board> selectQnAList(Connection conn, PageInfo pi) {

        ArrayList<Board> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectQnAList");

        try {
            psmt = conn.prepareStatement(sql);

            if (pi.getCurrentPage() != 0 ){
                int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
                int endRow = startRow + pi.getBoardLimit() - 1;
                psmt.setInt(1, startRow);
                psmt.setInt(2, endRow);
            }


            rset = psmt.executeQuery();

            while (rset.next()) {
                list.add(Board.selectQnAList(rset.getString("RESULTSTATUS")
                        , rset.getInt("BOARD_NO")
                        , rset.getString("BOARD_TITLE")
                        , rset.getDate("CREATE_DATE")
                        , rset.getString("MEMBER_NAME")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return list;

    }


    public Board selectAdoptReviewDetail(Connection conn, int boardNo) {

        Board b = null;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectAdoptReviewDetail");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);

            rset = psmt.executeQuery();

            if(rset.next()) {
                b =  Board.selectAdoptReviewDetail(
                        rset.getString("BOARD_TITLE"),
                        rset.getString("MEMBER_NO"),
                        rset.getDate("ISSUE_DATE"),
                        rset.getDate("CREATE_DATE"),
                        rset.getInt("READ_COUNT"),
                        rset.getString("BOARD_CONTENT")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }

        return b;

    }

    public int insertReply(Connection conn , Reply r) {

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

    public ArrayList<Reply> selectReplyList(Connection conn , int boardNo){

        ArrayList<Reply> list = new ArrayList<>();

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectReplyList");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);

            rset = psmt.executeQuery();

            while(rset.next()) {

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
    public int selectListCount(Connection conn, String categoryName){

        int listCount = 0;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, categoryName);

            rset = psmt.executeQuery();

            if(rset.next()){
                listCount = rset.getInt("cnt");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return listCount;
    }

    public int insertBoard(Board b, Connection conn){
        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertBoard");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, b.getCategoryNo());
            psmt.setString(2, b.getBoardTitle());
            psmt.setString(3, b.getBoardContent());
            psmt.setInt(4,b.getMemberNo());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }

    public int insertAttachment(Attachment at, Connection conn){

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


    public List<Board> selectVolunteerThumNail(Connection conn, int page) {
        List<Board> vList=new ArrayList<>();


        return vList;
    }

    public int increaseCount(Connection conn, int boardNo){
        int result = 0;

        PreparedStatement psmt = null;
        String sql = prop.getProperty("increaseCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardNo);

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }

    public Board selectInquireBoard(Connection conn, int boardNo){

        Board b = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectInquireBoard");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,boardNo);

            rset = psmt.executeQuery();

            if (rset.next()){
                b = Board.selectInquireBoard(
                        rset.getInt("BOARD_NO"),
                        rset.getString("CATEGORY_NAME"),
                        rset.getString("BOARD_TITLE"),
                        rset.getString("BOARD_CONTENT"),
                        rset.getString("MEMBER_ID"),
                        rset.getDate("CREATE_DATE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return b;
    }

    public Attachment selectInquireAttachment(Connection conn, int boardNo){

        Attachment at = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;

        String sql = prop.getProperty("selectInquireAttachment");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,boardNo);

            rset = psmt.executeQuery();

            if(rset.next()){
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

























}
