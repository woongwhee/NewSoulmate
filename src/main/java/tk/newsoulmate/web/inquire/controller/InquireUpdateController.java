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
import java.util.List;

@WebServlet(name = "inquireUpdate", value = "/inquire/update")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*20 //20메가
)
public class InquireUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberNo =((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
        String content = request.getParameter("boardContent");
        String title = request.getParameter("boardTitle");
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        int category = Integer.parseInt(request.getParameter("categoryNo"));
        Part p=request.getPart("upfile");
        Board b=Board.updateInquire(boardNo,memberNo,category,title,content);

        b.setMemberNo(memberNo);
        InquireService is = new InquireService();
        int result = is.updateBoard(b);
        if (result==0) {
            error(request, response,"게시글수정 실패");
            return;
        }
        String delete = request.getParameter("deleteFile");
        UploadUtil uploadUtil=UploadUtil.create(request.getServletContext());
        if (delete.equals("delete")) {//삭제된 파일이 있는경우
            int originFileNo =Integer.parseInt(request.getParameter("originFileNo"));
            List<Attachment> aList = is.selectAttachment(boardNo);
            for (Attachment at: aList) {
                if (at.getFileNo()==originFileNo){
                    if(uploadUtil.deleteFile(at)){
                        is.deleteAttachment(originFileNo);
                    }
                }
            }
        }
        if (p!=null&&!p.getSubmittedFileName().equals("")) {
            //새로 업로드된 파일이 있는경우 .
            Attachment at = uploadUtil.saveFiles(p,uploadUtil.createFilePath());
            at.setBoardNo(boardNo);
            result = is.insertNewAttachment(at);
        }
        if(result>0) {
            success(request, response, boardNo);
        }else{
            error(request, response,"첨부파일 수정 실패");
        }
    }
    private void success(HttpServletRequest request, HttpServletResponse response, int boardNo) throws IOException {
        request.getSession().setAttribute("alertMsg", "게시글 수정완료.");
        response.sendRedirect(request.getContextPath() + "/inquireDetail?bno="+ boardNo);
    }

    private void error(HttpServletRequest request, HttpServletResponse response,String msg) throws ServletException, IOException {
        request.getSession().setAttribute("errorMsg", msg);
        response.sendRedirect(request.getContextPath());
    }


}
