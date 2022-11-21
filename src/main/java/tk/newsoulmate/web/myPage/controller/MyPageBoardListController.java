package tk.newsoulmate.web.myPage.controller;

import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.web.myPage.service.MyPageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MyPageListController", value = "/myPageBoardList.bo")
public class MyPageBoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        PageInfo pi;
        BoardType bt = null;
        if (loginUser == null){
            pi = new PageInfo(0,1);
        } else {
            int listCount = new MyPageService().selectMyPageBoardListCount(loginUser);
            int currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
//            pi = new PageInfo(listCount, currentPage);
            int pageLimit=10;
            int boardLimit=10;
            int maxPage=listCount/pageLimit+1;
            int startPage=currentPage/boardLimit*boardLimit+1;
            int endPage=(currentPage/boardLimit+1)*(boardLimit);
            if(endPage>maxPage){
                endPage=maxPage;
            }
            pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
            ArrayList<Board> list = new MyPageService().selectMyPageBoardList(pi,loginUser);
            request.setAttribute("list", list);
        }
        request.setAttribute("pi",pi);
        request.setAttribute("bt",bt);
        request.getRequestDispatcher("/views/myPage/myPageBoardListView.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
