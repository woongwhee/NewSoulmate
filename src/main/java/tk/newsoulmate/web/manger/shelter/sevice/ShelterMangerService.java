package tk.newsoulmate.web.manger.shelter.sevice;

import tk.newsoulmate.domain.dao.SubscriptionDao;
import tk.newsoulmate.domain.dao.VolunteerDao;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

import static tk.newsoulmate.web.common.JDBCTemplet.close;
import static tk.newsoulmate.web.common.JDBCTemplet.getConnection;

public class ShelterMangerService {
    public int shelterNoAdoptApplyListCount(long shelterNo) {
        Connection conn = getConnection();

        int listCount = new SubscriptionDao().shelterNoAdoptApplyListCount(conn,shelterNo);

        close();
        return listCount;
    }

    public ArrayList<Subscription> shelterNoAdoptApplyList(PageInfo pi, long shelterNo) {
        Connection conn = getConnection();

        ArrayList<Subscription> list = new SubscriptionDao().shelterNoAdoptApplyList(conn, pi ,shelterNo);
        close();
        return list;
    }

    public int volunteerApplyListCount(long shelterNo) {
        Connection conn = getConnection();

        int listCount = new VolunteerDao().volunteerApplyListCount(conn,shelterNo);

        close();
        return listCount;


    }

    public ArrayList<Volunteer> volunteerApplyList(PageInfo pi, long shelterNo) {
        Connection conn = getConnection();

        ArrayList<Volunteer> list = new VolunteerDao().volunteerApplyList(conn, pi ,shelterNo);
        close();
        return list;
    }





}
