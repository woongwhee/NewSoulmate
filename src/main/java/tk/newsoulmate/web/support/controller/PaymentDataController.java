package tk.newsoulmate.web.support.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PaymentData", value = "/PaymentData")
public class PaymentDataController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");
        //결제 정보를 가져오기
        int price = Integer.parseInt(request.getParameter("price"));
        String supportNo = request.getParameter("supportNo");

/*        SupportService service = new SupportService();
        //supportNo 찾아서 payment에 insert
        int result = service.insertPayment(price, supportNo);*/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
