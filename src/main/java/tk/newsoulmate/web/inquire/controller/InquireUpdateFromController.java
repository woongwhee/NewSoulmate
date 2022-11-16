package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.web.inquire.service.AttachmentService;
import tk.newsoulmate.web.inquire.service.CategoryService;
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
        AttachmentService atService = new AttachmentService();
        CategoryService caService = new CategoryService();

        int boardNo = Integer.parseInt(request.getParameter("bno"));

        ArrayList<Category> list = caService.selectCategoryList();

        Board b = iService.selectInquireBoard(boardNo);

        Attachment at = atService.selectInquireAttachment(boardNo);


        request.setAttribute("list",list);
        request.setAttribute("b",b);
        request.setAttribute("at",at);





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
