package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteVolunteerController", value = "/DeleteVolunteer")
public class AjaxDeleteVolunteerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int volunteerNo = Integer.parseInt(request.getParameter("volNo"));
        int result = new ShelterMangerService().deleteVolunteer(volunteerNo);
        response.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
