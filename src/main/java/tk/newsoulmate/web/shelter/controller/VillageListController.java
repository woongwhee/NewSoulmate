package tk.newsoulmate.web.shelter.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "villageList", value = "/village.ax")
public class VillageListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int villageNo = Integer.parseInt(request.getParameter("village"));
        ArrayList<Shelter> sList2 = new ShelterService().selectShelterByVillage(villageNo);
        response.setContentType("application/json; charset=UTF-8");
        Gson gson = new Gson();
        gson.toJson(sList2,response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
