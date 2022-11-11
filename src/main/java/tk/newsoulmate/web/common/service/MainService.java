package tk.newsoulmate.web.common.service;

import tk.newsoulmate.domain.dao.NoticeDao;
import tk.newsoulmate.domain.vo.Notice;
import java.sql.Connection;
import java.util.List;

import static tk.newsoulmate.web.common.JDBCTemplet.*;
public class MainService {
    public List<Notice> selectThumbNail(int page){
        Connection conn= getConnection();
        List<Notice>nList=new NoticeDao().selectThumbNail(conn,page);
        close();
        return nList;
    }
    public Notice selectDetail(long dno){
        Connection conn= getConnection();
        Notice n=new NoticeDao().selectNotice(conn,dno);
        close();
        return n;
    }


}
