package tk.newsoulmate.web.common.controller;

import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShelterSearchController", value = "/ShelterSearch")
public class ShelterSearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<City> cList = new ShelterService().selectCity();
        ArrayList<Village> vList = new ShelterService().selectVillage(cList.get(0).getCityNo());
        request.setAttribute("cList",cList);
        request.setAttribute("vList",vList);

        request.getRequestDispatcher("/views/common/shelterSearch.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
