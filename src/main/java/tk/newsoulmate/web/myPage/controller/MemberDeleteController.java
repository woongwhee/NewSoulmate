package tk.newsoulmate.web.myPage.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MemberDeleteController", value = "/memberDelete")
public class MemberDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String memberId = ((Member) session.getAttribute("loginUser")).getMemberId();
        String memberPwd = request.getParameter("memberPwd");
        int result = new MemberService().deleteMember(memberId, memberPwd);

        if(result > 0) {
            request.getSession().invalidate();
            request.getSession(true);
            request.getSession().setAttribute("alertMsg", "성공적으로 회원탈퇴 되었습니다.");
            response.sendRedirect(request.getContextPath());
        }else {
            request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
            request.getRequestDispatcher("views/myPage/myPageInfoView.jsp").forward(request, response);
        }

    }
}
