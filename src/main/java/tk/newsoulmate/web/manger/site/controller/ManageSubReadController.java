package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ManageSubReadController", value = "/adoptApplySubRead")
public class ManageSubReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int subNo = Integer.parseInt(request.getParameter("sno"));
        String subRead = request.getParameter("subRead");

        if(subRead.equals("Y")){
            request.getRequestDispatcher("adoptApplyList?page=1").forward(request, response);
        }else{
            ManageService msService = new ManageService();

            int s = msService.changeAdoptApplySubRead(subNo);


            if(s!=0){
                request.setAttribute("s",s);

                request.getRequestDispatcher("adoptApplyList?page=1").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMsg", "확인 실패");
                response.sendRedirect(request.getContextPath());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
