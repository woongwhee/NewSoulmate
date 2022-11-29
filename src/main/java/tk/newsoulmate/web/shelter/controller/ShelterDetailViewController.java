package tk.newsoulmate.web.shelter.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.manger.site.service.ManageService;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShelterDetatilViewController", value = "/ShelterDetail")
public class ShelterDetailViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long shelterNo = Long.parseLong(request.getParameter("shelterNo"));
        Shelter s = new ShelterService().shelterListByShelterNo(shelterNo);


        request.setAttribute("s",s);
        request.getRequestDispatcher("/views/shelter/shelterDetailView.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
