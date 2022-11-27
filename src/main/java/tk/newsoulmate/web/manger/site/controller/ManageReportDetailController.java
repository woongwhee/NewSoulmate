package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Report;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManageReportDetailController", value = "/reportDetail")
public class ManageReportDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int refNo = Integer.parseInt(request.getParameter("bno"));
        ManageService msService = new ManageService();

        Report r = msService.selectReportDetail(refNo);
        if(r!=null){

            request.setAttribute("r",r);
            request.getRequestDispatcher("views/manager/managerReportDetail.jsp").forward(request,response);
        } else {
            request.getSession().setAttribute("errorMsg", "신고내용확인 실패");
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
