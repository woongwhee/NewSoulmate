package tk.newsoulmate.web.member.controller;


import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "checkAuth", value = "/checkAuth")
public class AuthenticationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authCode = request.getParameter("authCode");
        int confirmNo = 0;
        if(request.getParameterMap().containsKey("confirmNo")) {
            confirmNo = Integer.parseInt(request.getParameter("confirmNo"));
        }
        int result= new MemberService().copareConfirm(confirmNo,authCode);
        response.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
