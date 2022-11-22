package tk.newsoulmate.web.shelter.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Request;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "noticeSearch", value = "/noticeSearch")
public class NoticeSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        System.out.println(request.getParameter("request"));
        Request rq=gson.fromJson(request.getParameter("request"), Request.class);
        List<Notice> nList=new ShelterService().getNoticeList(rq);
        request.setAttribute("nList",nList);
        request.getRequestDispatcher("views/shelter/noticeListArea.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
