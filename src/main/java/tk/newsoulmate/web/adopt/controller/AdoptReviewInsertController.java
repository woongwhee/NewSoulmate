package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "adoptRevInsert", value = "/adoptRevInsert")
public class AdoptReviewInsertController extends HttpServlet {
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
        Integer bno= (Integer) session.getAttribute("bno");
        int memberNo=((Member)session.getAttribute("loginUser")).getMemberNo();
        AdoptService as=new AdoptService();
        if(bno==null){
            bno=as.selectBoardNo();
        }else{
            session.removeAttribute("bno");
        }
        Board board=Board.enrollBoard(memberNo,bno.intValue(),adoptDate,BoardType.ADOPT,boardTitle,boardContent );
        int result=as.insertBoard(board);
        if(result>0){
            session.setAttribute("alertMsg","게시글작성 성공");
            response.sendRedirect(request.getContextPath()+"/adoptRevList");
        }else{
            session.setAttribute("erorrtMsg","게시글작성 실패");
            response.sendRedirect(request.getContextPath()+"/adoptRevList");

        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
