package tk.newsoulmate.web.shelter.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Reply;
import tk.newsoulmate.domain.vo.type.ReplyType;
import tk.newsoulmate.web.common.UploadUtil;
import tk.newsoulmate.web.common.service.CommonService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "NoticeReplyInsertController", value = "/replyInsert.no")
@MultipartConfig
public class NoticeReplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        System.out.println("뭐지" + request.getParameter("reply"));
        Reply r = gson.fromJson(request.getParameter("reply"), Reply.class);
        Part p=request.getPart("upfile");
        Attachment at=null;
        if(p.getSubmittedFileName().equals("")){
            r.setReplyType(ReplyType.NORMAL);

        }else{
            UploadUtil uu=UploadUtil.create(request.getServletContext());
            at=uu.saveFiles(p,uu.createFilePath());
            r.setReplyType(ReplyType.PICTURE);
        }
        int result = 0;
        result = new CommonService().insertNoticeReply(r,at);
        response.getWriter().print(result);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
