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
            psmt.setLong(4,report.getRefNo());
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
    public ArrayList<Report> selectReportList(Connection conn){
        ArrayList<Report> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReportList");
        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while (rset.next()){
                list.add(new Report(
                        rset.getInt("REPORT_NO"),
                        rset.getString("CATEGORY_NAME"),
                        rset.getInt("REF_TYPE"),
                        rset.getInt("REF_NO"),
                        rset.getString("REPORT_CONTENT")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return list;

    }
    public Report selectReportDetail(Connection conn,int reportNo){
        Report r = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectReportDetail");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,reportNo);
            psmt.setInt(2,reportNo);
            psmt.setInt(3,reportNo);
            rset = psmt.executeQuery();
            if(rset.next()){
                r = new Report(
                        rset.getInt("REPORT_NO"),
                        rset.getLong("REF_NO"),
                        rset.getInt("TYPE_NO")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return r;
    }
    public int changeReportStatus(Connection conn, int reportNo){
        int result = 0;
        Report r = null;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("changeReportStatus");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,reportNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }

}
