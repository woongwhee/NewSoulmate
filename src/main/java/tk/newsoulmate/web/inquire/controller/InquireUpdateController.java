package tk.newsoulmate.web.inquire.controller;

import com.oreilly.servlet.MultipartRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tk.newsoulmate.domain.dao.BoardDao;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Member;
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

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberNo =((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        String title = request.getParameter("boardTitle");
        String content = request.getParameter("boardContent");
        int category = Integer.parseInt(request.getParameter("categoryNo"));
        Part p=request.getPart("upfile");
        Board b=Board.updateInquire(boardNo,memberNo,category,title,content);

        InquireService is = new InquireService();
        int result = is.updateBoard(b);
        if (result==0) {
            error(request, response);
            return;
        }
        String delete = request.getParameter("deleteFile");
        if (p.getSubmittedFileName().equals("")&&delete.equals("none")) {
            //새로 업로드된 파일이 없다.
            success(request, response, boardNo);
            return;
        }
        //새로 업로드된 파일이 있다.
        UploadUtil uplodUtil=UploadUtil.create(request.getServletContext());
        if (delete.equals("delete")) {//삭제된 파일이 있는경우
            int originFileNo =Integer.parseInt(request.getParameter("originFileNo"));
            Attachment at = is.selectAttachment(boardNo);
            if (at.getFileNo()==originFileNo){
                if(uplodUtil.deleteFile(at)){
                    System.out.println("삭제됨");
                    is.deleteAttachment(originFileNo);
                }
            }
        }
        Attachment at = uplodUtil.saveFiles(p,uplodUtil.createFilePath());
        at.setBoardNo(boardNo);
        result = is.insertNewAttachment(at);
        if(result>0) {
            success(request, response, boardNo);
        }else{
            error(request, response);
        }


    }
    private void success(HttpServletRequest request, HttpServletResponse response, int boardNo) throws IOException {
        request.setAttribute("alertMsg", "게시글 수정완료.");
        response.sendRedirect(request.getContextPath() + "/inquireDetail?bno="+ boardNo);
    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMsg", "게시글 수정실패.");
        request.getRequestDispatcher(request.getContextPath()).forward(request, response);
    }


}
