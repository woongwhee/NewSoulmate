package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.MemberGrade;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ManagerDao {

    private Properties prop = new Properties();

    public ManagerDao() {
        String fileName = MemberDao.class.getResource("/sql/member/Manager-Mapper.xml").getPath();
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

    public ArrayList<Member> selectMember(Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        ArrayList<Member> mList = new ArrayList<Member>();
        String sql = prop.getProperty("manageMember");

        try {
            psmt = conn.prepareStatement(sql);
            rset = psmt.executeQuery();
            while (rset.next()) {
                Member m = new Member();
                m.setMemberNo(rset.getInt("MEMBER_NO"));
                m.setMemberId(rset.getString("MEMBER_ID"));
                m.setMemberName(rset.getString("MEMBER_NAME"));
                m.setEmail(rset.getString("EMAIL"));
                MemberGrade memberGrade = MemberGrade.valueOfNumber(rset.getInt("MEMBER_GRADE"));
                m.setEnrollDate(rset.getDate("ENROLL_DATE"));
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
}
