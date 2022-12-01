package tk.newsoulmate.web.volunteer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.response.GsonDateFormate;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.volunteer.service.VolunteerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "volunteerRevInsert", value = "/volunteerRevInsert")
public class VolunteerReviewInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Date.class, new GsonDateFormate()).
                create();
        JsonObject jobj = new JsonObject();
        response.setContentType("application/json; charset=UTF-8");
        Board b = gson.fromJson(request.getParameter("board"), Board.class);
        if (b.getFileCount()==0) {
            jobj.addProperty("result", 0);
            jobj.addProperty("msg", "첨부파일이 1개 이상 필요합니다.");
            gson.toJson(jobj, response.getWriter());
            return;
        }
        VolunteerService vs = new VolunteerService();

        int memberNo = ((Member) session.getAttribute("loginUser")).getMemberNo();
        b.setMemberNo(memberNo);
        Integer bno = (Integer) session.getAttribute("bno");
        if (bno == null) {
            bno = vs.selectBoardNo();
        } else {
            session.removeAttribute("bno");
        }
        b.setBoardNo(bno);
        int result = vs.insertBoard(b);
        jobj.addProperty("bno", bno);
        jobj.addProperty("result", result);
        if (result > 0)
            jobj.addProperty("msg", "게시글작성성공.");
        else {
            jobj.addProperty("msg", "게시글작성실패.");
        }

        gson.toJson(jobj, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
