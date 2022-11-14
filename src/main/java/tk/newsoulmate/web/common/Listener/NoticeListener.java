package tk.newsoulmate.web.common.Listener;

import tk.newsoulmate.web.common.APIKeys;
import tk.newsoulmate.web.schedule.service.ScrapTimmer;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.util.Timer;

@WebListener
public class NoticeListener implements ServletContextListener {

    public NoticeListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //new APIKeys();
        Timer m = new Timer();
        ScrapTimmer task = new ScrapTimmer();
        long time = task.StartTimming();
        long oneDay = 1000 * 60 * 60 * 24;
        //새벽 1시마다 데이터를 긁어오는 함수
       // m.schedule(task, time, oneDay);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

}
