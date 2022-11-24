package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Subscription;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.*;


public class SubscriptionDao {

    private Properties prop = new Properties();

    public SubscriptionDao(){
        try {
            prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/adopt/Subscription-Mapper.xml").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int insertSubscription(Connection conn, Subscription sb){
        int result = 0;
        PreparedStatement psmt = null;
        String sql = prop.getProperty("adoptApplyInsert");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,sb.getMemberNo());
            psmt.setLong(2,sb.getShelterNo());
            psmt.setLong(3,sb.getAnimalNo());
            psmt.setString(4,sb.getTelNum());
            psmt.setString(5,sb.getName());
            psmt.setString(6,sb.getGender());
            psmt.setString(7,sb.getAdoptReason());
            psmt.setString(8,sb.getFamilyAgreement(

            ));
            psmt.setString(9,sb.getWhenSick());
            psmt.setString(10,sb.getBigDuty());
            psmt.setDate(11,sb.getWishDate());
            psmt.setString(12,sb.getSubRead());

            result = psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;
    }
    public int selectAdoptApplyListCount(Connection conn){
        int listCount = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAdoptApplyListCount");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            if(rset.next()){
                listCount = rset.getInt("cnt");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return listCount;

    }

    public ArrayList<Subscription> selectAdoptApplyList(Connection conn, PageInfo pi){
        ArrayList<Subscription> list = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAdoptApplyList");

        try {
            psmt = conn.prepareStatement(sql);
            if (pi.getCurrentPage() == 0) {
                return list;
            }
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;

            psmt.setInt(1,startRow);
            psmt.setInt(2,endRow);

            rset = psmt.executeQuery();

            while (rset.next()){
                list.add(new Subscription(
                        rset.getInt("SUB_NO")
                        ,rset.getLong("ANIMAL_ID")
                        ,rset.getString("MEMBER_ID")
                        ,rset.getString("NAME")
                        ,rset.getString("TEL_NUM")
                        ,rset.getDate("SUB_DATE")
                        ,rset.getString("SUB_READ")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return list;
    }
    public int changeAdoptApplySubRead(Connection conn, int subNo){
        int result = 0;
        Subscription s = null;
        PreparedStatement psmt = null;

        String sql = prop.getProperty("changeAdoptApplySubRead");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,subNo);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(psmt);
        }
        return result;

    }
    public Subscription selectAdoptApplyDetail(Connection conn , int subNo){

        Subscription s = null;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAdoptApplyDetail");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setInt(1,subNo);

            rset = psmt.executeQuery();

            if(rset.next()){
                s = new Subscription(
                        rset.getInt("SUB_NO"),
                        rset.getInt("MEMBER_NO"),
                        rset.getLong("SHELTER_NO"),
                        rset.getLong("ANIMAL_ID"),
                        rset.getString("TEL_NUM"),
                        rset.getString("NAME"),
                        rset.getString("GENDER"),
                        rset.getString("ADOPT_REASON"),
                        rset.getString("FAMILY_AGREEMENT"),
                        rset.getString("WHEN_SICK"),
                        rset.getString("BIG_DUTY"),
                        rset.getDate("WISH_DATE"),
                        rset.getString("SUB_READ"),
                        rset.getDate("SUB_DATE"))
                        ;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(psmt);
        }
        return s;


    }



















}
