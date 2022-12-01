package tk.newsoulmate.web.common.Listener;

import tk.newsoulmate.web.common.APIKeys;
import tk.newsoulmate.web.schedule.service.ScrapTimmer;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.util.Timer;

@WebListener
public class NoticeListener implements ServletContextListener {
    private ScrapTimmer task;
    private Timer m;
    public NoticeListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new APIKeys();
        m = new Timer();
        task = new ScrapTimmer();
        //새벽 1시까지 남은 시간을 반환한는 함수
        long time = task.StartTimming();
        long oneDay = 1000 * 60 * 60 * 24;
        //특정한 시간에 원하는 작업을 수행하고자 할 때 사용하는 메소드
        m.schedule(task,time, oneDay);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        task.cancel();
        m.cancel();
    }

}
