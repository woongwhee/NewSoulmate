package tk.newsoulmate.web.manger.site.controller;

import com.google.gson.Gson;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.stream.Stream;

@WebServlet(name = "AjaxGradeAllowController", value = "/GradeAllow")
public class AjaxGradeAllowController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 등급 n => y 로 바꾸기
        // gra

        String[] memberNo =request.getParameterValues("checkMember");
        System.out.println(memberNo);
//        Integer[] memArr = Stream.of(memberNo).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
//        System.out.println(memArr);
        int result = new ManageService().changeStatus(memberNo);

        response.setContentType("application/json; charset=UTF-8");
        Gson gson = new Gson();
        gson.toJson(result,response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
