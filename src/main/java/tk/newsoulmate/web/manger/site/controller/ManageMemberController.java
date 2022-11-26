package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.ManageMember;
import tk.newsoulmate.web.manger.site.service.ManageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ManageMember", value = "/manageMember")
public class ManageMemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");

        ManageService service= new ManageService();
        ArrayList<ManageMember> mList = service.selectMemberList();

        if (filter == null || filter.equals("ALL")) {
            request.setAttribute("mList", mList);
        } else {
            List<ManageMember> list = mList.stream()
                    .filter(it -> it.getMemberGrade().name().equals(filter))
                    .collect(Collectors.toList()); // Collectors: 스트림의 요소들을 List 객체로 변환
            request.setAttribute("mList", list);
        } // Lambda, 메소드 시그니처, 메소드 참조형태

        RequestDispatcher view = request.getRequestDispatcher("/views/manager/managerMemberList.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
