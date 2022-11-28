package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Report;
import tk.newsoulmate.web.manger.site.service.ManageService;
import tk.newsoulmate.web.myPage.service.MyPageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ManageReportList", value = "/reportList")
public class ManageReportListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
            ArrayList<Report> rList = new ManageService().selectReportList();
            request.setAttribute("rList", rList);
        request.getRequestDispatcher("/views/manager/managerReportList.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
