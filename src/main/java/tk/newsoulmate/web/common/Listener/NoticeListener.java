package tk.newsoulmate.web.common.Listener;

import tk.newsoulmate.web.common.APIKeys;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.util.Timer;


@WebListener
public class NoticeListener implements ServletContextListener {
    private ScrapTimmer task;
     private Timer m;
    final private static long oneDay=1000 * 60 * 60 * 24;
    public NoticeListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //
        new APIKeys();
        m = new Timer();
        task = new ScrapTimmer();
        long time = task.StartTimming();
//        새벽 1시마다 전날 업데이트된 데이터를 긁어오는 함수
        m.schedule(task,time, oneDay);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        task.cancel();
        m.cancel();
    }

}
