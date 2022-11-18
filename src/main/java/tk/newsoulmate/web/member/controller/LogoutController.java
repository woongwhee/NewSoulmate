package tk.newsoulmate.web.member.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import tk.newsoulmate.domain.vo.Member;

@WebServlet(name = "Logout", urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Object obj = session.getAttribute("loginUser"); // obj = null; -> 로그인안됨
        if (obj != null) {
            request.getSession().invalidate();
            request.getSession(true);
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
