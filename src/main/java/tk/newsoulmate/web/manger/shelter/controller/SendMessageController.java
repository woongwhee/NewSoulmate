package tk.newsoulmate.web.manger.shelter.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.ExtMessage;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.web.common.controller.MessageController;
import tk.newsoulmate.web.manger.shelter.sevice.ShelterMangerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sendMessagest", value = "/sendMessage.st")
public class SendMessageController extends HttpServlet {
    /**
     * 보호소에서 입양/봉사 신청자에게 보내는 문자에 대한 서블릿
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson=new Gson();
        ExtMessage msg=gson.fromJson(request.getParameter("msg"),ExtMessage.class);
        int fromMemberNo=((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
        msg.setFromMemberNo(fromMemberNo);
        String status=new MessageController().sendMessage(msg);
        PrintWriter out=response.getWriter();
        if(!status.equals("2000")){//문자전송에 실패한경우
            out.print(0);
            return;
        }

        int result=new ShelterMangerService().insertMessage(msg);
        out.print(result);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
