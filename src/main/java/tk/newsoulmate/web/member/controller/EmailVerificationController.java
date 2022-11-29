package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EmailVerification", value = "/sendMail.do")
public class EmailVerificationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("memberMail");
        EmailController ec = new EmailController();
        String randomCode = EmailController.rannum();
        MemberService ms=new MemberService();
        int confirmCode=ms.insertConfirm(randomCode);


        request.getSession().setAttribute("emailCode",randomCode);
        PrintWriter out = response.getWriter();
        out.print(1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
