package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "adoptRevDelete", value = "/adoptRevDelete")
public class AdoptReviewDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bno_ = request.getParameter("bno");
        HttpSession session=request.getSession();
        if(bno_==null||bno_.equals("")){
            error(request, response);
            return;
        }
        int bno=Integer.parseInt(bno_);
        AdoptService as=new AdoptService();
        Board board = as.selectAdoptReviewDetail(bno);
        Member loginUser = (Member) session.getAttribute("loginUser");
        if(board==null||loginUser==null){
            error(request, response);
            return;
        }
        int boardMno= board.getMemberNo();
        int loginMno= loginUser.getMemberNo();
        if(boardMno!=loginMno){
            error(request, response);
            return;
        };
        int result=as.deleteBoard(bno);
        if(result==0){
            error(request,response);
            return;
        }
        session.setAttribute("alertMsg","게시글 삭제 성공");
        response.sendRedirect(request.getContextPath()+"/adoptReList");

    }

    private static void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("errorMsg","잘못된접근입니다.");
        response.sendRedirect(request.getContextPath()+"/adoptReList");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
