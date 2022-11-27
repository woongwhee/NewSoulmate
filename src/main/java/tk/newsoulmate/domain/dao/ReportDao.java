package tk.newsoulmate.domain.dao;

import oracle.jdbc.proxy.annotation.Pre;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Report;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ReportDao {
    private static Properties prop=new Properties();
    public ReportDao(){

        String FilePath=NoticeDao.class.getResource("/sql/report/Report-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(FilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public int insertReport(Connection conn, Report report){
        int result=0;
        PreparedStatement psmt=null;
        String sql= prop.getProperty("insertReport");
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,report.getCategoryNo());
            psmt.setString(2,report.getReportContent());
            psmt.setInt(3,report.getRefType().typeNo);
            psmt.setInt(4,report.getRefNo());
            result= psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }
    public int selectReportListCount(Connection conn){
        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReportListCount");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            if (rset.next()){
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
    public ArrayList<Report> selectReportList(Connection conn, PageInfo pi){
        ArrayList<Report> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReportList");

        try {
            psmt = conn.prepareStatement(sql);
            if (pi.getCurrentPage() == 0) {
                return list;
            }
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;

            psmt.setInt(1, startRow);
            psmt.setInt(2, endRow);

            rset = psmt.executeQuery();

            while (rset.next()){
                list.add(new Report(
                        rset.getInt("REPORT_NO"),
                        rset.getInt("CATEGORY_NO"),
                        rset.getInt("REF_TYPE"),
                        rset.getInt("REF_NO"),
                        rset.getString("BOARD_TITLE"),
                        rset.getString("REPORT_CONTENT"),
                        rset.getString("STATUS")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return list;

    }
    public Report selectReportDetail(Connection conn,int refNo){
        Report r = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReportDetail");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,refNo);

            rset = psmt.executeQuery();

            if(rset.next()){
                r = new Report(
                        rset.getInt("REPORT_NO"),
                        rset.getInt("CATEGORY_NO"),
                        rset.getInt("REF_TYPE"),
                        rset.getInt("REF_NO"),
                        rset.getString("BOARD_TITLE"),
                        rset.getString("REPORT_CONTENT"),
                        rset.getString("STATUS"),
                        rset.getString("BOARD_TYPE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return r;
    }
    public int changeReportStatus(Connection conn, int refNo){
        int result = 0;
        Report r = null;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("changeReportStatus");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,refNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }

}
