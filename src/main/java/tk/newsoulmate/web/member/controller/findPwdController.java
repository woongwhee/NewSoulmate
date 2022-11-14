package tk.newsoulmate.web.member.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "findPwd", value = "/findPwd")
public class findPwdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String memberName = request.getParameter("memberName");
        String memberId = request.getParameter("memberId");
        String Email = request.getParameter("Email");

        MemberService service = new MemberService();
        Member m = service.findPwd(memberName,memberId,Email);

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        gson.toJson(m,out);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
