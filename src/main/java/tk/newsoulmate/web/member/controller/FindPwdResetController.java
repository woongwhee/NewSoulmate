package tk.newsoulmate.web.member.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.Member;
/*import tk.newsoulmate.domain.vo.PwdReset;*/
import tk.newsoulmate.web.member.service.MemberService;

@WebServlet(name = "PwdReset", value = "/pwdReset.do")
public class FindPwdResetController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        PwdReset pwdReset = gson.fromJson(reader, PwdReset.class);
        MemberService ms = new MemberService();

        int result = ms.updatePassword(pwdReset);
        response.getWriter().println(result);
        response.getWriter().flush();*/

  /*      request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("MemberId");
        String userPwd = request.getParameter("MemberPwd");
        String updatePwd = request.getParameter("updatePwd");

        Member updateMem = new MemberService().updatePwdMember(userId, userPwd, updatePwd);

        HttpSession session = request.getSession();

        if(updateMem == null) { // 실패시 실행할 로직
            session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");

        }else { // 성공시
            session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다.");
            session.setAttribute("loginUser", updateMem);

        }

        request.getRequestDispatcher("views/member/memberLoginForm.jsp").forward(request, response);*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

}
