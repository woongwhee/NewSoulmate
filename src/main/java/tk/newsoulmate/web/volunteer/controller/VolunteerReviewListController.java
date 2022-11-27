package tk.newsoulmate.web.volunteer.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "volunteerRevList", value = "/volunteerRevList")
public class VolunteerReviewListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VolunteerService vs=new VolunteerService();
        int listCount =vs.selectListCount();
        int page=0;
        List<Attachment> tList= vs.selectVolunteerReviewList(page,10);
        request.setAttribute("tList",tList);
        request.setAttribute("listCount",listCount);
        request.getRequestDispatcher("views/volunteer/volunteerReviewList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
