package tk.newsoulmate.web.shelter.service;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tk.newsoulmate.domain.dao.*;
import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.domain.vo.response.ResponseMapper;
import tk.newsoulmate.domain.vo.type.BoardType;

public class ShelterService {

    public ArrayList<Shelter> selectList() {
        Connection conn = getConnection();

        ArrayList<Shelter> list = new ShelterDao().selectShelterList(conn);

        close();

        return list;
    }

    public ArrayList<City> selectCity() {
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

    public ArrayList<Village> selectVillage(long cityNo) {
        Connection conn = getConnection();

        ArrayList<Village> vList2 = new VillageDao().selectVillage(cityNo, conn);

        close();

        return vList2;
    }

    public ArrayList<Shelter> selectShelterByVillage(int villageNo) {
        Connection conn = getConnection();

        ArrayList<Shelter> sList = new ShelterDao().selectShelterByVillage(villageNo, conn);

        close();

        return sList;
    }

    public ArrayList<Shelter> selectShelterByCity(int cityNo) {
        Connection conn = getConnection();

        ArrayList<Shelter> sList = new ShelterDao().selectShelterByCity(cityNo, conn);

        close();

        return sList;
    }

    public Shelter shelterListByShelterNo(long shelterNo) {

        Connection conn = getConnection();

        Shelter s = new ShelterDao().shelterListByShelterNo(shelterNo, conn);

        close();

        return s;
    }

    public Notice selectNotice(long dno) {
        Connection conn = getConnection();
        Notice n = new NoticeDao().selectNotice(conn, dno);
        close();
        return n;
    }

    public List<Notice> selectNoticeList() {
        Connection conn = getConnection();
        Notice n = new Notice();
        List<Notice> nList = new NoticeDao().selectNoticeList(conn, n);
        close();
        return nList;
    }

    public List<Notice> getNoticeList(Request request) {
        URL url = request.toUrl();
        System.out.println(url.toString());
        ResponseMapper responseMapper = null;
        List<Notice> nlist = null;
        try {
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-type", "application/json");
            if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                responseMapper = gson.fromJson(new InputStreamReader(httpConn.getInputStream(), "UTF-8"),
                        ResponseMapper.class);
                nlist = responseMapper.getResponse().getBody().getItems().getItem();
            }
            httpConn.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nlist;

    }

    public int getNoticeCount(Request request) {
        URL url = request.toUrl();
        ResponseMapper responseMapper = null;
        int count = 0;
        try {
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-type", "application/json");
            if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                responseMapper = gson.fromJson(new InputStreamReader(httpConn.getInputStream(), "UTF-8"),
                        ResponseMapper.class);
                count = responseMapper.getResponse().getBody().getTotalCount();
            }
            httpConn.disconnect();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return count;

    }

    public Shelter findShelter(long shelterNo) {
        Connection conn = getConnection();
        Shelter s = new ShelterDao().findByShelterNo(shelterNo, conn);
        close();
        return s;
    }

    public int updateLatestTransfer(long shelterNo, long supportNo) {
        Connection conn = getConnection();
        // SupportNo 기준으로 Transfer를 가져오기
        Transfer latestTransfer = new TransferDao().findBySupportNo(conn, supportNo);
        int result = new ShelterDao().updateLatestTransfer(conn, shelterNo, latestTransfer.getTransferNo());
        if (result > 0) {
            commit();
        } else {
            rollback();
        }
        close();
        return result;
    }



    public List<Reply> selectNoticeReply(long dno) {
        Connection conn = getConnection();
        List<Reply> rList = new ReplyDao().selectNoticeReply(conn, dno);
        new AttachmentDao().selectReplyAttachment(conn, rList);
        close();
        return rList;
    }

    public List<Category> selectCategoryList() {
        Connection conn = getConnection();
        List<Category> cList = new CategoryDao().selectCategoryList(conn, BoardType.REPORT);
        close();
        return cList;
    }



    public Transfer findTransfer(long transferNo) {
        Connection conn = getConnection();
        Transfer transfer = new TransferDao().findByTransferNo(conn, transferNo);
        close();
        return transfer;
    }

    public List<Support> findAllTransfer() {
        Connection conn = getConnection();
        List<Support> transfers = new TransferDao().findAll(conn);
        close();
        return transfers;
    }



}
