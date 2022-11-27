package tk.newsoulmate.web.shelter.controller;

import com.google.gson.Gson;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Member;
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
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*20 //20메가
)
public class NoticeReplyInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberNo=((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
        String replyContent=request.getParameter("replyContent");
        long noticeNo= Long.parseLong(request.getParameter("noticeNo"));
        Reply r=new Reply(memberNo,noticeNo,replyContent);
        Part p=request.getPart("upfile");
        Attachment at=null;
        int result=0;
        if(p!=null||!p.getSubmittedFileName().equals("")){
            UploadUtil uu=UploadUtil.create(request.getServletContext());
            at=uu.saveFiles(p,uu.createFilePath());
            r.setReplyType(ReplyType.PICTURE);
        }else{
            r.setReplyType(ReplyType.NORMAL);
        }
        result = new CommonService().insertNoticeReply(r,at);
        if(result>0) {
            request.getSession().setAttribute("alertMsg", "댓글작성성공");
            response.sendRedirect(request.getContextPath() + "/noticeDetail?dno=" + noticeNo);
        }else{
            request.getSession().setAttribute("errorMsg", "댓글작성실패");
            response.sendRedirect(request.getContextPath() + "/noticeDetail?dno=" + noticeNo);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
