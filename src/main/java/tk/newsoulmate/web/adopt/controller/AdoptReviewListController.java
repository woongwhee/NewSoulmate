package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdoptReviewListController", value = "/adoptReList.bo")
public class AdoptReviewListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int listCount;
        int currentPage;
        int pageLimit;
        int boardLimit;

        int maxPage;
        int startPage;
        int endPage;

        listCount = new AdoptService().adoptReviewListCount();

        currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));

        pageLimit = 10;
        boardLimit = 10;

        maxPage = (int)Math.ceil((double)listCount / boardLimit);

        startPage = (currentPage-1) / pageLimit * pageLimit + 1;

        endPage = startPage + pageLimit - 1;

        if(endPage > maxPage) {
            endPage = maxPage;
        }
        PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

        ArrayList<Board> list= new AdoptService().selectAdoptReviewList(pi);

        request.setAttribute("list", list);
        request.setAttribute("pi", pi);
        request.getRequestDispatcher("views/adopt/adoptReviewList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
