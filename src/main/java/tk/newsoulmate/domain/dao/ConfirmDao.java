package tk.newsoulmate.domain.dao;


import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ConfirmDao {
    private static Properties prop=new Properties();
    public ConfirmDao(){
        try {
            String FileName=ShelterDao.class.getResource("/sql/confirm/Confirm-Mapper.xml").getPath();
            prop.loadFromXML(new FileInputStream(FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectConfirmNo(Connection conn) {
        PreparedStatement psmt=null;
        ResultSet rset=null;
        String sql=prop.getProperty("selectConfirmNo");
        int confirmNo=0;
        try {
            psmt=conn.prepareStatement(sql);
            rset=psmt.executeQuery();
            if (rset.next()){
                confirmNo=rset.getInt("nextval");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            close(rset,psmt);
        }
        return confirmNo;
    }
    public int insertConfirm(Connection conn,int confirmNo,String confirmCode){
        PreparedStatement psmt=null;
        String sql=prop.getProperty("insertConfirm");
        int result=0;
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,confirmNo);
            psmt.setString(2,confirmCode);
            result=psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(psmt);
        }
        return result;



    }

    public int copareConfirm(Connection conn,int confirmNo,String confirmCode){
        PreparedStatement psmt=null;
        ResultSet rset=null;
        String sql=prop.getProperty("compareConfirm");

        int result=0;
        try {
            psmt=conn.prepareStatement(sql);
            psmt.setInt(1,confirmNo);
            psmt.setString(2,confirmCode);
            rset=psmt.executeQuery();
            if (rset.next()){
                result=rset.getInt("result");}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset,psmt);
        }
        return result;

    }
}