package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdoptReviewDetailController", value = "/adoptReDetail.bo")
public class AdoptReviewDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//
//        int boardNo = Integer.parseInt(request.getParameter("bno"));
//
//        AdoptService bService = new AdoptService();
//
//        int result = bService.readCount(boardNo);
//
//        if(result > 0) {
//
//            Board b = bService.selectAdoptReviewDetail(boardNo);
//
//            request.setAttribute("b", b);
//
//            request.getRequestDispatcher("views/adopt/adoptReviewDetail.jsp").forward(request, response);
//        } else {
//
//        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
