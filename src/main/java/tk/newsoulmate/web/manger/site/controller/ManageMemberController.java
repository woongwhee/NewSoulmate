package tk.newsoulmate.web.manger.site.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.web.manger.site.service.ManageService;

@WebServlet(name = "ManageMember", value = "/manageMember")
public class ManageMemberController extends HttpServlet {

    private final ManageService manageService = new ManageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        int currentPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));

        int totalCount = manageService.countMember(filter);
        PageInfo pageInfo = new PageInfo(totalCount, currentPage);
        request.setAttribute("pageInfo", pageInfo);

        ArrayList<Member> mList = manageService.selectMemberList(pageInfo, filter);
        request.setAttribute("mList", mList);

        RequestDispatcher view = request.getRequestDispatcher("/views/manager/managerMemberList.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
