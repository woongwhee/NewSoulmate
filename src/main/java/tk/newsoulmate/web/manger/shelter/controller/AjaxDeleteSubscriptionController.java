package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxDeleteSubscriptionController", value = "/DeleteSubscription")
public class AjaxDeleteSubscriptionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int subNo = Integer.parseInt(request.getParameter("sno"));
        int result = new ShelterMangerService().deleteSubscription(subNo);
        response.getWriter().print(result);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
