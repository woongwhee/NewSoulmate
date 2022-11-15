package tk.newsoulmate.web.member.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/*import tk.newsoulmate.domain.vo.PwdReset;*/
import tk.newsoulmate.web.member.service.MemberService;

@WebServlet(name = "PwdReset", value = "/pwdReset.do")
public class FindPwdResetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        PwdReset pwdReset = gson.fromJson(reader, PwdReset.class);
        MemberService ms = new MemberService();

        int result = ms.updatePassword(pwdReset);
        response.getWriter().println(result);
        response.getWriter().flush();
*/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

}
