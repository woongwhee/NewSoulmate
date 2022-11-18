package tk.newsoulmate.web.support.controller;

import java.io.IOException;
import java.net.http.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.client.iamport.IamportClient;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportVerify", value = "/support/verify")
public class SupportVerifyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String impUid = req.getParameter("impUid");
		String merchantUid = req.getParameter("merchantUid");

		boolean result = new SupportService().verify(impUid, merchantUid);

		if (result) {
			resp.setStatus(200);

		} else {
			resp.setStatus(400); // Bad Request
		}
	}

}
