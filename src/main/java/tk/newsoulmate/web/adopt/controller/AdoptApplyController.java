package tk.newsoulmate.web.adopt.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdoptApplyController", value = "/adoptApply")
public class AdoptApplyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dno_=request.getParameter("dno");
        if(dno_!=null&&!dno_.equals("")){
            request.setAttribute("dno",dno_);
        }
        request.getRequestDispatcher("/views/adopt/adoptApply.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
