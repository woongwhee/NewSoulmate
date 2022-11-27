package tk.newsoulmate.web.shelter.controller;

import tk.newsoulmate.domain.dao.ShelterDao;
import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Village;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateShelterServlet", value = "/updateShelter")
public class UpdateShelterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         int villageNo = Integer.parseInt(request.getParameter("villageNo"));
         int cityNo = Integer.parseInt(request.getParameter("cityNo"));
         String impossible = request.getParameter("impossible");
         // 도 만 정하고, 시군구를 선택하지 않았을떼 --> cityNo 로 shelter 조회
        // 도, 시군구 전체- 로 조회 --> ALL
        // 도, 시군구 선택 --> villageNo 로 조회
        // 도 선택x, 시군구 선택 0 --> 불가능하게 subCategory에 disabled 걸어놓기 -> mainCategory onchange 시 true로 바뀌게


            ArrayList<Shelter> sList = new ArrayList<>();
            if (villageNo == 0 && cityNo == 0) { // 둘다 선택 x  --> 둘다 전체
                sList = new ShelterService().selectList();

            } else if (villageNo == 0) { // 시군구만 선택 x
                sList = new ShelterService().selectShelterByCity(cityNo);

            } else { // 둘다 선택
                sList = new ShelterService().selectShelterByVillage(villageNo);
            }




        ArrayList<City> cList = new ShelterService().selectCity();
        ArrayList<Village> vList = new ShelterService().selectVillage(cList.get(0).getCityNo());
        request.setAttribute("sList",sList);
        request.setAttribute("cList",cList);
        request.setAttribute("vList",vList);
        request.getRequestDispatcher("/views/shelter/updateShelter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
