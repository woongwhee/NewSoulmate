<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.UUID"%>
<%
    //한글 인코딩을 위한 UTF-8 설정
    String title = request.getParameter("adTitle");
    String content = request.getParameter("adContent");
%>
<%
    String fileInfo = "";
    String fileName = request.getHeader("file-name");
    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);

        //이미지이므로 신규 파일로 디렉토리 설정 및 업로드
        //파일 기본경로
        String defaultPath  = request.getSession().getServletContext().getRealPath("/");
        String filePath = defaultPath  + "/smarteditor2/upload/" + File.separator;

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String autoFileName = UUID.randomUUID()+ "." + fileExt;
        String realFileName = filePath + autoFileName;

        InputStream is = request.getInputStream();
        OutputStream os = new FileOutputStream(realFileName);
        int num;

        byte bt[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
        while ((num = is.read(bt, 0, bt.length)) != -1) {
            System.out.println("111");
            os.write(bt, 0, num);
        }
        if (is != null) {
            is.close();
        }
        os.flush();
        os.close();

        fileInfo += "&bNewLine=true&sFileName="+ fileName+"&sFileURL="+"/NewSoulmate/smarteditor2/upload/"+autoFileName;
        out.println(fileInfo);

%>

제목  <%=title%> <br>
내용 <br> <%=content%>
사진 <%=fileInfo%>