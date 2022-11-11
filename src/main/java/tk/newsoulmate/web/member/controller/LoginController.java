package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

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
            RequestDispatcher view = request.getRequestDispatcher("views/MemberLoginForm.jsp");
            view.forward(request, response);

        }else {

            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            session.setAttribute("alertMsg", "성공적으로 로그인이 되었습니다.");

            response.sendRedirect(request.getContextPath());

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
