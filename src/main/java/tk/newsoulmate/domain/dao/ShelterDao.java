package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class ShelterDao {
    private static Properties prop=new Properties();

    public ShelterDao(){
        try {
            String FileName=ShelterDao.class.getResource("/sql/shelter/Shelter-Mapper.xml").getPath();
            prop.loadFromXML(new FileInputStream(FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Shelter> selectShelterList(Connection conn){

        // select 문 resultSet
        ArrayList<Shelter> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectShelterList");

        try {
            psmt = conn.prepareStatement(sql);

            rset = psmt.executeQuery();

            while(rset.next()){
                list.add(new Shelter(rset.getLong("SHELTER_NO"),
                        rset.getString("SHELTER_NAME"),
                        rset.getString("SHELTER_ADDRESS"),
                        rset.getString("SHELTER_LANDLINE"),
                        rset.getLong("CITY_NO"),
                        rset.getLong("VILLAGE_NO")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return list;
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

//    public ArrayList<Village> selectVillageAll(Connection conn){
//        ArrayList<Village> vList = new ArrayList<>();
//        PreparedStatement psmt = null;
//        ResultSet rset = null;
//        String sql = prop.getProperty("selectVillageAll");
//
//        try {
//            psmt = conn.prepareStatement(sql);
//
//            rset = psmt.executeQuery();
//            while(rset.next()){
//                vList.add(new Village(rset.getLong("VILLAGE_NO"),
//                        rset.getLong("CITY_NO"),
//                        rset.getString("VILLAGE_NAME")));
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            JDBCTemplet.close(rset);
//            JDBCTemplet.close(psmt);
//        }
//        return vList;
//    }

    /**
     *
     * @param cityNo 도시번호
     * @param conn
     * @return 도시번호에 해당하는 마을번호 리스트
     */
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


    /**
     * 시군구번호로 보호소 리스트를 조회하는 용도
     * @param villageNo
     * @param conn
     * @return shelterName,shelterNo가 담긴 shelterList
     */
    public ArrayList<Shelter> selectShelterByVillage(int villageNo, Connection conn){
        ArrayList<Shelter> sList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("ShelterListByVillage");


        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,villageNo);
            rset = psmt.executeQuery();
            while(rset.next()) {
                sList.add(new Shelter(rset.getLong("SHELTER_NO"),
                                      rset.getString("SHELTER_NAME"),
                                      rset.getString("SHELTER_ADDRESS"),
                                      rset.getString("SHELTER_LANDLINE"),
                                      rset.getLong("CITY_NO"),
                                      rset.getLong("VILLAGE_NO")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return sList;
    }
    public ArrayList<Shelter> selectShelterByCity(int cityNo, Connection conn) {
        ArrayList<Shelter> sList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("ShelterListByCity");


        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, cityNo);
            rset = psmt.executeQuery();
            while (rset.next()) {
                sList.add(new Shelter(rset.getLong("SHELTER_NO"),
                        rset.getString("SHELTER_NAME"),
                        rset.getString("SHELTER_ADDRESS"),
                        rset.getString("SHELTER_LANDLINE"),
                        rset.getLong("CITY_NO"),
                        rset.getLong("VILLAGE_NO")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return sList;

    }

    public Shelter shelterListByShelterNo(long shelterNo, Connection conn){
        Shelter s = new Shelter();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("shelterListByShelterNo");


        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, shelterNo);
            rset = psmt.executeQuery();

                while (rset.next()) {
                s=(new Shelter(rset.getLong("SHELTER_NO"),
                        rset.getString("SHELTER_NAME"),
                        rset.getString("SHELTER_ADDRESS"),
                        rset.getString("SHELTER_LANDLINE"),
                        rset.getLong("CITY_NO"),
                        rset.getLong("VILLAGE_NO")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return s;
    }


}
