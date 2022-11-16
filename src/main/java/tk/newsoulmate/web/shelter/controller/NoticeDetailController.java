package tk.newsoulmate.web.shelter.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "noticeDetail", value = "/noticeDetail")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dno_=request.getParameter("dno");
        if(dno_==null||dno_.equals("")) {
            request.getSession().setAttribute("errorMsg", "잘못된접근입니다.");
            response.sendRedirect("/Main");
            return;
        }
        long dno=Long.parseLong(dno_);
        Notice n= new ShelterService().selectNotice(dno);
        request.setAttribute("n",n);
        request.getRequestDispatcher("/views/shelter/noticeDetailView.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
