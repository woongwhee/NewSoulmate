package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.type.MemberGrade;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static tk.newsoulmate.web.common.JDBCTemplet.close;

public class ManagerDao {

    private Properties prop = new Properties();

    public ManagerDao() {
        String fileName = MemberDao.class.getResource("/sql/manager/Manager-Mapper.xml").getPath();
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Member> selectManageMember(Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        ArrayList<Member> mList = new ArrayList<Member>();
        String sql = prop.getProperty("manageMember2");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while (rset.next()) {
                Member m = new Member();
                Shelter s = new Shelter();
                m.setMemberNo(rset.getInt("MEMBER_NO"));
                m.setMemberId(rset.getString("MEMBER_ID"));
                m.setMemberName(rset.getString("MEMBER_NAME"));
                m.setEmail(rset.getString("EMAIL"));
                m.setNickName(rset.getString("NICKNAME"));
                MemberGrade memberGrade = MemberGrade.valueOfNumber(rset.getInt("MEMBER_GRADE"));
                m.setMemberGrade(memberGrade);
                m.setShelterNo(rset.getLong("SHELTER_NO"));
                s.setShelterName(rset.getString("SHELTER_NAME"));
                m.setEnrollDate(rset.getDate("ENROLL_DATE"));
                m.setResentConnection(rset.getDate("RESENT_CONNECTION"));
                mList.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
            JDBCTemplet.close(rset);
        }
        return mList;
    }

    public int selectCountMember(Connection conn) {
        int countMember = 0;
        PreparedStatement psmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("countMember");
        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            if (rset.next()) {
                countMember = rset.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(psmt);
        }
        return countMember;
    }






}
