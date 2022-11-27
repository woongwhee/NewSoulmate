package tk.newsoulmate.web.volunteer.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "volunteerRevUpdate", value = "/volunteerRevUpdate")
public class VolunteerRevUpdateEnrollController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String bno_=request.getParameter("bno");
        int bno=bno_==null?0:bno_.equals("")?0:Integer.parseInt(bno_);
        int loginMno=((Member)session.getAttribute("loginUser")).getMemberNo();
        Board b=new VolunteerService().selectReviewDetail(bno);
        if(b!=null &&b.getMemberNo()==loginMno){
            session.setAttribute("bno",b.getBoardNo());
            request.setAttribute("b",b);
            request.getRequestDispatcher("views/volunteer/volunteerReviewUpdate.jsp").forward(request,response);
        }else{
            session.setAttribute("errorMsg","잘못된접근입니다.");
            response.sendRedirect(request.getContextPath());
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
