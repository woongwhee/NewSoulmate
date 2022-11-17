/*
package tk.newsoulmate.web.support.service;

import tk.newsoulmate.domain.dao.SupportDao;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;

public class SupportService {

*/
/*    public int insertPayment(int price, String supportNo, int memberNo) {
        Connection conn = JDBCTemplet.getConnection();
        int result = 0;
        int supportNo = SupportDao.insertSupport(conn);
        if(supportNo == 0) {//가져오지 못함
            JDBCTemplet.rollback(conn);
        }else {
            result = SupportDao.insertSupport(conn, supportNo, price, memberNo);
            if(result>0) {
                JDBCTemplet.commit(conn);
            }else {
                JDBCTemplet.rollback(conn);
            }
        }
        return result;*//*

    }
























*/
/*    public Payment searchPayment(int reserveNo) {
        Connection conn = JDBCTemplate.getConnection();
        Payment p = dao.searchPayment(conn,reserveNo);
        JDBCTemplate.close(conn);
        return p;
    }

    public int deletePayment(int reserveNo) {
        Connection conn = JDBCTemplate.getConnection();
        int result = dao.deletePayment(conn,reserveNo);
        if(result>0) {
            JDBCTemplate.commit(conn);
        }else {
            JDBCTemplate.close(conn);
        }
        JDBCTemplate.close(conn);
        return result;
    }*//*

}
*/
