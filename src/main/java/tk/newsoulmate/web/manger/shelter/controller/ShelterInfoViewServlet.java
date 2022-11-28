package tk.newsoulmate.web.manger.shelter.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.awt.*;
import java.io.IOException;

import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.web.shelter.service.ShelterService;

@WebServlet(name = "UpdateShelterInfo", value = "/UpdateShelterInfo")
public class ShelterInfoViewServlet extends HttpServlet {

    private final ShelterService shelterService;

    public ShelterInfoViewServlet() {
        this.shelterService = new ShelterService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath());
        } else {
            Shelter shelter = shelterService.findShelter(loginUser.getShelterNo());
            request.setAttribute("shelter", shelter);
            request.getRequestDispatcher("/views/shelterManager/shelterInfoView.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
