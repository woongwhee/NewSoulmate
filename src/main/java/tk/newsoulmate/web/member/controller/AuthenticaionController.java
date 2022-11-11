package tk.newsoulmate.web.member.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "checkAuth", value = "/checkAuth")
public class AuthenticaionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String input =request.getParameter("authCode");
        String code= (String) request.getSession().getAttribute("emailCode");
        System.out.println(input+code);
        if(input.equals(code)){
            response.getWriter().print(1);

        }else{
            response.getWriter().print(0);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
