package tk.newsoulmate.web.inquire.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;

import tk.newsoulmate.web.common.UploadUtil;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@WebServlet(name = "InquireInsertController", value = "/inquireInsert.bo")
@MultipartConfig(
    fileSizeThreshold = 1024*1024,
    maxFileSize = 1024*1024*20 //20메가
)
public class InquireInsertController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String categoryNo_ = request.getParameter("categoryNo");
            int categoryNo = Integer.parseInt(categoryNo_);
            String boardTitle = request.getParameter("boardTitle");
            String boardContent = request.getParameter("boardContent");
            String memberNo = request.getParameter("memberNo");
            System.out.println(categoryNo);
            Board b = Board.insertInquire(categoryNo,boardTitle, boardContent, memberNo);
            Attachment at = null;
            Part p=request.getPart("upfile");
            if(p!=null&&!p.getSubmittedFileName().equals("")) {
                UploadUtil uploadUtil = UploadUtil.create(request.getServletContext());
                at = uploadUtil.saveFiles(p, uploadUtil.createFilePath());
            }
            int result = new InquireService().insertInquire(b,at);
            if(result>0){
                request.getSession().setAttribute("alertMsg","문의작성 성공");
                response.sendRedirect(request.getContextPath()+"/inquire");

            }else{
                request.getSession().setAttribute("errorMsg","문의작성 실패");
                response.sendRedirect(request.getContextPath());
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
