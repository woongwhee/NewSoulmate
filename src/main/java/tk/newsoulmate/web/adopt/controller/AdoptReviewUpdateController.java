package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AdoptReviewEnrollController", value = "/adopt/update")
public class AdoptReviewUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardTitle=request.getParameter("boardTitle");
        String boardContent=request.getParameter("boardContent");
        String adoptDate_=request.getParameter("adoptDate");
        Date adoptDate=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            adoptDate= new Date (sdf.parse(adoptDate_).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        HttpSession session=request.getSession();
        int memberNo=((Member)session.getAttribute("loginUser")).getMemberNo();
        Board board=Board.enrollBoard(memberNo,bno,adoptDate, BoardType.ADOPT,boardTitle,boardContent );
        int result=new AdoptService().updateBoard(board);
        if(result>0){
            session.setAttribute("alertMsg","게시글수정 성공");
        }else{
            session.setAttribute("erorrtMsg","게시글수정 실패");
        }
        response.sendRedirect(request.getContextPath()+"adoptRevDetail?bno="+bno);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
