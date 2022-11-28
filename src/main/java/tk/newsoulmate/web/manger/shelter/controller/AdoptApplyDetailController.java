package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;
import tk.newsoulmate.web.manger.site.service.ManageService;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdoptApplyDetailServlet", value = "/AdoptDetail")
public class AdoptApplyDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int subNo = Integer.parseInt(request.getParameter("subNo"));

        Subscription s = new ShelterMangerService().selectAdoptApplyDetail(subNo);
        Notice n= new ManageService().selectNotice(s.getAnimalNo());
        request.setAttribute("s",s);
        request.setAttribute("n",n);

        request.getRequestDispatcher("/views/shelterManager/shelterAdoptApply.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
