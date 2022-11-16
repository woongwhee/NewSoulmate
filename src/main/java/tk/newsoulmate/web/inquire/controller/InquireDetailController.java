package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.*;
import tk.newsoulmate.web.inquire.service.AttachmentService;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InquireDetailController", value = "/inquireDetail.bo")
public class InquireDetailController extends HttpServlet {
    public InquireDetailController(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int boardNo = Integer.parseInt(request.getParameter("bno"));

        InquireService iService = new InquireService();
        AttachmentService atService = new AttachmentService();


        // 조회수 증가 / 게시글 조회(Inquire) / 첨부파일 조회(Attachment)

        int result = iService.increaseCount(boardNo);

        if(result > 0){ // 유효한 게시글일때 => 게시글, 첨부파일 조회 => 상세조회 페이지
            Board b = iService.selectInquireBoard(boardNo);
            Attachment at = atService.selectInquireAttachment(boardNo);

            request.setAttribute("b", b);
            request.setAttribute("at",at);

            request.getRequestDispatcher("views/inquire/inquireDetailView.jsp").forward(request,response);

        } else { // 에러페이지
            request.getSession().setAttribute("alertMsg", "게시글 상세조회 실패");
            response.sendRedirect(request.getContextPath()+"inquireDetail.bo");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
