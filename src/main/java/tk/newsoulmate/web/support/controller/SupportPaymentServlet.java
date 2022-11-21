package tk.newsoulmate.web.support.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.shelter.service.ShelterService;

@WebServlet(name = "supportPaymentPage", value = "/supportPaymentPage")
public class SupportPaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Shelter> sList = new ShelterService().selectList();
        request.setAttribute("sList", sList);
        request.getRequestDispatcher("views/support/supportPayment.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
