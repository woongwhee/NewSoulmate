package tk.newsoulmate.web.manger.site.service;

import tk.newsoulmate.domain.dao.ManagerDao;
import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.close;
import static tk.newsoulmate.web.common.JDBCTemplet.getConnection;

public class ManageMemberService {

    private ManagerDao managerDao;

    public ArrayList<Member> selectMember() {
        Connection conn = JDBCTemplet.getConnection();
        ArrayList<Member> mList = managerDao.selectMember(conn);
        JDBCTemplet.close();
        return mList;
    }


}
