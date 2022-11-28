package tk.newsoulmate.web.manger.shelter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.shelter.service.ShelterService;

@WebServlet(name = "UpdateShelterInfoController", value = "/shelter/update")
public class UpdateShelterInfoController extends HttpServlet {

    private final ShelterService shelterService;

    public UpdateShelterInfoController() {
        this.shelterService = new ShelterService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Shelter updateReq = new Gson().fromJson(request.getReader(), Shelter.class);

        int result = shelterService.updateShelter(updateReq);

        if (result > 0) {
            response.setStatus(200);
        } else {
            response.setStatus(500);
        }
    }

}
