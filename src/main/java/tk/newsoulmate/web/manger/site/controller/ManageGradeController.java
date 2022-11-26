package tk.newsoulmate.web.manger.site.controller;

import tk.newsoulmate.domain.vo.GradeUp;
import tk.newsoulmate.web.manger.site.service.ManageService;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ManageGradeController", value = "/manageGrade")
public class ManageGradeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ArrayList<Member> mList = new ManageService().selectManageMember();
        ArrayList<GradeUp> gList = new ManageService().selectGradeUp();
        //ArrayList<Shelter> sList = new ShelterService().selectList();

        //request.setAttribute("mList",mList);
        request.setAttribute("gList",gList);
        //request.setAttribute("sList",sList);
        request.getRequestDispatcher("/views/manager/managerMemberShelter.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
