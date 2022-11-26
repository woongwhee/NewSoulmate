package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.City;
import tk.newsoulmate.domain.vo.ManageMember;
import tk.newsoulmate.domain.vo.Shelter;
import tk.newsoulmate.domain.vo.Village;

import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ManageMemberSelect", value = "/manageMemberSelect")
public class ManageMemberSelectController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*
        int userGrade = Integer.parseInt(request.getParameter("userGrade"));
        int shelterGrade = Integer.parseInt(request.getParameter("shelterGrade"));

        ArrayList<ManageMember> mList = new ArrayList<>();
        if (userGrade == 0) {
            mList = new ManageService().selectMemberList();

        } else if (shelterGrade == 1) {
            mList = new ManageService().selectMemberList();

        }

        request.getRequestDispatcher("/views/shelter/manageMemberList.jsp").forward(request, response);
*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
