package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.Board;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "inquire", value = "/inquire")
public class InquireListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String categoryName = "문의";
        Member loginUser=(Member)request.getSession().getAttribute("loginUser");
        PageInfo pi;
        if(loginUser==null){
            pi=new PageInfo(0,1,categoryName);
        }else{
            int listCount = new InquireService().selectListCount(loginUser);
            int currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
            pi = new PageInfo(listCount,currentPage,categoryName);
            ArrayList<Board> list = new InquireService().selectQnAList(pi,loginUser);
            request.setAttribute("list", list);
        }
        request.setAttribute("pi", pi);
        request.getRequestDispatcher("/views/inquire/inquireFQListView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
