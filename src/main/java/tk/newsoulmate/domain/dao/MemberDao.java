package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.MemberGrade;
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
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("loginMember");
        Member m=null;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, memberId);
            psmt.setString(2, memberPwd);
            rset = psmt.executeQuery();
            if (rset.next()) {
                m=mapToLoginMember(rset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return m;
    }

    public Member mapToLoginMember(ResultSet resultSet) throws SQLException {
        int memberNo=resultSet.getInt("MEMBER_NO");
        String memberId = resultSet.getString("MEMBER_ID");
        String memberPwd = resultSet.getString("MEMBER_PWD");
        String memberName = resultSet.getString("MEMBER_NAME");
        String nickname = resultSet.getString("NICKNAME");
        MemberGrade mg= MemberGrade.valueOfNumber(resultSet.getInt("MEMBER_GREED"));
        String email = resultSet.getString("EMAIL");
        String phone = resultSet.getString("PHONE");

        Member m=new Member(memberNo,memberId, memberName, nickname, phone, email,mg);
        if(m.getMemberGrade()==MemberGrade.SHELTER_MANAGER){
            long shelterNo=resultSet.getLong("SHLETER_NO");
            m.setShelterNo(shelterNo);
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
                m = mapToMember(rset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return m;
    }

    private Member mapToMember(ResultSet resultSet) throws SQLException {
        String memberId = resultSet.getString("MEMBER_ID");
        String memberPwd = resultSet.getString("MEMBER_PWD");
        String memberName = resultSet.getString("MEMBER_NAME");
        String nickname = resultSet.getString("NICKNAME");
        MemberGrade mg= MemberGrade.valueOfNumber(resultSet.getInt("MEMBER_GREED"));
        String email = resultSet.getString("EMAIL");
        String phone = resultSet.getString("PHONE");
        Member m=new Member(memberId,memberPwd, memberName, nickname, phone, email);
        return m;
    }


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

 /*   public int updatePassword(Connection conn, String memberId, String password) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        int result = 0;
        String sql = prop.getProperty("updatePwd");

        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, password);
            psmt.setString(2, memberId);
            rset = psmt.executeQuery();
            if (rset.next()) {
                result = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }
        return result;
    }*/

    /*public int updatePwdMember(String memberId, String memberPwd, String updatePwd, Connection conn) {

        int result = 0;

        PreparedStatement psmt = null;

        String sql = prop.getProperty("updatePwdMember");

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, updatePwd);
            psmt.setString(2, memberId);
            psmt.setString(3, memberPwd);

            result = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
        }

        return result;

    }


    public Member selectMember(String userId, Connection conn) {

        Member m = null;

        PreparedStatement psmt = null;

        ResultSet rset = null;

        String sql = prop.getProperty("selectMember");

        *//*
         * SELECT * FROM MEMBER WHERE USER_ID = ?
         *//*

        try {
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, userId);
            rset = psmt.executeQuery();

*//*            if(rset.next()) {
                m = new Member(rset.getInt("MEMBER_NO"), // member 객체에 생성자 매개변수로 넘겨줌
                        rset.getString("MEMBER_ID"),
                        rset.getString("MEMBER_PWD"),
                        rset.getString("MEMBER_NAME"),
                        rset.getString("PHONE"),
                        rset.getString("EMAIL"),
                        rset.getString("NICKNAME"),
                        rset.getString("MEMBER_GRADE"),
                        rset.getString("MEMBER_STATUS")),
                        rset.getDate("ENROLL_DATE");

            }*//*

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(rset);
            JDBCTemplet.close(psmt);
        }

        return m;

    }*/

}