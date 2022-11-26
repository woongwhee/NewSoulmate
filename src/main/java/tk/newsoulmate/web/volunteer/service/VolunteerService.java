package tk.newsoulmate.web.volunteer.service;

import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.dao.VolunteerDao;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

public class VolunteerService {
    public ArrayList<Shelter> volAbleShelter() {
        Connection conn = JDBCTemplet.getConnection();
        ArrayList<Shelter> sList = new ShelterDao().volAbleShelter(conn);
        JDBCTemplet.close();

        return sList;
    }

    public int volApplyInset(Volunteer vol) {
        Connection conn = JDBCTemplet.getConnection();
        int result = new VolunteerDao().volApplyInsert(conn,vol);
        JDBCTemplet.close();

        if(result>0){
            JDBCTemplet.commit();
        }else{
            JDBCTemplet.rollback();
        }
        return result;
    }
}
