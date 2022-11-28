package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReportStatus", value = "/reportStatus")
public class ManageReportStatusController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reportNo = Integer.parseInt(request.getParameter("rno"));
        ManageService msService = new ManageService();

        int r = msService.changeReportStatus(reportNo);


        if(r!=0) {
            response.sendRedirect(request.getContextPath()+"/reportList");
        }else{
            request.getSession().setAttribute("errorMsg", "확인 실패");
            response.sendRedirect(request.getContextPath()+"/reportList");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
