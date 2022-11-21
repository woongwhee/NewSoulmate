package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InquireEnrollController", value = "/inquireEnroll.bo")

public class InquireEnrollController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Category> list = new InquireService().selectCategoryList();

        request.setAttribute("list", list);
        request.getRequestDispatcher("/views/inquire/inquireEnrollView.jsp").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
