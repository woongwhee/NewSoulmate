package tk.newsoulmate.web.common.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.web.common.service.CommonService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "replyInsert", value = "/replyInsert")
public class ReplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        System.out.println("뭐지"+request.getParameter("reply"));
        Reply r=gson.fromJson(request.getParameter("reply"), Reply.class);
        System.out.println("뭘까"+r);
        System.out.println(r.getReplyContent());
        int result=new CommonService().insertBoardReply(r);
        response.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
