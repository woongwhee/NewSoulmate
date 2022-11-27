package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet(name = "AdoptReviewListController", value = "/adoptRevList")
public class AdoptReviewListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdoptService as=new AdoptService();
        int listCount =as.selectListCount();
        int page=0;
        List<Attachment> tList= as.selectAdoptReviewList(page,10);
        request.setAttribute("tList",tList);
        request.setAttribute("listCount",listCount);
        request.getRequestDispatcher("views/adopt/adoptReviewList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
