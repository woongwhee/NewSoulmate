package tk.newsoulmate.web.common.Listener;

import tk.newsoulmate.web.common.service.ScheduleService;

import java.util.Calendar;
import java.util.TimerTask;

public class ScrapTimmer extends TimerTask {
    @Override
    public void run()
    {
        new ScheduleService().scrapNotice();
    }
    public long StartTimming(){
        Calendar now = Calendar.getInstance();
        Calendar next = Calendar.getInstance();
        next.set(Calendar.HOUR_OF_DAY, 4);
        next.set(Calendar.MINUTE, 0);
        if (now.get(Calendar.HOUR_OF_DAY) >= 1) {
            next.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR) + 1);
        }
        long time = next.getTimeInMillis() - now.getTimeInMillis();
        return time;
    }

    @Override
    public boolean cancel() {
        return super.cancel();

    }
}
