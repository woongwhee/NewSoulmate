package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManageAdoptApplyDetailController", value = "/adoptApplyDetail")
public class ManageAdoptApplyDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int subNo = Integer.parseInt(request.getParameter("sNo"));
        ManageService msService = new ManageService();

        Subscription s = msService.selectAdoptApplyListCheck(subNo);
        Notice n=msService.selectNotice(s.getAnimalNo());
        if(s!=null){

            request.setAttribute("s",s);
            request.setAttribute("n",n);
            request.getRequestDispatcher("views/manager/managerAdoptApplyDetail.jsp").forward(request,response);
        } else {
            request.getSession().setAttribute("errorMsg", "입양신청서확인 실패");
            response.sendRedirect(request.getContextPath());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
