package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.shelter.service.ShelterService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "adoptApplyInsertController", value = "/adoptApplyInsert")
public class adoptApplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 신청서 번호는 넥스트바로 가능.
        int memberNo = 1234;
        String name = request.getParameter("name");

        // 유효한 animalNo인지 확인해서
        // 만약에 잘못되었다면 alert창 띄어주기
        // 잘 썻다면 입양 신청 db에 저장 -> 입양신청이 성공적으로 되었습니다. 페이지 만들까??

        long animalNo = Long.parseLong(request.getParameter("animalNo"));
        String telNum = request.getParameter("telNum");
        String gender = request.getParameter("gender");
        String adoptReason = request.getParameter("adoptReason");
        String agreement = request.getParameter("agreement");
        String whenSick = request.getParameter("whenSick");
        String bigDuty = request.getParameter("bigDuty");
        String wishDate = request.getParameter("wishDate");
        String subRead = request.getParameter("subRead");
        String subDate = request.getParameter("subDate");
        long shleterNo = new ShelterService().shelterNoByName(animalNo);



        Subscription sb = new Subscription();
        sb.setMemberNo(memberNo);
        sb.setName(name);
        sb.setAnimalNo(animalNo);
        sb.setTelNum(telNum);
        sb.setGender(gender);
        sb.setAdoptReason(adoptReason);
        sb.setAgreement(agreement);
        sb.setWhenSick(whenSick);
        sb.setBigDuty(bigDuty);
        sb.setWishDate(wishDate);
        sb.setSubRead(subRead);
        sb.setSubDate(subDate);
        sb.setShelterNo(shleterNo);

        int result = new AdoptService().adoptApplyInsert(sb);









    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
