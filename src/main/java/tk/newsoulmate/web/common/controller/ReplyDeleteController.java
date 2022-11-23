package tk.newsoulmate.web.common.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.common.service.CommonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "replyDelete", value = "/replyDelete.bo")
public class ReplyDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rno_ = request.getParameter("rno");
        HttpSession session=request.getSession();
        if(rno_==null||rno_.equals("")){
            error(request, response);
            return;
        }
        int rno=Integer.parseInt(rno_);
        CommonService cs=new CommonService();
        Member loginUser = (Member) session.getAttribute("loginUser");
        int loginMno= loginUser.getMemberNo();
        int result=cs.deleteReply(rno,loginUser.getMemberNo());
        response.getWriter().print(result);

    }

    private static void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("errorMsg","잘못된접근입니다.");
        response.sendRedirect(request.getContextPath()+"/adoptRevList");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
