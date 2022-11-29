package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdoptApplyReadController", value = "/AdoptApplyRead")
public class AdoptApplyReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int subNo = Integer.parseInt(request.getParameter("sno"));
        String subRead = request.getParameter("subRead");

        if(subRead.equals("Y")){
            request.getRequestDispatcher("ShelterMessage?page=1").forward(request, response);
        }else{
            int s = new ShelterMangerService().changeAdoptApplySubRead(subNo);

            if(s>0){
                response.sendRedirect("ShelterMessage?page=1");
            } else {
                request.getSession().setAttribute("errorMsg", "서버요청 실패");
                response.sendRedirect(request.getContextPath());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
