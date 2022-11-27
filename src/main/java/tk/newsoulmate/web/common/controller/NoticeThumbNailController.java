package tk.newsoulmate.web.common.controller;

import tk.newsoulmate.domain.vo.Notice;
import tk.newsoulmate.web.common.service.CommonService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "noticeNext", value = "/noticeNext")
public class NoticeThumbNailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page= Integer.parseInt(request.getParameter("page"));
        List<Notice> nList =new CommonService().selectNoticeThumbNail(page);
        request.setAttribute("nList",nList);
        request.getRequestDispatcher("views/common/noticeSlide.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
