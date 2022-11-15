package tk.newsoulmate.web.member.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.member.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "findPwd", value = "/findPwd")
public class findPwdController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*        request.setCharacterEncoding("utf-8");

        String memberName = request.getParameter("memberName");
        String memberId = request.getParameter("memberId");
        String Email = request.getParameter("Email");

        MemberService service = new MemberService();
        Member m = service.findPwd(memberName,memberId,Email);

        request.setAttribute("alertMsg","비밀번호 : " + m.getMemberPwd());
        request.getRequestDispatcher("views/member/memberLoginForm.jsp").forward(request, response);*/

        /*PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        gson.toJson(m,out);*/


        request.setCharacterEncoding("UTF-8");

        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");
        String updatePwd = request.getParameter("updatePwd");

        Member updateMem = new MemberService().updatePwdMember(memberId, memberPwd, updatePwd);

        HttpSession session = request.getSession();

        if(updateMem == null) { // 실패시 실행할 로직
            session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");

        }else { // 성공시
            session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다.");
            session.setAttribute("loginUser", updateMem);

        }

        response.sendRedirect(request.getContextPath()); // /jsp



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}