package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdoptReviewDetailController", value = "/adoptRevDetail")
public class AdoptReviewDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardNo_ =request.getParameter("bno");
        if(boardNo_==null ||boardNo_.equals("")){
            request.getSession().setAttribute("errorMsg","잘못된접근입니다.");
            response.sendRedirect(request.getContextPath());
            return;
        }
        int boardNo=Integer.parseInt(boardNo_);
        AdoptService bService = new AdoptService();
        int result = bService.increaseReadCount(boardNo);
        if(result > 0) {
            Board b = bService.selectAdoptReviewDetail(boardNo);
            List<Reply> rList = bService.selectReplyList(boardNo);
            List<Category> cList= bService.selectCategoryList();
            request.setAttribute("b", b);
            request.setAttribute("rList", rList);
            request.setAttribute("cList", cList);
            request.getRequestDispatcher("views/adopt/adoptReviewDetail.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("errorMsg","잘못된접근입니다.");
            response.sendRedirect(request.getContextPath());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
