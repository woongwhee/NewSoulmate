package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdoptReviewEnrollController", value = "/adoptRevUpdateEnroll")
public class AdoptReviewUpdateEnrollController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String bno_=request.getParameter("bno");
        int bno=bno_==null?0:bno_.equals("")?0:Integer.parseInt(bno_);
        int loginMno=((Member)session.getAttribute("loginUser")).getMemberNo();
        Board b=new AdoptService().selectAdoptReviewDetail(bno);
        if(b!=null &&b.getMemberNo()==loginMno){
            request.setAttribute("b",b);
            request.getRequestDispatcher("views/adopt/adoptReviewUpdate.jsp").forward(request,response);
        }else{
            session.setAttribute("errorMsg","잘못된접근입니다.");
            response.sendRedirect(request.getContextPath());
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
