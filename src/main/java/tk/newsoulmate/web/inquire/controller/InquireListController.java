package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.Board;

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



        // * listCount : 총 게시글 개수
        int listCount = new InquireService().selectListCount();

        // * currentPage : 현재 페이지
        int currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));

        String categoryName = "문의";

        // 1. 페이징바만들때 필요한 객체.
        PageInfo pi = new PageInfo(listCount,currentPage,categoryName);


        // 현재 QnA게시판의 게시글들 가져오기
        ArrayList<Board> list = new InquireService().selectQnAList(pi);
        request.setAttribute("list", list);
        request.setAttribute("pi", pi);
        request.getRequestDispatcher("/views/inquire/inquireFQListView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
