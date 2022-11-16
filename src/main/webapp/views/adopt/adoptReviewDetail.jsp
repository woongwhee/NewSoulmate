<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    //한글 인코딩을 위한 UTF-8 설정
    request.setCharacterEncoding("utf-8");
    out.println(request.getParameter("adReviewContent"));	// html코드형식의 데이터
%>
<%--<%--%>
<%--    String sFileInfo = "";--%>
<%--    String name = request.getHeader("file-name");--%>
<%--    String ext = name.substring(name.lastIndexOf(".")+1);--%>
<%--    String defaultPath = request.getServletContext().getRealPath("/");--%>
<%--    String path = defaultPath + "upload" + File.separator;--%>

<%--    File file = new File(path);--%>
<%--    if(!file.exists()) {--%>
<%--        file.mkdirs();--%>
<%--    }--%>

<%--    String realname = UUID.randomUUID().toString() + "." + ext;--%>
<%--    InputStream is = request.getInputStream();--%>
<%--    OutputStream os = new FileOutputStream(path + realname);--%>
<%--    int numRead;--%>

<%--    byte bt[] = new byte[Integer.parseInt(request.getHeader("file-size"))];--%>
<%--    while((numRead = is.read(bt,0,bt.length)) != -1) {--%>
<%--        os.write(bt,0,numRead);--%>
<%--    }--%>
<%--    if(is != null) {--%>
<%--        is.close();--%>
<%--    }--%>

<%--    os.flush();--%>
<%--    os.close();--%>

<%--    out.println("path : "+path);--%>
<%--    out.println("realname : "+realname);--%>

<%--    sFileInfo += "&bNewLine=true&sFileName="+ name+"&sFileURL="+"/upload/"+realname;--%>
<%--    out.println(sFileInfo);--%>
<%--%>--%>
