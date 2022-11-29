package tk.newsoulmate.web.volunteer.controller;

import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "volunteerApply", value = "/volunteerApply")
public class VolunteerApplyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Shelter> sList = new VolunteerService().volAbleShelter();
        request.setAttribute("sList",sList);

        request.getRequestDispatcher("/views/volunteer/volunteerApply.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
