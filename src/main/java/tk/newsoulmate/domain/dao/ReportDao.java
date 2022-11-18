package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Report;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

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
    public int insertBoardReport(Connection conn, Report report){

        return 0;
    }

}
