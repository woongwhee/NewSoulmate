package tk.newsoulmate.web.shelter.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "jqAjaxShelter", value = "/shelter/jqAjaxCity")
public class jqAjaxShelterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long cityNo = Long.parseLong(request.getParameter("city"));

        ArrayList<Village> vList2= new ShelterService().selectVillage(cityNo);


        response.setContentType("application/json; charset=UTF-8");
        Gson gson = new Gson();
        gson.toJson(vList2,response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
