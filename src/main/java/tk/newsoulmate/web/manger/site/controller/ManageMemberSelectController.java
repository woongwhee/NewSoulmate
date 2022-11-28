package tk.newsoulmate.web.manger.site.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
