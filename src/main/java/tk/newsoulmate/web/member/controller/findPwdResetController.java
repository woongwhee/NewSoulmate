package tk.newsoulmate.web.member.controller;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import tk.newsoulmate.domain.dto.request.PwdReset;

@WebServlet(name = "PwdReset", value = "/pwdReset.do")
public class findPwdResetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* 요청보낼때 post / 데이터를 request body에 담아서 보냄.
         *  JSON 포맷 (REST API)
         *  {
         *     "password": "1234",
         *     "passwordConfirm": "1234"
         *  }
         * '{PwdReset:{"password":"1234","passwordConfirm":"1234"}}' -> JSON 형태의 문자열
         * Library : ObjectMapper, Gson
         * 데이터 타입은 json / 자바의 객체로 변환하기 위해서 gson 라이브러리 사용
         */
        Gson gson = new Gson();
        PwdReset pwdReset = gson.fromJson(request.getParameter("PwdReset"), PwdReset.class);
        int memberNo=((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
        MemberService ms = new MemberService();
        int result = ms.updatePassword(memberNo,pwdReset.getPassword());
        response.getWriter().println(result);
        response.getWriter().flush();
    }
}
