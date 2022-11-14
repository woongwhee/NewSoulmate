package tk.newsoulmate.web.schedule.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tk.newsoulmate.domain.dao.NoticeDao;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Request;
import tk.newsoulmate.domain.vo.response.Body;
import tk.newsoulmate.domain.vo.response.ResponseMapper;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleService {
    public int scrapNotice() {
        int result=0;
        System.out.println(new Date()+"공고문업데이트 시작");
        Request request = new Request();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 0);
        request.setBgndate(cal.getTime());
        request.setEnddate(cal2.getTime());
        request.setPageNo(1);
        request.setNumberOfRows(1);
        int count=getTotalCount(request);
        request.setNumberOfRows(200);
        Connection conn=JDBCTemplet.getConnection();
        Boolean tresult=new NoticeDao().trunkNotice(conn);
        JDBCTemplet.commit();
        System.out.println(tresult);
        JDBCTemplet.close();

        for (int i = 1; i <= count/300+1; i++) {
            request.setPageNo(i);
            List<Notice> list=getNoticeList(request);
            Thread tread=new Thread(new Runnable() {
                @Override
                public void run() {
                    Connection conn= JDBCTemplet.getConnection();
                int result=new NoticeDao().insertNotice(conn,list);
                if(result>0){
                    JDBCTemplet.commit();
                }else{
                    JDBCTemplet.rollback();
                }
                JDBCTemplet.close();
                }
            });
            tread.run();
        }
        System.out.println(new Date()+"공고문업데이트 종료");
        return  result;
    }

    private int getTotalCount(Request request) {
        URL url = request.toUrl();
        System.out.println(url.toString());
        ResponseMapper responseMapper = null;
        List<Notice> Nlist = new ArrayList<>();
        int totalCount = 0;
        try {
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-type", "application/json");
            System.out.println(httpConn.getResponseCode());
            if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                responseMapper = gson.fromJson(new InputStreamReader(httpConn.getInputStream(), "UTF-8"), ResponseMapper.class);
                Body b = responseMapper.getResponse().getBody();
                totalCount = b.getTotalCount();
                httpConn.disconnect();
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return totalCount;
    }
    private  List<Notice> getNoticeList(Request request){
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
                System.out.println(Nlist.get(0));
            }
            httpConn.disconnect();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return Nlist;

    }
}
