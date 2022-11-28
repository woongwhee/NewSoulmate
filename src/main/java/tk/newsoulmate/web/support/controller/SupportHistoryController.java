package tk.newsoulmate.web.support.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.domain.vo.SupportPage;
import tk.newsoulmate.web.shelter.service.ShelterService;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportHistory", value = "/supports")
public class SupportHistoryController extends HttpServlet {

    private final SupportService supportService;
    private final ShelterService shelterService;

    public SupportHistoryController() {
        this.shelterService = new ShelterService();
        this.supportService = new SupportService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object loginUser = request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            response.sendRedirect(request.getContextPath());
        } else {
            Member member = (Member) loginUser;
            List<Support> supportList;
            SupportPage pageInfo;
            int page = Integer.parseInt(request.getParameter("page"));

            try {
                LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
                LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
                request.setAttribute("startDate", startDate);
                request.setAttribute("endDate", endDate);

                int totalCount = supportService.countOnlyDoneByDate(member, startDate, endDate.plusDays(1));
                pageInfo = new SupportPage(page, totalCount);
                supportList = supportService.findAllOnlyDoneByDate(member, startDate, endDate.plusDays(1), pageInfo);
            } catch (Exception ex) {
                request.removeAttribute("startDate");
                request.removeAttribute("endDate");

                int totalCount = supportService.countOnlyDone(member);
                pageInfo = new SupportPage(page, totalCount);
                supportList = supportService.findAllOnlyDone(member, pageInfo);
            }


            request.setAttribute("pageInfo", pageInfo);
            request.setAttribute("supportList", supportList);
            request.getRequestDispatcher("/views/support/supportHistory.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
