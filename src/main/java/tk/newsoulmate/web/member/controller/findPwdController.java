package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "findPwddo", value = "/findPwd.do")
public class findPwdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String memberName = request.getParameter("memberName");
        String Email = request.getParameter("Email");

        System.out.println(memberId+memberName+Email);
        int result = new MemberService().resetPwd(memberName, memberId, Email);
        response.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}