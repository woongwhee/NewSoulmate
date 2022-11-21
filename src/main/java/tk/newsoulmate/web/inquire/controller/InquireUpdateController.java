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

@WebServlet(name = "InquireUpdateController", value = "/inquireUpdate.bo")
public class InquireUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if(ServletFileUpload.isMultipartContent(request)) {
            int maxSize = 1024* 1024* 20;

            String savePath = request.getSession().getServletContext().getRealPath("/resources/inquire_upfiles/");

            MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

            int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
            String categoryName = multiRequest.getParameter("categoryName");
            String boardTitle = multiRequest.getParameter("boardTitle");
            String boardContent = multiRequest.getParameter("boardContent");

            Board b = Board.updateInquire(boardNo,categoryName,boardTitle,boardContent);

            Attachment at = null;

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
