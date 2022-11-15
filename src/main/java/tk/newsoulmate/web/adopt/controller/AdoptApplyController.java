package tk.newsoulmate.web.adopt.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdoptApplyController", value = "/adoptApply")
public class AdoptApplyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 입양 동물 상세페이지에서 넘오오는 경우 dno를 가지고 넘어옴 , 메인페이지에서 넘어올 경우 dno 존재하지 않음
        String dno_=request.getParameter("dno");

        if(request.getParameterMap().containsKey("dno")){ // 만약 dno가 존재 한다면
            request.setAttribute("dno",dno_);
        }

        request.getRequestDispatcher("/views/adopt/adoptApply.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
