package tk.newsoulmate.web.myPage.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.myPage.service.MyPageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MyPageInfoController", value = "/MyPageInfo")
public class MyPageInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher("/views/myPage/myPageInfoView.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
