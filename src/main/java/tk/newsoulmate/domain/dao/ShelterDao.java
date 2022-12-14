package tk.newsoulmate.domain.dao;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import tk.newsoulmate.domain.vo.Shelter;


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
    /**
     *
     * @param cityNo 도시번호
     * @param conn
     * @return 도시번호에 해당하는 마을번호 리스트
     */


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
            close(rset);
            close(psmt);
        }
        return list;
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
            close(rset);
            close(psmt);
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
            close(rset);
            close(psmt);
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
            close(rset);
            close(psmt);
        }
        return s;
    }


    public long shelterNoByName(Connection conn,String animalNo){
        long shelterNo=0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("shelterNoByName");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,animalNo);
            rset = psmt.executeQuery();
            if(rset.next()) {
                shelterNo = rset.getLong("SHELTER_NO");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return shelterNo;

    }

    public Shelter findByShelterNo(long shelterNo, Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectByShelterNo");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, shelterNo);
            rset = psmt.executeQuery();
            if (rset.next()) {
                return new Shelter(rset.getLong("SHELTER_NO"),
                        rset.getString("SHELTER_NAME"),
                        rset.getString("SHELTER_ADDRESS"),
                        rset.getString("SHELTER_LANDLINE"),
                        rset.getString("SHELTER_TEL"),
                        rset.getString("SHELTER_EMAIL"),
                        rset.getLong("CITY_NO"),
                        rset.getLong("VILLAGE_NO"),
                        rset.getLong("TRANSFER_NO"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return null;
    }

    public int updateLatestTransfer(Connection conn, long shelterNo, long transferNo) {
        int result = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("updateLatestTransfer");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, transferNo);
            psmt.setLong(2, shelterNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return result;
    }

    public ArrayList<Shelter> volAbleShelter(Connection conn) {
        ArrayList<Shelter> sList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("volAbleShelter");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while (rset.next()){
                Shelter s = new Shelter();
                s.setShelterNo(rset.getLong("SHELTER_NO"));
                s.setShelterName(rset.getString("SHELTER_NAME"));
                s.setShelterAddress(rset.getString("SHELTER_ADDRESS"));
                s.setShelterLandline(rset.getString("SHELTER_LANDLINE"));

                sList.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(psmt);
        }
        return sList;
    }

    public int update(Connection conn, Shelter updateReq) {
        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("update");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, updateReq.getShelterLandline());
            psmt.setString(2, updateReq.getShelterTel());
            psmt.setString(3, updateReq.getShelterAddress());
            psmt.setString(4, updateReq.getShelterEmail());
            psmt.setLong(5, updateReq.getShelterNo());
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;
    }
}
