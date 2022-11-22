package tk.newsoulmate.web.shelter.controller;

import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "noticeList", value = "/noticeList")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<City> cList=new ShelterService().selectCity();
        request.setAttribute("cList",cList);
//        List<Notice> nList=new ShelterService().selectNoticeList();
        request.getRequestDispatcher("/views/shelter/noticeListView.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
