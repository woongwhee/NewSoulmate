package tk.newsoulmate.web.manger.site.service;

import tk.newsoulmate.domain.dao.ManagerDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

public class ManageMemberService {

    private ManagerDao managerDao;

    public ArrayList<Member> selectMember() {
        Connection conn = JDBCTemplet.getConnection();
        ArrayList<Member> memberList = managerDao.selectMember(conn);
        JDBCTemplet.close();
        return memberList;
    }
}
