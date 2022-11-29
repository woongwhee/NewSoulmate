package tk.newsoulmate.web.myPage.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.type.MemberGrade;
import tk.newsoulmate.web.common.UploadUtil;


import tk.newsoulmate.web.manger.site.service.ManageService;
import tk.newsoulmate.web.myPage.service.MyPageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MGradeUpdateController", value = "/mGradeUpdate")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*20 //20메가
)
public class MGradeUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        int memberNo = loginUser.getMemberNo();
        MemberGrade loginGrade = loginUser.getMemberGrade();
        if(loginGrade==MemberGrade.SHELTER_MANAGER){
            error(request,response,"관리자는 등업 신청을 할 수 없습니다.");
            return;
        }
        boolean isSub= new ManageService().isSubmit(memberNo);
        long shelterNo = Long.parseLong(request.getParameter("shelterNo"));
        if(isSub){
            error(request, response,"이미 신청된 회원입니다.");
            return;
        }

        String shelterCompNo = request.getParameter("shelterCompNo");
        String shelterLandLine = request.getParameter("shelterLandLine");
        String shelterTel = request.getParameter("shelterTel");
        String shelterAddress = request.getParameter("shelterAddress");

        GradeUp up = new GradeUp(memberNo,shelterNo,shelterTel,shelterLandLine,shelterCompNo,shelterAddress);

        Attachment at = null;
        Part p=request.getPart("upFile");
        if(p!=null&&!p.getSubmittedFileName().equals("")) {
            UploadUtil uploadUtil = UploadUtil.create(request.getServletContext());
            at = uploadUtil.saveFiles(p , uploadUtil.createFilePath());
        }
        int result = new MyPageService().gradeUp(up,at);
        if(result >0){
            request.getSession().setAttribute("alertMsg","등업신청성공");
            response.sendRedirect(request.getContextPath()+"/myPageShelter");
        }else {
            request.getSession().setAttribute("errorMsg","등업신청 실패");
            response.sendRedirect(request.getContextPath());
        }


    }

    private static void error(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
        request.getSession().setAttribute("errorMsg", msg);
        response.sendRedirect(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
