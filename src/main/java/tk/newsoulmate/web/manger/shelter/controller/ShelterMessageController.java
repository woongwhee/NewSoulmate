package tk.newsoulmate.web.manger.shelter.controller;

import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShelterMessageController", value = "/ShelterMessage")
public class ShelterMessageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Subscription> scriptList = new ShelterMangerService().subscriptionList();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
