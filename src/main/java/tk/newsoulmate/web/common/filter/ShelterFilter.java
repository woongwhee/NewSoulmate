package tk.newsoulmate.web.common.filter;

import tk.newsoulmate.domain.vo.Member;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "shelterFilter",
    servletNames = {
            "shelterInfoServlet","ShelterSupportController","ShelterMessageController",
            "ShelterSupportServlet","SupportWithdrawController"
    }
)
/**
 * 보호소 관계자 페이지에대한 일반사용자(USER)의 접근을 막는 필터
 */
public class ShelterFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session=((HttpServletRequest)request).getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        if(loginUser==null||loginUser.getMemberGrade().isUSER()){
            session.setAttribute("errorMsg","잘못된 접근입니다.");
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath());

        }else {
            chain.doFilter(request, response);
        }
    }
}
