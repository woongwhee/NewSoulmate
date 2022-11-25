package tk.newsoulmate.web.shelter.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import tk.newsoulmate.domain.vo.Request;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "noticeCount", value = "/noticeCount")
public class NoticeCountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        System.out.println(request.getParameter("request"));
        Request rq=gson.fromJson(request.getParameter("request"), Request.class);
        rq.setNumberOfRows(1);
        Request.setValid(rq);
        rq.setBgndate(Calendar.getInstance().getTime());
        int totalCount=new ShelterService().getNoticeCount(rq);
        JsonObject json =new JsonObject();
        json.addProperty("totalCount",totalCount);
        System.out.println(totalCount);
        response.setContentType("application/json; charset=UTF-8");//gson으로 보낼꺼면 이렇게!
        gson.toJson(json,response.getWriter());
    }
}
