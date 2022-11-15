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

        if(loginUser == null) {
            request.setAttribute("errorMsg","로그인에 실패했습니다.");
            request.getRequestDispatcher("views/member/memberLoginForm.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("loginUser", loginUser);
            response.sendRedirect(request.getContextPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
