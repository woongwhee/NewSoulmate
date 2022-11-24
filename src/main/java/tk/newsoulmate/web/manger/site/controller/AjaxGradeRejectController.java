package tk.newsoulmate.web.manger.site.controller;

import com.google.gson.Gson;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxGradeRejectController", value = "/GradeReject")
public class AjaxGradeRejectController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] memberNo =request.getParameterValues("rejectMember");
        System.out.println(memberNo[0]);
        int result = new ManageService().changeStatusReject(memberNo);

        response.setContentType("application/json; charset=UTF-8");
        Gson gson = new Gson();
        gson.toJson(result,response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
