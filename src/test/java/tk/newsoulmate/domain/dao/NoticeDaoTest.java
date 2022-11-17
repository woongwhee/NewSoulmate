package tk.newsoulmate.domain.dao;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.common.JDBCTemplet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tk.newsoulmate.web.common.JDBCTemplet.*;

class NoticeDaoTest {

   @Test
   void 슬라이드이미지테스트() {
       Connection conn = getTestConnection();
       List<Notice> list = new NoticeDao().selectThumbNail(conn, 0);
       assertEquals(20, list.size());
   }
    @Test
    void noticeSelect(){
        long dno=441382202200974L;
        Connection conn=JDBCTemplet.getTestConnection();
        Notice n=new NoticeDao().selectNotice(conn,dno);
        System.out.println(n);
        assertEquals(dno,n.getDesertionNo());

    }
}