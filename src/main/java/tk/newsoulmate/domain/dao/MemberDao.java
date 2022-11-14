package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.*;
import java.sql.*;
import java.util.*;

public class MemberDao {

    private Properties prop = new Properties();

    public MemberDao() {
        String fileName = MemberDao.class.getResource("/sql/member/Member-Mapper.xml").getPath();

        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public int insertMember(Member m, Connection conn) {

        int result = 0;

        PreparedStatement psmt = null;

        String sql = prop.getProperty("insertMember");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, m.getMemberId());
            psmt.setString(2, m.getMemberPwd());
            psmt.setString(3, m.getMemberName());
            psmt.setString(4, m.getNickName());
            psmt.setString(5, m.getPhone());
            psmt.setString(6, m.getEmail());
            result = psmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
        }

        return result;

    }

    public Member loginMember(String memberId, String memberPwd, Connection conn) {

        Member m = null;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("loginMember");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, memberId);
            psmt.setString(2, memberPwd);
            rset = psmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return m;
    }



    public int idCheck(Connection conn, String checkId) {

        int count = 0;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("idCheck");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, checkId);

            rset = psmt.executeQuery();

            if(rset.next()) {
                count = rset.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return count;


    }


    public int nicknameCheck(Connection conn, String checkNickname) {

        int count = 0;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("nicknameCheck");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, checkNickname);

            rset = psmt.executeQuery();

            if(rset.next()) {
                count = rset.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return count;


    }


    public Member findId(Connection conn, String memberName, String Email) {

        PreparedStatement psmt = null;
        ResultSet rset = null;
        Member m = null;

        String sql = prop.getProperty("findId");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, memberName);
            psmt.setString(2, Email);

            rset = psmt.executeQuery();

            if(rset.next()) {
                m = new Member();
                m.setMemberId(rset.getString("memberId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return m;
    }

/*
    public Member findPwd(Connection conn, String memberName, String memberId, String email) {

        PreparedStatement psmt = null;

        ResultSet rset = null;

        Member m = null;

        String sql = prop.getProperty("searchPwd");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, memberId);
            psmt.setString(2, email);
            rset = psmt.executeQuery();
            if (rset.next()) {
                m = new Member();
                m.setMemberPwd(rset.getString("memberpwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return m;
    }
*/


}