package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class VillageDao {
    private static Properties prop=new Properties();

    public VillageDao(){
        try {
            String FileName=ShelterDao.class.getResource("/sql/shelter/Village-Mapper.xml").getPath();
            prop.loadFromXML(new FileInputStream(FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Village> selectVillage(long cityNo, Connection conn){
        ArrayList<Village> vList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectVillage");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setLong(1,cityNo);

            rset = psmt.executeQuery();

            while(rset.next()){
                vList.add(new Village(rset.getLong("CITY_NO"),
                        rset.getLong("VILLAGE_NO"),
                        rset.getString("VILLAGE_NAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return vList;
    }
}
