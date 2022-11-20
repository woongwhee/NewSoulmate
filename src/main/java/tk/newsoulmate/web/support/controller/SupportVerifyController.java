package tk.newsoulmate.web.support.controller;

import tk.newsoulmate.web.support.service.SupportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SupportVerify", value = "/support/verify")
public class SupportVerifyController extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달한 값을 받기위한 파라미터
		// get 메소드로 전달한값을 받아서
		// impUid는 iamport에 요청해서 결제정보를 다시 받아올 수 있음
		String impUid = request.getParameter("impUid");
		String merchantUid = request.getParameter("merchantUid");

		boolean result = new SupportService().verify(impUid, merchantUid);


		if (result) {
			// TODO: PENDING -> DONE 으로 업데이트
			response.setStatus(200);

		} else {
			// TODO : PENDING -> FAILED로 업데이트
			// TODO : Iamport 쪽에 취소처리해야할수도 있을것같다?
			response.setStatus(400); // Bad Request
		}
	}

}
