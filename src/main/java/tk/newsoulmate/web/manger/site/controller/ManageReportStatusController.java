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
        int refNo = Integer.parseInt(request.getParameter("rno"));
        String status = request.getParameter("status");

        if(status.equals("N")){
            request.getRequestDispatcher("adoptApplyList?page=1").forward(request, response);
        }else{
            ManageService msService = new ManageService();

            int r = msService.changeReportStatus(refNo);


            if(r!=0){
                request.setAttribute("r",r);

                request.getRequestDispatcher("reportList?page=1").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMsg", "확인 실패");
                response.sendRedirect(request.getContextPath());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
