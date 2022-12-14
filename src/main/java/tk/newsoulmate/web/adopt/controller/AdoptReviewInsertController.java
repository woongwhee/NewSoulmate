package tk.newsoulmate.web.adopt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.dto.response.response.GsonDateFormate;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;


@WebServlet(name = "adoptRevInsert", value = "/adoptRevInsert")
public class AdoptReviewInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Date.class, new GsonDateFormate()).
                create();
        JsonObject jobj = new JsonObject();
        response.setContentType("application/json; charset=UTF-8");
        Integer fileCount = (Integer) session.getAttribute("fileCount");
        if (fileCount == null) {
            jobj.addProperty("result", 0);
            jobj.addProperty("msg", "첨부파일이 1개 이상 필요합니다.");
            gson.toJson(jobj, response.getWriter());
            return;
        }else{
            session.removeAttribute("fileCount");
        }

        AdoptService as = new AdoptService();
        Board b = gson.fromJson(request.getParameter("board"), Board.class);
        int memberNo = ((Member) session.getAttribute("loginUser")).getMemberNo();
        b.setMemberNo(memberNo);
        b.setFileCount(fileCount);
        Integer bno = (Integer) session.getAttribute("bno");
        if (bno == null) {
            bno = as.selectBoardNo();
        } else {
            session.removeAttribute("bno");
        }
        b.setBoardNo(bno);

        int result = as.insertBoard(b);
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
