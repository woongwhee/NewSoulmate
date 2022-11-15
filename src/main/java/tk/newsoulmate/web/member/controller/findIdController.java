package tk.newsoulmate.web.member.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "findId", value = "/findId.do")
public class findIdController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String memberName = request.getParameter("searchName");
        String Email = request.getParameter("memberMail");

        MemberService service = new MemberService();
        Member m = service.findId(memberName,Email);

        request.setAttribute("alertMsg","아이디 : " + m.getMemberId());
        request.getRequestDispatcher("views/member/memberLoginForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}