package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.manger.site.service.ManageMemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ManageMember", value = "/manageMember")
public class ManageMemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ManageMemberService service= new ManageMemberService();
        ArrayList<Member> mList = service.selectManageMember();
        request.setAttribute("mList", mList);
        RequestDispatcher view = request.getRequestDispatcher("/views/manager/managerMemberList.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
