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
        String confirmCode = EmailController.rannum();
        new EmailController().sendConfirmMail(confirmCode,email);
        MemberService ms = new MemberService();
        int confirmNo=ms.insertConfirm(confirmCode);
        PrintWriter out = response.getWriter();
        out.print(confirmNo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
