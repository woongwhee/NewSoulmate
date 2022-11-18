package tk.newsoulmate.web.adopt.controller;

import tk.newsoulmate.domain.vo.Attachment;
import tk.newsoulmate.web.adopt.sevice.AdoptService;
import tk.newsoulmate.web.common.UploadUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "saveAttach", value = "/saveAttach")
public class nFileSaveController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //파일정보
            String sFileInfo = "";
            //파일명을 받는다 - 일반 원본파일명
            String filename = request.getHeader("file-name");
            //파일 확장자
            String sFilenameExt = filename.substring(filename.lastIndexOf(".")+1);
            //확장자를소문자로 변경
            sFilenameExt = sFilenameExt.toLowerCase();
            //이미지 검증 배열변수
            String[] allowFileArr = {"jpg","jpeg","png","bmp","gif"};
            //확장자 체크
            int nCnt = 0;
            for(int i=0; i<allowFileArr.length; i++) {
                if(sFilenameExt.equals(allowFileArr[i])){
                    nCnt++;
                }
            }
            //이미지가 아니라면
            if(nCnt == 0) {
                PrintWriter print = response.getWriter();
                print.print("NOTALLOW_"+filename);
                print.flush();
                print.close();
            } else {
                //디렉토리 설정 및 업로드

                //파일경로
                UploadUtil uu=UploadUtil.create(request.getServletContext());
                String filePath =uu.createFilePath();
                int fileSize=Integer.parseInt(request.getHeader("file-size"));
                Attachment at=uu.saveFiles(request,filename,filePath,fileSize);
                if(at!=null){
                    HttpSession session=request.getSession();
                    Integer bno=(Integer)session.getAttribute("bno");
                    AdoptService as=new AdoptService();
                    if(bno==null){
                        bno=as.selectBoardNo();
                    }
                    session.setAttribute("bno",bno);
                    at.setBoardNo(bno);
                    int result=as.insertAttachment(at);
                }
                // 정보 출력
                System.out.println(at.getFilePath());
                sFileInfo += "&bNewLine=true";
                // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
                sFileInfo += "&sFileName="+ filename;
                sFileInfo += "&sFileURL="+at.getFilePath()+File.separator+at.getChangeName();
                PrintWriter printWriter = response.getWriter();
                printWriter.print(sFileInfo);
                printWriter.flush();
                printWriter.close();
            }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
