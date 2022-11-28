package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VolunteerAdoptDetailController", value = "/VolunteerDetail")
public class VolunteerAdoptDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int volunteerNo = Integer.parseInt(request.getParameter("volunteerNo"));
        String volRead = request.getParameter("volRead");

        Volunteer v = new ShelterMangerService().selectVolunteer(volunteerNo);

        if(v.getVolRead().equals("N")){
            new ShelterMangerService().ReadVolunteer(volunteerNo);
        }

        request.setAttribute("v",v);
        request.getRequestDispatcher("/views/shelterManager/shelterVolunteerApply.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
