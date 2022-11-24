package tk.newsoulmate.web.manger.shelter.sevice;

import tk.newsoulmate.domain.dao.SubscriptionDao;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.util.ArrayList;

public class ShelterMangerService {
    public ArrayList<Subscription> subscriptionList() {
        Connection conn = JDBCTemplet.getConnection();
        ArrayList<Subscription> scriptList = new SubscriptionDao().subscriptionList(conn);

        JDBCTemplet.close();

        return scriptList;
    }
}
