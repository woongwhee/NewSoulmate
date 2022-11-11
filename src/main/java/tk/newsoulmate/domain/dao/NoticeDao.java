package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class NoticeDao {

    private static Properties prop=new Properties();
    public NoticeDao(){
        
        String FilePath=NoticeDao.class.getResource("/sql/notice/Notice-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(FilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Notice> selectThumbNail(Connection conn, int page) {
        List<Notice> nList=new ArrayList<Notice>(20);
        int start=page*20+1;
        int end=page*20+20;
        String sql= prop.getProperty("selectThumbNail");
        PreparedStatement psmt=null;
        ResultSet rset=null;
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,start);
            psmt.setInt(2,end);
            rset=psmt.executeQuery();
            Notice n=null;
            while(rset.next()){
                n=new Notice();
                n.setDesertionNo(rset.getLong("desertionNo"));
                n.setFilename(rset.getString("filename"));
                nList.add(n);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
            close(rset);
        }
    return nList;
    }

    public Notice selectNotice(Connection conn, long nno){
        Notice n=null;
        String sql=prop.getProperty("selectNotice");
        PreparedStatement psmt=null;
        ResultSet rset=null;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일");
            psmt=conn.prepareStatement(sql);
            psmt.setLong(1,nno);
            rset=psmt.executeQuery();
            if(rset!=null){
                n=new Notice(
                        rset.getLong("desertionNo"),
                        rset.getString("filename"),
                        sdf.format(rset.getDate("happenDt")),
                        rset.getString("happenPlace"),
                        rset.getString("kindCd"),
                        rset.getString("colorCd"),
                        rset.getString("age"),
                        rset.getString("weight"),
                        rset.getString("noticeNo"),
                        sdf.format(rset.getDate("noticeSdt")),
                        sdf.format(rset.getDate("noticeEdt")),
                        rset.getString("popfile"),
                        rset.getString("processState"),
                        rset.getString("sexCd"),
                        rset.getString("neuterYn"),
                        rset.getString("specialMark"),
                        rset.getString("careNm"),
                        rset.getString("careTel"),
                        rset.getString("careAddr"),
                        rset.getString("orgNm"),
                        rset.getString("chargeNm"),
                       rset.getString("officetel")
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close();
        }

        return n;
    }



    /**
     * 공고문 리스트를 저장하는 메소드
     * @param conn
     * @param Nlist
     * @return
     */
    public int insertNotice(Connection conn, List<Notice> Nlist){
        PreparedStatement psmt=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String sql=prop.getProperty("insertNotice");
        int result=0;
        try {
                psmt=conn.prepareStatement(sql);

                for(Notice n:Nlist){
                    Date hd=new Date(sdf.parse(n.getHappenDt()).getTime());
                    Date sd=new Date(sdf.parse(n.getNoticeSdt()).getTime());
                    Date ed=new Date(sdf.parse(n.getNoticeEdt()).getTime());
                    psmt.setLong(1,n.getDesertionNo());
                    psmt.setString(2,n.getFilename());
                    psmt.setDate(3,hd);
                    psmt.setString(4,n.getHappenPlace());
                    psmt.setString(5,n.getKindCd());
                    psmt.setString(6,n.getColor());
                    psmt.setString(7,n.getAge());
                    psmt.setString(8,n.getWeight());
                    psmt.setString(9,n.getNoticeNo());
                    psmt.setDate(10,sd);
                    psmt.setDate(11,ed);
                    psmt.setString(12,n.getPopfile());
                    psmt.setString(13,n.getProcessState());
                    psmt.setString(14,n.getSexCd());
                    psmt.setString(15,n.getNeuterYn());
                    psmt.setString(16,n.getSpecialMark());
                    psmt.setString(17,n.getCareNm());
                    psmt.setString(18,n.getCareTel());
                    psmt.setString(19,n.getCareAddr());
                    psmt.setString(20,n.getOrgNm());
                    psmt.setString(21,n.getChargeNm());
                    psmt.setString(22,n.getOfficetel());
                    result+=psmt.executeUpdate();
                    System.out.println(result);
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
     return result;


    }
    /**
     * ANimal테이블을 비워주는 메소드
     * @param conn
     * @return
     */
    public int trunkNotice(Connection conn) {
        PreparedStatement psmt=null;
        String sql=prop.getProperty("truncNotice");
        int result=0;
        try {
            psmt=conn.prepareStatement(sql);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }
}
