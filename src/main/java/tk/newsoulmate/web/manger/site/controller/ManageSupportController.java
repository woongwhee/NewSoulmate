package tk.newsoulmate.web.manger.site.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.newsoulmate.domain.vo.Support;
import tk.newsoulmate.web.shelter.service.ShelterService;

@WebServlet(name = "ManageSupport", value = "/manageSupport")
public class ManageSupportController extends HttpServlet {

    private final ShelterService shelterService;

    public ManageSupportController() {
        this.shelterService = new ShelterService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Support> transfers = shelterService.findAllTransfer();

        request.setAttribute("transfers", transfers);
        request.getRequestDispatcher("/views/manager/manageSupportHistory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
