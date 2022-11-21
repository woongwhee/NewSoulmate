package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjaxCheckId", urlPatterns = { "/ajaxCheckId.do" })
public class AjaxCheckIdController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String memberId = request.getParameter("memberId");
        MemberService service = new MemberService();
        int count = service.idCheck(memberId);
        PrintWriter pw = response.getWriter();
        if(count > 0) {
            pw.print(1);
            // 사용불가
        }else {
            pw.print(0);
            // 사용가능
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}