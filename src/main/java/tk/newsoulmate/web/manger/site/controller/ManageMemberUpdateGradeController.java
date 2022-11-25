package tk.newsoulmate.web.manger.site.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.request.ManageMemberUpdateGradeRequest;
import tk.newsoulmate.web.member.service.MemberService;

@WebServlet(name = "ManageMemberUpdateGradeController", value = "/manage/grade")
public class ManageMemberUpdateGradeController extends HttpServlet {
	private final MemberService memberService;

	public ManageMemberUpdateGradeController() {
		this.memberService = new MemberService();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ManageMemberUpdateGradeRequest updateGradeReq = new Gson().fromJson(req.getReader(), ManageMemberUpdateGradeRequest.class);
		int result = memberService.updateGrade(updateGradeReq);
		if (result > 0) {
			resp.setStatus(200);  // 2xx -> 정상
		} else {
			resp.setStatus(500);  // 4xx(클라이언트 잘못), 5xx(서버 잘못) -> 에러
		}

	}
}
