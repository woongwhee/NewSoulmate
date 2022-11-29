package tk.newsoulmate.web.shelter.controller;

import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "shelterList", value = "/shelterList")
public class ShelterListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Shelter> sList;
        if(request.getParameterMap().containsKey("villageNo") &&  request.getParameterMap().containsKey("cityNo")){
            int villageNo = Integer.parseInt(request.getParameter("villageNo"));
            int cityNo = Integer.parseInt(request.getParameter("cityNo"));

            if (villageNo == 0 && cityNo == 0) { // 둘다 선택 x  --> 둘다 전체
                sList = new ShelterService().selectList();

            } else if (villageNo == 0) { // 시군구만 선택 x
                sList = new ShelterService().selectShelterByCity(cityNo);

            } else { // 둘다 선택
                sList = new ShelterService().selectShelterByVillage(villageNo);
            }
        }else{
            sList = new ShelterService().selectList();
        }
        request.setAttribute("sList",sList);
        request.getRequestDispatcher("/views/shelter/shelterList.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
