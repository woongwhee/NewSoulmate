package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Subscription;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "adoptApplyInsertController", value = "/adoptApplyInsert")
public class adoptApplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 신청서 번호는 넥스트바로 가능.
        int memberNo = 1;
        String name = request.getParameter("name");
        // 유효한 animalNo인지 확인해서
        // 만약에 잘못되었다면 alert창 띄어주기
        // 잘 썻다면 입양 신청 db에 저장 -> 입양신청이 성공적으로 되었습니다. 페이지 만들까??

        String animalNo = request.getParameter("animalNo");
        String telNum = request.getParameter("telNum");
        String gender = request.getParameter("gender");
        String adoptReason = request.getParameter("adoptReason");
        String agreement = request.getParameter("agreement");
        String whenSick = request.getParameter("whenSick");
        String bigDuty = request.getParameter("bigDuty");
        String wishDate_ = request.getParameter("wishDate");
        String subRead = request.getParameter("subRead");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date wishDate= null;
        try {
            wishDate =new Date( df.parse(wishDate_).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        long shelterNo = new AdoptService().shelterNoByName(animalNo);
        Subscription sb = new Subscription();
        sb.setMemberNo(memberNo);
        sb.setName(name);
        sb.setAnimalNo(Long.parseLong(animalNo));
        sb.setTelNum(telNum);
        sb.setGender(gender);
        sb.setAdoptReason(adoptReason);
        sb.setFamilyAgreement(agreement);
        sb.setWhenSick(whenSick);
        sb.setBigDuty(bigDuty);
        sb.setWishDate(wishDate);
        sb.setSubRead(subRead);
        sb.setShelterNo(shelterNo);

        int checkAnimal = new AdoptService().checkAnimal(animalNo);
        if(checkAnimal==0){
            request.getSession().setAttribute("alertMsg","입력하신 공고번호는 존재하지 않는 번호입니다.");
            request.getRequestDispatcher("/views/adopt/adoptApply.jsp").forward(request,response);
            return;
        }
        int result = new AdoptService().insertSubscription(sb);


        if(checkAnimal > 0 && result>0){ // 입양신청 성공 -> alert띄우고 메인페이지로 이동
            request.getSession().setAttribute("alertMsg","입양신청이 성공적으로 되었습니다.");
            response.sendRedirect(request.getContextPath());
        }else{ // 다시 입양신청 페이지로
            request.getSession().setAttribute("alertMsg","입양신청에 실패하였습니다.");
            request.getRequestDispatcher("/views/adopt/adoptApply.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}