package tk.newsoulmate.web.inquire.controller;

import com.oreilly.servlet.MultipartRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.web.common.MyFileRenamePolicy;
import tk.newsoulmate.web.common.UploadUtil;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "InquireUpdateController", value = "/inquire/update")
public class InquireUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberNo = Integer.parseInt(request.getParameter("memberNo"));
        InquireService is = new InquireService();
        String title = request.getParameter("boardTitle");
        String content = request.getParameter("boardContent");
        int category = Integer.parseInt(request.getParameter("categoryNo"));
        String delete = request.getParameter("deleteFile");
        System.out.println(delete);


        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        Part p=request.getPart("upfile");
        Board b=Board.updateInquire(boardNo,category,title,content);
        int result = is.updateBoard(b);
        if (result==0) {
            request.setAttribute("errorMsg", "게시글 수정실패.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            return;
        }
        if (p.getSubmittedFileName().equals("")) {//새로 업로드된 파일이 없다.
            request.setAttribute("alertMsg", "게시글 수정완료.");
            response.sendRedirect(request.getContextPath() + "detail.bo?bno=" + boardNo);
            return;
        }
        if (delete == null || delete.equals("none")) {
            if (p.getSubmittedFileName().equals("")) {

            } else {

            }
        }
        //새로 업로드된 파일이 있다.
        UploadUtil uplodUtil=UploadUtil.create(request.getServletContext());
        if (delete != null && delete.equals("delete")) {//삭제된 파일이 있는경우
            int originFileNo =Integer.parseInt(request.getParameter("originFileNo"));
            Attachment at = is.selectAttachment(boardNo);
            if (at.getFileNo()==originFileNo);
            if(uplodUtil.deleteFile(at)){
                System.out.println("삭제됨");
                bs.deleteAttachment(originFileNo);
            }
        }
        Attachment at = uplodUtil.saveFiles(p,uplodUtil.createFilePath(category));
        at.setRefNo(boardNo);
        result = bs.insertAttachment(at);
        if(result>0) {
            request.setAttribute("alertMsg", "게시글 수정완료.");
            response.sendRedirect(request.getContextPath() + "/detail.bo?bno="+boardNo);
        }else{
            request.setAttribute("errorMsg", "첨부파일 수정실패.");
            request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
