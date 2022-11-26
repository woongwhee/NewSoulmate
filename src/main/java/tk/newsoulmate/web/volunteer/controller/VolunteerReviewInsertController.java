package tk.newsoulmate.web.volunteer.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "VolunteerReviewInsertController", value = "/volunteerRevInsert")
public class VolunteerReviewInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardTitle=request.getParameter("boardTitle");
        String boardContent=request.getParameter("boardContent");
        String volunteerDate_=request.getParameter("volunteerDate");
        Date volunteerDate=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            volunteerDate= new Date (sdf.parse(volunteerDate_).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        HttpSession session=request.getSession();
        Integer bno= (Integer) session.getAttribute("bno");
        int memberNo=((Member)session.getAttribute("loginUser")).getMemberNo();
        VolunteerService vs=new VolunteerService();
        if(bno==null){
            bno=vs.selectBoardNo();
        }else{
            session.removeAttribute("bno");
        }
        Board board=Board.enrollBoard(memberNo,bno.intValue(),volunteerDate, BoardType.VOLUNTEER,boardTitle,boardContent );
        int result=vs.insertBoard(board);
        if(result>0){
            session.setAttribute("alertMsg","게시글작성 성공");
        }else{
            session.setAttribute("erorrtMsg","게시글작성 실패");
        }
        response.sendRedirect(request.getContextPath()+"/adoptRevList");




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
