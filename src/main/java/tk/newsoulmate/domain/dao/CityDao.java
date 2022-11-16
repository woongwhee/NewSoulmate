package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class CityDao {
    private static Properties prop=new Properties();

    public CityDao(){
        try {
            String FileName=ShelterDao.class.getResource("/sql/shelter/City-Mapper.xml").getPath();
            prop.loadFromXML(new FileInputStream(FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<City> selectCityAll(Connection conn){
        ArrayList<City> cList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectCityAll");
        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while(rset.next()){
                cList.add(new City(rset.getLong("CITY_NO"),
                        rset.getString("CITY_NAME")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return cList;
    }
}
