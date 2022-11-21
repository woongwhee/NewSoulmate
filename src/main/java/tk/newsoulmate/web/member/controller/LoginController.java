package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login.do")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");

        MemberService ms = new MemberService();
        Member loginUser = ms.loginMember(memberId, memberPwd);
        System.out.println("왜안되?"+loginUser);
        if(loginUser == null) {
            request.getSession().setAttribute("errorMsg","로그인에 실패했습니다.");
            response.sendRedirect(request.getContextPath()+"/loginpage");
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("alertMsg", "로그인성공");
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
