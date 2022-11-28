package tk.newsoulmate.web.adopt.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
import tk.newsoulmate.domain.vo.response.GsonDateFormate;
import tk.newsoulmate.domain.vo.type.BoardType;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.common.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "adoptUpdate", value = "/adopt/update")
public class AdoptReviewUpdateController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("application/json; charset=UTF-8");
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Date.class, new GsonDateFormate()).
                create();
           Board b = gson.fromJson(request.getParameter("board"), Board.class);

        JsonObject jobj = new JsonObject();
        int memberNo=((Member)session.getAttribute("loginUser")).getMemberNo();
        b.setMemberNo(memberNo);
        AdoptService as=new AdoptService();
        List<Attachment>aList=as.selectAttachmentList(b.getBoardNo());
        List<Attachment>dList=checkDelete(b,aList);;
        if(aList.size()==dList.size()){
            jobj.addProperty("msg","첨부파일이 1개 이상 필요합니다.");
            jobj.addProperty("result",0);
            return;
        }
        UploadUtil uu=UploadUtil.create(request.getServletContext());
        for (Attachment at:dList) {
            uu.deleteFile(at);
        }
        as.deleteAttachmentList(dList);
        int result=new AdoptService().updateBoard(b);
        String msg=result==0?"게시글수정실패":"게시글수정성공";
        jobj.addProperty("result",result);
        jobj.addProperty("msg",msg);
        jobj.addProperty("bno",b.getBoardNo());
        gson.toJson(jobj, response.getWriter());
    }
    private List<Attachment> checkDelete(Board b,List<Attachment> aList){
        String content=b.getBoardContent();
        List<Attachment> deletedList=new ArrayList();
        for (Attachment at:aList) {
            if(!content.contains(at.getChangeName())){
                deletedList.add(at);
            }
        }
        return deletedList;
    }


}
