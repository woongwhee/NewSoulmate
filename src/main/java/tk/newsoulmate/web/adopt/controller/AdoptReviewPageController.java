package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "adoptRevNext", value = "/adoptRevNext")
public class AdoptReviewPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page= Integer.parseInt((request.getParameter("page")));
        List<Attachment> tList= new AdoptService().selectAdoptReviewList(page,10);
        request.setAttribute("tList",tList);
        request.getRequestDispatcher("views/adopt/adoptRevListArea.jsp").forward(request, response);
    }
}
