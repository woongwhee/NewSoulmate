package tk.newsoulmate.web.support.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SupportPayment", value = "/supportpayment")
public class SupportPaymentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Member loginUser = (Member) session.getAttribute("loginUser");
        // 세션에 없으면 로그인 페이지로 아니면 공통으로 필터에서 걸러짐

        request.setAttribute("loginUser",loginUser);
        request.getRequestDispatcher("/views/").forward(request,response); //todo: 결제페이지 갈떄 경로

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // todo: ajax 컨트롤러 만들지 고민  db에 저장하는 리퀘스트 온거 꺼내서 객체새로 만들기
    }
}
