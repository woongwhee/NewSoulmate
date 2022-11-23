package tk.newsoulmate.web.manger.shelter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.SupportWithdrawRequest;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportWithdrawController", value = "/support/withdraw")
public class SupportWithdrawController extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SupportWithdrawRequest req = new Gson().fromJson(request.getReader(), SupportWithdrawRequest.class);

		int result = new SupportService().withdraw(req);

		if (result > 0) {
			response.setStatus(200);
		} else {
			response.setStatus(500);
		}

	}

}
