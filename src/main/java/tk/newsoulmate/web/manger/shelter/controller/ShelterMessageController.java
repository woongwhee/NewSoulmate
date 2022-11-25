package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShelterMessageController", value = "/ShelterMessage")
public class ShelterMessageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member loginUser=(Member)request.getSession().getAttribute("loginUser");
        PageInfo pi;
        long shelterNo = loginUser.getShelterNo();
        if(loginUser==null){
            pi=new PageInfo(0,1);
        }else {
            int listCount = new ShelterMangerService().shelterNoAdoptApplyListCount(shelterNo);

            int currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));

            int pageLimit=10;
            int boardLimit=10;
            int maxPage=listCount/pageLimit+1;
            int startPage=currentPage/boardLimit*boardLimit+1;
            int endPage=(currentPage/boardLimit+1)*(boardLimit);
            if(endPage>maxPage){
                endPage=maxPage;
            }
            pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);

            ArrayList<Subscription> list = new ShelterMangerService().ShelterNoAdoptApplyList(pi,shelterNo);
            request.setAttribute("list", list);
        }
        request.setAttribute("pi", pi);
        request.getRequestDispatcher("/views/shelterManager/shelterMessage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}