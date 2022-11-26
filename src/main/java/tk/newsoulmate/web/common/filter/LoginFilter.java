package tk.newsoulmate.web.common.filter;

import tk.newsoulmate.domain.vo.Member;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter(filterName = "loginFilter",
    servletNames = {
        "saveAttach","AdoptApplyController","InquireDetailController","MemberInfoDetailViewController",
            "adoptRevEnroll","adoptRevInsert","adoptUpdate","replyInsert.ad","replyInsert","replyDelete",
            "ManageAdoptApplyListController","NoticeReplyInsertController"
    }
)
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session=((HttpServletRequest)request).getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        System.out.println(loginUser);
        if(loginUser==null){
            session.setAttribute("errorMsg","로그인 후 접근 할 수 있는 페이지입니다.");
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/loginpage");

        }else {
            chain.doFilter(request, response);
        }


    }
}
