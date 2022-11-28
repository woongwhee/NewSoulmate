package tk.newsoulmate.web.volunteer.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "volunteerRevNext", value = "/volunteerRevNext")
public class VolunteerReviewPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page= Integer.parseInt((request.getParameter("page")));
        List<Attachment> tList= new VolunteerService().selectVolunteerReviewList(page,10);
        request.setAttribute("tList",tList);
        request.getRequestDispatcher("views/volunteer/volunteerRevListArea.jsp").forward(request, response);
    }
}
