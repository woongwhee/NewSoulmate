package tk.newsoulmate.web.support.controller;

import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.shelter.service.ShelterService;
import tk.newsoulmate.web.support.service.SupportService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SupportHistory", value = "/SupportHistory")
public class SupportHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Support> supportList = new SupportService().selectSupportList();
        request.setAttribute("supportList", supportList);
        request.getRequestDispatcher("views/support/supportHistory.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
