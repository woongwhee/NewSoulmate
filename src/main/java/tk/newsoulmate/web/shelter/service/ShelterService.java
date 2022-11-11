package tk.newsoulmate.web.shelter.service;

import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

public class ShelterService {

    public ArrayList<Shelter> selectList(){
        Connection conn = JDBCTemplet.getConnection();

        ArrayList<Shelter> list = new ShelterDao().selectShelterList(conn);

        JDBCTemplet.close();

        return list;
    }

    public ArrayList<City> selectCity(){
        Connection conn = JDBCTemplet.getConnection();
        ArrayList<City> cList = new ShelterDao().selectCityAll(conn);

        JDBCTemplet.close();

        return cList;
    }
//    public ArrayList<Village> selectVillageAll(){
//
//        Connection conn = JDBCTemplet.getConnection();
//        ArrayList<Village> vList = new ShelterDao().selectVillageAll(conn);
//
//        JDBCTemplet.close();
//
//        return vList;
//    }

    public ArrayList<Village> selectVillage(long cityNo){
        Connection conn = JDBCTemplet.getConnection();

        ArrayList<Village> vList2 = new ShelterDao().selectVillage(cityNo,conn);

        JDBCTemplet.close();

        return vList2;
    }

    public ArrayList<Shelter> selectShelterByVillage(int villageNo){
        Connection conn = JDBCTemplet.getConnection();

        ArrayList<Shelter> sList = new ShelterDao().selectShelterByVillage(villageNo,conn);

        JDBCTemplet.close();

        return sList;
    }
    public ArrayList<Shelter> selectShelterByCity(int cityNo){
        Connection conn = JDBCTemplet.getConnection();

        ArrayList<Shelter> sList = new ShelterDao().selectShelterByCity(cityNo,conn);

        JDBCTemplet.close();

        return sList;
    }

    public Shelter shelterListByShelterNo(long shelterNo){

        Connection conn = JDBCTemplet.getConnection();

        Shelter s = new ShelterDao().shelterListByShelterNo(shelterNo,conn);

        JDBCTemplet.close();

        return s;
    }
}
