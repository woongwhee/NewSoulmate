package tk.newsoulmate.web.myPage.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.myPage.service.MyPageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ajaxCheckPwdController", value = "/ajaxCheckPwd")
public class ajaxCheckPwdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String memberPwd = request.getParameter("memberPwd");

        HttpSession session = request.getSession();
        int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
        String memberPwd2 = new MyPageService().checkPwd(memberNo);
        PrintWriter pw = response.getWriter();

        if(memberPwd.equals(memberPwd2)){
            pw.print(1);
        }else{
            pw.print(0);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
