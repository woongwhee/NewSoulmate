package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MemberSignup", value = "/signup.do")
public class MemberSignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");
        String memberName = request.getParameter("memberName");
        String nickName = request.getParameter("nickName");
        String Phone = request.getParameter("Phone");
        String Email = request.getParameter("memberMail");

        Member m = new Member();

        m.setMemberId(memberId);
        m.setMemberPwd(memberPwd);
        m.setMemberName(memberName);
        m.setNickName(nickName);
        m.setPhone(Phone);
        m.setEmail(Email);

        MemberService service = new MemberService();
        int result = service.insertMember(m);

        HttpSession session = request.getSession();
        session.setAttribute("alertMsg", "회원가입이 완료되었습니다.");

        RequestDispatcher view = request.getRequestDispatcher("index.jsp");

        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
