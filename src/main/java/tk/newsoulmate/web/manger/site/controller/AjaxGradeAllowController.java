package tk.newsoulmate.web.manger.site.controller;

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

        String[] memberNo =(request.getParameterValues("checkMember"));
        int[] memArr = Stream.of(memberNo).mapToInt(Integer::parseInt).toArray();


        int[] result = new ManageService().changeStatus(memArr);
        for(int i = 0; i<result.length;i++){
            if(result[i] <0)
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
