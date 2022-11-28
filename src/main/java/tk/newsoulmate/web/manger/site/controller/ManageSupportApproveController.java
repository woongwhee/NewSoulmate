package tk.newsoulmate.web.manger.site.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "ManageSupportApproveController", value = "/support/withdraw-approve")
public class ManageSupportApproveController extends HttpServlet {

	private final SupportService supportService;

	public ManageSupportApproveController() {
		this.supportService = new SupportService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long supportNo = Long.parseLong(req.getParameter("supportNo"));
		int result = supportService.withdrawApprove(supportNo);

		if (result > 0) {
			resp.setStatus(200);
		} else {
			resp.setStatus(500);
		}
	}

}
