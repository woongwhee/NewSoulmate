package tk.newsoulmate.web.adopt.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.web.adopt.sevice.AdoptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReplyListController", value = "/rList.bo")
public class ReplyListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        ArrayList<Reply> list = new AdoptService().selectReplyList(boardNo);

        response.setContentType("application/json; charset=UTF-8");
        new Gson().toJson(list , response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
