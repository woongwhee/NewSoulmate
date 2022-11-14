package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Subscription;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "adoptApplyInsertController", value = "/adoptApplyInsertController")
public class adoptApplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        long animalNo = Long.parseLong(request.getParameter("animalNo"));
        String telNum = request.getParameter("telNum");
        String gender = request.getParameter("gender");
        String adoptReason = request.getParameter("adoptReason");
        String agreement = request.getParameter("agreement");
        String whenSick = request.getParameter("whenSick");
        String bigDuty = request.getParameter("bigDuty");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
        try {
            Date date = sdf.parse("wishDate");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //DateFormat subRead = new SimpleDateFormat(request.getParameter("subDate"));

        Subscription sb = new Subscription();
        sb.setName(name);
        sb.setAnimalNo(animalNo);
        sb.setGender(gender);
        sb.setTelNum(telNum);
        sb.setAdoptReason(adoptReason);
        sb.setAgreement(agreement);
        sb.setWhenSick(whenSick);
        sb.setBigDuty(bigDuty);
        sb.setWishDate(wishDate);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
