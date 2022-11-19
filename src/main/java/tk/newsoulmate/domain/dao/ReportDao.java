package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Report;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
