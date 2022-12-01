package tk.newsoulmate.web.support.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportNumberCreate", value = "/support/number")
public class SupportNumberCreateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginUser");
		long shelterNo = Long.parseLong(request.getParameter("shelterNo"));
		long amount = Long.parseLong(request.getParameter("amount"));
		// 2. 쉘터번호랑 얼마 후원할지 받아서 랜덤한 키값을 생성해서(supportService - 그 값과 어떤 쉘터 얼마 데이터베이스에 (SupportService createNumber) 클라이언트가 결제 하겠다고 한 금액을 저장
		String supportNumber = new SupportService().createNumber(loginMember.getMemberNo(), shelterNo, amount);
		response.getWriter().print(supportNumber);
		response.getWriter().flush();
	}
}
