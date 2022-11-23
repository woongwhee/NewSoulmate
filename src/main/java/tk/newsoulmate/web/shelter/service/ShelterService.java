package tk.newsoulmate.web.shelter.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tk.newsoulmate.domain.dao.CityDao;
import tk.newsoulmate.domain.dao.NoticeDao;
import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.dao.VillageDao;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.vo.response.ResponseMapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

    public  List<Notice> getNoticeList(Request request){
        URL url = request.toUrl();
        System.out.println(url.toString());
        ResponseMapper responseMapper = null;
        List<Notice> Nlist=new ArrayList<>();
        try {
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-type", "application/json");
            System.out.println(httpConn.getResponseCode());
            if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                responseMapper = gson.fromJson(new InputStreamReader(httpConn.getInputStream(), "UTF-8"), ResponseMapper.class);
                Nlist = responseMapper.getResponse().getBody().getItems().getItem();
            }
            httpConn.disconnect();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Nlist);
        return Nlist;

    }

	public Shelter find(long shelterNo) {
        Connection conn = getConnection();
        Shelter s = new ShelterDao().findByShelterNo(shelterNo,conn);
        close();
        return s;
	}

}
