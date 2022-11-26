package tk.newsoulmate.web.manger.site.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.request.ManageMemberDeleteRequest;
import tk.newsoulmate.web.member.service.MemberService;

@WebServlet(name = "ManageMemberDeleteController", value = "/manage/delete-member")
public class ManageMemberDeleteController extends HttpServlet {

	private final MemberService memberService;

	public ManageMemberDeleteController() {
		this.memberService = new MemberService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManageMemberDeleteRequest deleteReq = new Gson().fromJson(req.getReader(), ManageMemberDeleteRequest.class);
		int result = memberService.deleteMember(deleteReq.getMemberNo());
		if (result > 0) {
			resp.setStatus(200);
		} else {
			resp.setStatus(500);
		}
	}
}
