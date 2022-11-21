package tk.newsoulmate.web.support.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.PageInfo;
import tk.newsoulmate.domain.vo.SupportCompleteResponse;
import tk.newsoulmate.domain.vo.SupportPage;
import tk.newsoulmate.web.support.service.SupportService;

@WebServlet(name = "SupportHistory", value = "/supports")
public class SupportHistoryController extends HttpServlet {

    private final SupportService supportService;

    public SupportHistoryController() {
        this.supportService = new SupportService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object loginUser = req.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath());
        } else {
            Member member = (Member) loginUser;
            List<SupportCompleteResponse> supportList = new ArrayList<>();
            SupportPage pageInfo;
            int page = Integer.parseInt(req.getParameter("page"));

            try {
                LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
                LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));
                req.setAttribute("startDate", startDate);
                req.setAttribute("endDate", endDate);

                int totalCount = supportService.countOnlyDoneByDate(member, startDate, endDate.plusDays(1));
                pageInfo = new SupportPage(page, totalCount);
                supportList = supportService.findAllOnlyDoneByDate(member, startDate, endDate.plusDays(1), pageInfo);
            } catch (Exception ex) {
                req.removeAttribute("startDate");
                req.removeAttribute("endDate");

                int totalCount = supportService.countOnlyDone(member);
                pageInfo = new SupportPage(page, totalCount);
                supportList = supportService.findAllOnlyDone(member, pageInfo);
            }

            req.setAttribute("pageInfo", pageInfo);
            req.setAttribute("supportList", supportList);
            req.getRequestDispatcher("/views/support/supportHistory.jsp").forward(req, resp);
        }
    }
}
