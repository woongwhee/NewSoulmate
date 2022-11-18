package tk.newsoulmate.web.support.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaymentResult", value = "/paymentresult")
public class PaymentResultController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("pg_token");
        RequestDispatcher view = request.getRequestDispatcher("/views/support/PaymentResult.jsp");

        request.setAttribute("token", token);
        view.forward(request, response);

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
