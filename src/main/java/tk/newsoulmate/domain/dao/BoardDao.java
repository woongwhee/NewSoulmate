package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.vo.type.BoardType;
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

public class BoardDao {
    private Properties prop = new Properties();

    public BoardDao() {
        try {
            prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/board/Board-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int selectQnAListCount(Connection conn, BoardType boardType, Member loginUser) {

        int listCount = 0;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectQnAListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardType.typeNo);
            psmt.setInt(2, loginUser.getMemberNo());
            psmt.setInt(3, loginUser.getMemberGrade().gradeNumber);
            rset = psmt.executeQuery();

            if (rset.next()) {
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

    /**
     * 게시글 속성에 따른 게시글 개수를 반환하는 메서드
     * @param conn
     * @param boardType
     * @return 정수로 반환된다.
     */
    public int boardListCount(Connection conn,BoardType boardType) {

        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardType.typeNo);
            rset = psmt.executeQuery();
            if (rset.next()) {
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

    public ArrayList<Board> selectList(Connection conn,BoardType boardType, PageInfo pi) {
        ArrayList<Board> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectList");
        int start=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
        int end=(pi.getCurrentPage())*pi.getBoardLimit();
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, boardType.typeNo);
            psmt.setInt(2, start);
            psmt.setInt(3, end);
            rset = psmt.executeQuery();

            while (rset.next()) {
                list.add(Board.selectAdoptReviewList(rset.getInt("BOARD_NO"),
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

    public int increaseCount(Connection conn, int boardNo) {

        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("increaseCount");
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


    public ArrayList<Board> selectQnAList(Connection conn, PageInfo pi, Member loginUser) {

        ArrayList<Board> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectQnAList");

        try {
            psmt = conn.prepareStatement(sql);

            if (pi.getCurrentPage() == 0) {
                return list;
            }
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            psmt.setInt(1, loginUser.getMemberNo());
            psmt.setInt(2, loginUser.getMemberGrade().gradeNumber);
            psmt.setInt(3, startRow);
            psmt.setInt(4, endRow);
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


    public Board selectReviewDetail(Connection conn, int boardNo) {

        Board b = null;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectReviewDetail");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);

            rset = psmt.executeQuery();

            if (rset.next()) {
                b = Board.selectReviewDetail(
                        rset.getInt("BOARD_NO"),
                        rset.getString("BOARD_TITLE"),
                        rset.getInt("MEMBER_NO"),
                        rset.getString("NICKNAME"),
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


    /**
     * board의 시퀀스값을 생성시키는 메서드
     * @return 시퀸스 생성에 실패했을시 0을반환
     */
    public int selectBoardNo(Connection conn){
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectBoardNo");
        int boardNo=0;
        try {
            psmt = conn.prepareStatement(sql);
            rset=psmt.executeQuery();
            if (rset.next()) {
                boardNo=rset.getInt("NEXTVAL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return boardNo;
    }




    /**
     * 신고접수 , QnA 게시글 삽입용메서드
     * @param b
     * @param conn
     * @return
     */
    public int insertQnABoard(Board b, Connection conn) {
        int result = 0;
        PreparedStatement psmt = null;
        String insert = prop.getProperty("insertQnABoard");
        try {
            psmt = conn.prepareStatement(insert);
            psmt.setInt(1, b.getBoardNo());
            psmt.setInt(2, b.getMemberNo());
            psmt.setInt(3, b.getBoardType().typeNo);
            psmt.setString(4, b.getBoardTitle());
            psmt.setString(5, b.getBoardContent());
            psmt.setInt(6, b.getFileCount());
            psmt.setInt(7, b.getCategoryNo());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }
    /**
     * 봉사후기게시판 입양후기게시판 게시글 삽입용메서드
     * @param b
     * @param conn
     * @return
     */
    public int insertReviewBoard(Board b, Connection conn) {
        int result = 0;
        PreparedStatement psmt = null;
        String insert = prop.getProperty("insertReviewBoard");
        try {
            psmt = conn.prepareStatement(insert);
            psmt.setInt(1, b.getBoardNo());
            psmt.setInt(2, b.getMemberNo());
            psmt.setInt(3, b.getBoardType().typeNo);
            psmt.setString(4, b.getBoardTitle());
            psmt.setString(5, b.getBoardContent());
            psmt.setInt(6, b.getFileCount());
            psmt.setDate(7, b.getIssueDate());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }

    public List<Board> selectVolunteerThumNail(Connection conn, int page) {
        List<Board> vList = new ArrayList<>();
        return vList;
    }


    public Board selectInquireBoard(Connection conn, int boardNo, Member loginUser) {

        Board b = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectInquireBoard");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1, boardNo);
            psmt.setInt(2, loginUser.getMemberNo());
            psmt.setInt(3, loginUser.getMemberGrade().gradeNumber);
            rset = psmt.executeQuery();
            if (rset.next()) {
                b = Board.selectInquireBoard(
                        rset.getInt("BOARD_NO"),
                        rset.getString("CATEGORY_NAME"),
                        rset.getString("BOARD_TITLE"),
                        rset.getString("BOARD_CONTENT"),
                        rset.getString("MEMBER_ID"),
                        rset.getDate("CREATE_DATE"),
                        rset.getString("NICKNAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return b;
    }

    /**
     * 1:1문의 수정
     * @param conn
     * @param b
     * @return
     */
    public int updateInquireBoard(Connection conn, Board b) {
        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("updateInquireBoard");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,b.getCategoryNo());
            psmt.setString(2,b.getBoardTitle());
            psmt.setString(3,b.getBoardContent());
            psmt.setInt(4,b.getBoardNo());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }
    public int deleteBoard( Connection conn,int boardNo){
        int result = 0;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("deleteBoard");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,boardNo);

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }

    /**
     * 타입에 상관없이 해당 로그인유저의 게시글 개수 반환하는 메서드
     * @param conn
     * @param loginUser
     * @return
     */
    public int selectMyPageBoardListCount(Connection conn, Member loginUser){
        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectMyPageBoardListCount");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, loginUser.getMemberNo());

            rset = psmt.executeQuery();
            if (rset.next()) {
                listCount = rset.getInt("cnt");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return listCount;

    }

    public ArrayList<Board> selectMyPageBoardList(Connection conn, PageInfo pi, Member loginUser) {
        ArrayList<Board> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectMyPageBoardList");

        try {
            psmt = conn.prepareStatement(sql);
            if (pi.getCurrentPage() == 0) {
                return list;
            }
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;

            psmt.setInt(1,loginUser.getMemberNo());
            psmt.setInt(2,startRow);
            psmt.setInt(3,endRow);

            rset = psmt.executeQuery();
            while (rset.next()){
                list.add(Board.selectMyPageBoardList(rset.getInt("BOARD_NO")
                        , rset.getString("BOARD_TITLE")
                        , rset.getDate("CREATE_DATE")
                        , rset.getInt("READ_COUNT")
                        , rset.getInt("TYPE_NO")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }

        return list;
    }



}
