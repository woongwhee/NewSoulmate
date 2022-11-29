package tk.newsoulmate.web.volunteer.controller;


import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Volunteer;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;


@WebServlet(name = "VolunteerApplyEnrollController", value = "/volunteerApplyEnroll")
public class VolunteerApplyEnrollController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");
        int memberNo = loginUser.getMemberNo();

        String name = request.getParameter("name");
        String wishDate = request.getParameter("wishDate");
        java.sql.Date sqlDate = java.sql.Date.valueOf(wishDate);

        String telNumber = request.getParameter("telNum");
        String gender = request.getParameter("gender");
        long shelterNo = Long.parseLong(request.getParameter("shelterNo"));

        Volunteer vol = new Volunteer();
        vol.setName(name);
        vol.setTelNumber(telNumber);
        vol.setMemberNo(memberNo);
        vol.setStartDate(sqlDate);
        vol.setGender(gender);
        vol.setShelterNo(shelterNo);

        int result = new VolunteerService().volApplyInset(vol);

        if(result > 0) {
            request.getSession().setAttribute("alertMsg", "봉사신청이 완료되었습니다");
        }else{
            request.getSession().setAttribute("alertMsg","서버요청 실패");
        }
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
