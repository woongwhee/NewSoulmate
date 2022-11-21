package tk.newsoulmate.web.manger.site.service;

import tk.newsoulmate.domain.dao.ManagerDao;
import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.*;

public class ManageMemberService {

    private ManagerDao managerDao;

    public ArrayList<Member> selectManageMember() {
        Connection conn = getConnection();
        ArrayList<Member> mList = managerDao.selectManageMember(conn);
        close();
        return mList;
    }


}
