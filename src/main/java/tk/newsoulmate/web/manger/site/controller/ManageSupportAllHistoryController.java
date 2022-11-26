package tk.newsoulmate.web.manger.site.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "ManageSupportAllHistory", value = "/manageSupportAllHistory")
public class ManageSupportAllHistoryController extends HttpServlet {
    private final SupportService supportService;

    public ManageSupportAllHistoryController() {
        this.supportService = new SupportService();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        int currentPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));

        int totalCount = supportService.countByFilter(filter);
        PageInfo pageInfo = new PageInfo(totalCount, currentPage);
        request.setAttribute("pageInfo", pageInfo);

        List<Support> mList = supportService.findAllByFilter(pageInfo, filter);
        request.setAttribute("mList", mList);

        request.getRequestDispatcher("/views/manager/manageSupportAllHistory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
