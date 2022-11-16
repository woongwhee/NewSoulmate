package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InquireUpdateFromController", value = "/inquireUpdateForm.bo")
public class InquireUpdateFromController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InquireService iService = new InquireService();
        Member loginUser= (Member) request.getSession().getAttribute("loginUser");
        int boardNo = Integer.parseInt(request.getParameter("bno"));

        ArrayList<Category> list = iService.selectCategoryList();

        Board b = iService.selectInquireBoard(boardNo,loginUser);

        Attachment at = iService.selectInquireAttachment(boardNo);


        request.setAttribute("list",list);
        request.setAttribute("b",b);
        request.setAttribute("at",at);





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
