package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.domain.vo.Report;
import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManageReportDetailController", value = "/reportDetail")
public class ManageReportDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reportNo = Integer.parseInt(request.getParameter("rno"));
        ManageService msService = new ManageService();
        Report r=msService.selectReportDetail(reportNo);
        String path=request.getContextPath();
        if(r==null){
            response.sendRedirect(request.getContextPath()+"/reportList");
            return;
        }
        if(r.getBoardType()== BoardType.NOTICE){
            path+="/noticeDetail?dno"+r.getRefNo();
        }else{
            path+="/"+r.getBoardType().boardName+"Detail?bno="+r.getRefNo();
        }
        System.out.println(path);
        response.sendRedirect(path);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
