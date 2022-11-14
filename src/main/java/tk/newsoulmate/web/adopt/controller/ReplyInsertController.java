package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ReplyInsertController", value = "/replyInsert.ad")
public class ReplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String replyContent = request.getParameter("content");
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int memberNo = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
        int replyNo = Integer.parseInt(request.getParameter("replyNo"));

        Reply r = new Reply();
        r.setReplyContent(replyContent);
        r.setBoardNo(boardNo);
        r.setMemberNo(Integer.parseInt(memberNo+""));
        r.setReplyNo(replyNo);

        int result = new AdoptService().insertReply(r);

        response.getWriter().print(result);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
