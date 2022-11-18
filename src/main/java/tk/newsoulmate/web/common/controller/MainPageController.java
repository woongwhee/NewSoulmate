package tk.newsoulmate.web.common.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.common.service.CommonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Main", value = "/Main")
public class MainPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Notice> nList =new CommonService().selectThumbNail(0);
        List<Board>vList =new CommonService().selectVolunteerThumbNail(0);
        List<Board>aList =new CommonService().selectAdoptReview(0);
        request.setAttribute("nList",nList);
        request.getRequestDispatcher("views/common/mainPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
