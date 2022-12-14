package tk.newsoulmate.web.manger.shelter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.Member;

import tk.newsoulmate.domain.dto.request.SupportWithdrawRequest;

import tk.newsoulmate.web.support.service.SupportService;
import tk.newsoulmate.web.shelter.service.ShelterService;

@WebServlet(name = "SupportWithdrawController", value = "/support/withdraw")
public class SupportWithdrawController extends HttpServlet {

	private final SupportService supportService;
	private final ShelterService shelterService;

	public SupportWithdrawController() {
		this.supportService = new SupportService(); // 객체 생성 -> 곧 메모리 -> Singleton
		this.shelterService = new ShelterService();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object loginUser = request.getSession(false).getAttribute("loginUser");
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath());
		} else {
			Member member = (Member)loginUser;
			long shelterNo = member.getShelterNo();

			SupportWithdrawRequest req = new Gson().fromJson(request.getReader(), SupportWithdrawRequest.class);
			int result = supportService.withdraw(req);

			if (result > 0) {
				result = shelterService.updateLatestTransfer(shelterNo, req.getSupportNo());
				response.setStatus(200);
			} else {
				response.setStatus(500);
			}
		}
	}

}
