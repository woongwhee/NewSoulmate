package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MemberSignup", value = "/signupForm.do")
public class MemberSignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");
        String memberName = request.getParameter("memberName");
        String nickName = request.getParameter("nickName");
        String phone = request.getParameter("memberPhone")+request.getParameter("Phone");
        String email = request.getParameter("memberMail");

        Member m = new Member(memberId, memberPwd, memberName, nickName, phone, email);
        MemberService service = new MemberService();
        int result = service.insertMember(m);

        if (result > 0) {
            request.setAttribute("alertMsg", "회원가입에 성공했습니다.");
        } else {
            request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
        }
        request.getRequestDispatcher("views/member/memberLoginForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,
            IOException {

    }
}
