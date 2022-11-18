package tk.newsoulmate.web.myPage.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.myPage.service.MyPageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MyPageUpdateController", value = "/MyPageUpdate")
public class MyPageUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();


        int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
        String phone = request.getParameter("phone");
        String nickName = request.getParameter("nickName");
        String email_1 = request.getParameter("email_1");
        String email_2 = request.getParameter("email_2");
        String email = email_1 + email_2;

        Member m = new Member(memberNo,phone,email,nickName);

        Member updateMem = new MyPageService().updateMember(m);

        session.setAttribute("loginUser",updateMem);
        session.setAttribute("alertMsg","성공적으로 회원정보를 수정했습니다.");
        response.sendRedirect(request.getContextPath()+"/myPage");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
