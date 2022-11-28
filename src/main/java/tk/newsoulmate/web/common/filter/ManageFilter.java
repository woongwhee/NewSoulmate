package tk.newsoulmate.web.common.filter;

import tk.newsoulmate.domain.vo.Member;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "manageFilter",
servletNames = {
        "AjaxGradeAllowController","AjaxGradeRejectController","ManageAdoptApplyDetailController",
        "ManageAdoptApplyListController","ManageGradeController","ManageMember",
        "ManageMemberDeleteController","ManageMemberSelect","ManageMemberPage",
        "ManageMemberUpdateGradeController","ManageReportDetailController","ManageReportList",
        "ReportStatus","ManageSubReadController","ManageSupportAllHistory","ManageSupportAllHistoryPage",
        "ManageSupportApproveController","ManageSupport","ManageSupportPage"})
/**
 * 사이트 관리자에 전용페이지에 대한 다른 사용자들의 접근을 막는 필터
 */
public class ManageFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session=((HttpServletRequest)request).getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        if(loginUser==null||!loginUser.getMemberGrade().isSITE_MANAGER()){
            session.setAttribute("errorMsg","잘못된 접근입니다.");
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath());

        }else {
            chain.doFilter(request, response);
        }

    }
}
