package tk.newsoulmate.web.shelter.controller;

import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "noticeDetail", value = "/noticeDetail")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dno_=request.getParameter("dno");
        if(dno_==null||dno_.equals("")) {
            request.getSession().setAttribute("errorMsg", "잘못된접근입니다.");
            response.sendRedirect(request.getContextPath());
            return;
        }
        long dno=Long.parseLong(dno_);
        ShelterService ss=new ShelterService();
        Notice n= ss.selectNotice(dno);
        if(n==null){
            request.getSession().setAttribute("errorMsg", "업데이트 되지 않은 페이지 입니다.");
            response.sendRedirect(request.getContextPath());
            return;
        }
        List<Reply> rList=ss.selectNoticeReply(dno);
        List<Category> cList=ss.selectCategoryList();
        request.setAttribute("n",n);
        request.setAttribute("rList",rList);
        request.setAttribute("cList",cList);
        request.getRequestDispatcher("/views/shelter/noticeDetailView.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
