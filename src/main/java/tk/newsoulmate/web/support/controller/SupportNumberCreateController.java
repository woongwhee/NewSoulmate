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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object object = session.getAttribute("loginUser");
		if (object == null) {
			response.sendRedirect(request.getContextPath());
		}
		Member loginMember = (Member) object;

		long shelterNo = Long.parseLong(request.getParameter("shelterNo"));
		long amount = Long.parseLong(request.getParameter("amount"));
		String supportNumber = new SupportService().createNumber(loginMember.getMemberNo(), shelterNo, amount);

		response.getWriter().print(supportNumber);
		response.getWriter().flush();
	}
}
