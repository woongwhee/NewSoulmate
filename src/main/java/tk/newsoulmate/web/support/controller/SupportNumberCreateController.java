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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 받아야될 정보 : 얼마 후원인지, 누구인지(HttpSession)
		HttpSession session = req.getSession();
		Object object = session.getAttribute("loginUser");
		if (object == null) {
			resp.sendRedirect(req.getContextPath());
		}
		Member loginMember = (Member) object;

		long shelterNo = Long.parseLong(req.getParameter("shelterNo"));
		long amount = Long.parseLong(req.getParameter("amount"));
		String supportNumber = new SupportService().createNumber(loginMember.getMemberNo(), shelterNo, amount);

		resp.getWriter().println(supportNumber);
		resp.getWriter().flush();
	}

}
