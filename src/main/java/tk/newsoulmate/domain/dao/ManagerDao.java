package tk.newsoulmate.domain.dao;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.MemberGrade;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ManagerDao {

    private Properties prop = new Properties();

    public ArrayList<Member> selectMember(Connection conn) {
        PreparedStatement psmt = null;
        ResultSet rset = null;
        ArrayList<Member> list = new ArrayList<Member>();
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
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplet.close(psmt);
            JDBCTemplet.close(rset);
        }
        return list;
    }
}
