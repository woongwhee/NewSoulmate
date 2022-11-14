package tk.newsoulmate.web.inquire.controller;

import com.oreilly.servlet.MultipartRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tk.newsoulmate.domain.vo.Board;
import tk.newsoulmate.domain.vo.Category;
import tk.newsoulmate.web.common.MyFileRenamePolicy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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

            String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");

            MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

            String categoryName = multipartRequest.getParameter("categoryName");
            String boardTilte = multipartRequest.getParameter("title");
            String boardContent = multipartRequest.getParameter("content");
            String boardWriter = multipartRequest.getParameter("memberNo");

            Category c = new Category();

            c.setCategoryName(categoryName);
            Board.insertInquire(boardTilte, boardContent, boardWriter);




        }






    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
