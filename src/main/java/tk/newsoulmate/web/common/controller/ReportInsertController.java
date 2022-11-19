package tk.newsoulmate.web.common.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Report;
import tk.newsoulmate.web.common.service.CommonService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "report", value = "/report")
public class ReportInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        Report r=gson.fromJson(request.getParameter("report"), Report.class);
        int result=new CommonService().insertReport(r);
        response.getWriter().print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
