package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "findPwd", value = "/findPwd.do")
public class findPwdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String memberId = request.getParameter("memberId");
        String memberName = request.getParameter("memberName");
        String Email = request.getParameter("Email");
        MemberService service = new MemberService();
        Member m = service.findPwd(memberId,memberName,Email);

        request.setAttribute("alertMsg","비밀번호 : " + m.getMemberPwd());
        request.getRequestDispatcher("views/member/memberLoginForm.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}