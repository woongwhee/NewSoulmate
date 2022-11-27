package tk.newsoulmate.web.schedule.service;

import org.junit.jupiter.api.Test;
import tk.newsoulmate.domain.vo.Request;
import tk.newsoulmate.web.common.APIKeys;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleServiceTest {

    @Test
    void getTotalCount() throws ParseException {
        new APIKeys();
        ScheduleService ss=new ScheduleService();
        Request request=new Request();
        System.out.println();
        DateFormat df=Request.getUrlDate();
        Date[] day={new Date(df.parse("20221114").getTime()),new Date(df.parse("20221128").getTime()),new Date(df.parse("20221127").getTime()), new Date(df.parse("20221126").getTime())};
        request.setNumberOfRows(1);
        request.setPageNo(1);
        for (Date d: day) {
            request.setBgndate(d);
            int result=ss.getTotalCount(request);
            System.out.println(request.toUrl());
            System.out.println(result);

        }

    }
    @Test
    void testRequest() {
        Request r=new Request();
        r.setValid();
        System.out.println(r.getBgndate());
    }
}