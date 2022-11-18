package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InquireDeleteController", value = "/inquireDelete.bo")
public class InquireDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int boardNo = Integer.parseInt(request.getParameter("bno"));

        int result = new InquireService().deleteInquireBoard(boardNo);

        if(result > 0){
            request.getSession().setAttribute("alertMsg","성공적으로 게시글을 삭제했습니다.");
            response.sendRedirect(request.getContextPath()+"/inquire");
        } else {
            request.setAttribute("alertMsg","게시글 삭제 실패했습니다");

        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
