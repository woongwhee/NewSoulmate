package tk.newsoulmate.web.inquire.controller;

import com.oreilly.servlet.MultipartRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.domain.vo.Board;

import tk.newsoulmate.web.common.MyFileRenamePolicy;
import tk.newsoulmate.web.inquire.service.InquireService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "InquireInsertController", value = "/inquireInsert.bo")
public class InquireInsertController extends HttpServlet {
    public InquireInsertController(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if(ServletFileUpload.isMultipartContent(request)){

            int maxSize = 1024 * 1024 * 20;

            String savePath = request.getSession().getServletContext().getRealPath("/resources/inquire_upfiles/");

            MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

            // -  DB에 기록할 데이터들을 뽑아서 Attachment객체에 담기
            // - 카테고리번호, 제목, 내용, 작성자회원번호 -> Board에 INSERT
            // - 넘어온 첨부파일이 있다면 원본명, 수정명, 폴더경로를 뽑아서 -> Attachment에 INSERT

            String categoryNo = multipartRequest.getParameter("categoryNo");
            String boardTitle = multipartRequest.getParameter("boardTitle");
            String boardContent = multipartRequest.getParameter("boardContent");
            String memberNo = multipartRequest.getParameter("memberNo");
            System.out.println(categoryNo);
            Board b = Board.insertInquire(categoryNo,boardTitle, boardContent, memberNo);
            Attachment at = null;
            if(multipartRequest.getOriginalFileName("upfile") != null){
                at = new Attachment();
                at.setOriginName(multipartRequest.getOriginalFileName("upfile")); //원본파일명
                at.setChangeName(multipartRequest.getFilesystemName("upfile")); //수정파일명
                at.setFilePath("resources/board_upfiles/");
            }else{
                System.out.println("저장안되는데?");
            }
            int result = new InquireService().insertInquire(b, at);

            if(result > 0){ // 성공시 => inquire.bo?currentPage=1
                request.getSession().setAttribute("alertMsg", "게시글 작성을 성공했습니다!");
                response.sendRedirect(request.getContextPath()+"/inquire?currentPage=1");

            } else { // 실패시 => 첨부파일이 있었을겨우
                if(at != null){
                    new File(savePath + at.getChangeName()).delete();
                }
                request.getSession().setAttribute("alertMsg", "게시글 작성 실패");
                response.sendRedirect(request.getContextPath()+"inquireInsert.bo");

            }



        }






    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
