package tk.newsoulmate.web.manger.site.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import tk.newsoulmate.domain.vo.response.ManageSupportResponse;
import tk.newsoulmate.web.transfer.service.TransferService;

@WebServlet(name = "ManageSupport", value = "/manageSupport")
public class ManageSupportController extends HttpServlet {

    private final TransferService transferService;

    public ManageSupportController() {
        this.transferService = new TransferService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ManageSupportResponse> transfers = transferService.findAll();

        request.setAttribute("transfers", transfers);
        request.getRequestDispatcher("/views/manager/manageSupportHistory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
