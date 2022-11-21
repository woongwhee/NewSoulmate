package tk.newsoulmate.web.shelter.service;

import tk.newsoulmate.domain.dao.CityDao;
import tk.newsoulmate.domain.dao.NoticeDao;
import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.dao.VillageDao;
import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ShelterService {

    public ArrayList<Shelter> selectList(){
        Connection conn = getConnection();

        ArrayList<Shelter> list = new ShelterDao().selectShelterList(conn);

        close();

        return list;
    }

    public ArrayList<City> selectCity(){
        Connection conn = getConnection();
        ArrayList<City> cList = new CityDao().selectCityAll(conn);
        close();

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
        Connection conn = getConnection();

        ArrayList<Village> vList2 = new VillageDao().selectVillage(cityNo,conn);

        close();

        return vList2;
    }

    public ArrayList<Shelter> selectShelterByVillage(int villageNo){
        Connection conn = getConnection();

        ArrayList<Shelter> sList = new ShelterDao().selectShelterByVillage(villageNo,conn);

        close();

        return sList;
    }
    public ArrayList<Shelter> selectShelterByCity(int cityNo){
        Connection conn = getConnection();

        ArrayList<Shelter> sList = new ShelterDao().selectShelterByCity(cityNo,conn);

        close();

        return sList;
    }

    public Shelter shelterListByShelterNo(long shelterNo){

        Connection conn = getConnection();

        Shelter s = new ShelterDao().shelterListByShelterNo(shelterNo,conn);

        close();

        return s;
    }
    public Notice selectNotice(long dno) {
        Connection conn= getConnection();
        Notice n= new NoticeDao().selectNotice(conn,dno);
        close();
        return n;
    }

    public List<Notice> selectNoticeList() {
        Connection conn=getConnection();
        Notice n=new Notice();
        List<Notice> nList=new NoticeDao().selectNoticeList(conn,n);

        return nList;
    }
}
