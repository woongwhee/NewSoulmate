package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "AjaxGradeAllowController", value = "/GradeAllow")
public class AjaxGradeAllowController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 등급 n => y 로 바꾸기
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> memberNo = new Gson().fromJson(request.getReader(), listType);

        // String[] memberNo = (request.getParameterValues("checkMember"));
        // int[] result = new ManageService().changeStatus(memArr);

        String body = new Gson().toJson(memberNo);
        response.getWriter().println(body);
        response.getWriter().flush();

        /*for(int i = 0; i<result.length;i++){
            if(result[i] <0);
        }*/
    }
}
