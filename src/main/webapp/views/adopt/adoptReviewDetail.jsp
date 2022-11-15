<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-08
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="tk.newsoulmate.domain.vo.Board"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.text.SimpleDateFormat"%>
<% 	Board b = (Board) request.getAttribute("b"); %>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<html>
<head>
    <title>입양후기 상세보기</title>
    <link rel="stylesheet" href="CSS/common/adoptDetail.css" type="text/css">
    <script src="JS/common/adoptDetail.js"></script>
</head>
<body>

<div class="outer">
    <table id="detail-area">
        <tr id="title">
            <th>제목</th>
            <td><%=b.getBoardTitle() %></td>
        </tr>
<%--        <tr>--%>
<%--            <th>작성자</th>--%>
<%--            <td><%=b.getMemberName()%></td>--%>
<%--            <th>작성일</th>--%>
<%--            <td><%=b.getCreateDate()%></td>--%>
<%--            <th>입양날짜</th>--%>
<%--            <td><%=b.getIssueDate()%></td>--%>
<%--            <th>조회수</th>--%>
<%--            <td><%=b.getReadCount()%></td>--%>
<%--        </tr>--%>
        <hr>
        <tr id="content">
            <td>
                <p><%=b.getBoardContent() %></p>
            </td>
        </tr>
    </table>
    <table id="reply-area">
        <thead>
<%--            댓글리스트 출력--%>
        </thead>
        <tbody>
        <tr>
            <td><textarea placeholder="댓글을 작성해주세요"></textarea></td>
            <td> <button onclick="insertReply();">댓글등록</button></td>
        </tr>
        <tr>
            <td><textarea readonly>로그인후 댓글작성이 가능합니다.</textarea></td>
            <td><button disabled>댓글등록</button></td>
        </tr>
        </tbody>
    </table>
</div>

<%
    // 로컬경로에 파일 저장하기 ============================================
    String sFileInfo = "";

    // 파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴
    String name = request.getHeader("file-name");

    // 확장자
    String ext = name.substring(name.lastIndexOf(".")+1);

    // 파일 기본경로
    String defaultPath = request.getServletContext().getRealPath("/");

    // 파일 기본경로 _ 상세경로
    String path = defaultPath + "upload" + File.separator;

    File file = new File(path);
    if(!file.exists()) {
        file.mkdirs();
    }

    String realname = UUID.randomUUID().toString() + "." + ext;
    InputStream is = request.getInputStream();
    OutputStream os = new FileOutputStream(path + realname);
    int numRead;

    // 파일쓰기
    byte bt[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
    while((numRead = is.read(bt,0,bt.length)) != -1) {
        os.write(bt,0,numRead);
    }

    if(is != null) {
        is.close();
    }

    os.flush();
    os.close();

    System.out.println("path : "+path);
    System.out.println("realname : "+realname);

    // 파일 삭제
// 	File f1 = new File(path, realname);
// 	if (!f1.isDirectory()) {
// 		if(!f1.delete()) {
// 			System.out.println("File 삭제 오류!");
// 		}
// 	}

    sFileInfo += "&bNewLine=true&sFileName="+ name+"&sFileURL="+"/upload/"+realname;
    out.println(sFileInfo);

    // ./로컬경로에 파일 저장하기 ============================================
%>
<%--<%--%>
<%--    //파일정보--%>
<%--    String sFileInfo = "";--%>
<%--    //파일명을 받는다 - 일반 원본파일명--%>
<%--    String filename = request.getHeader("file-name");--%>
<%--    //파일 확장자--%>
<%--    String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);--%>
<%--    //확장자를소문자로 변경--%>
<%--    filename_ext = filename_ext.toLowerCase();--%>

<%--    //이미지 검증 배열변수--%>
<%--    String[] allow_file = { "jpg", "png", "bmp", "gif" };--%>

<%--    //돌리면서 확장자가 이미지인지--%>
<%--    int cnt = 0;--%>
<%--    for (int i = 0; i < allow_file.length; i++) {--%>
<%--        if (filename_ext.equals(allow_file[i])) {--%>
<%--            cnt++;--%>
<%--        }--%>
<%--    }--%>

<%--    //이미지가 아님--%>
<%--    if (cnt == 0) {--%>
<%--        out.println("NOTALLOW_" + filename);--%>
<%--    } else {--%>
<%--        String dftFilePath = request.getSession().getServletContext().getRealPath("/");--%>
<%--        String filePath = dftFilePath + "smarteditor2" + File.separator + "multiupload" + File.separator;--%>
<%--        File file = new File(filePath);--%>
<%--        if (!file.exists()) {--%>
<%--            file.mkdirs();--%>
<%--        }--%>
<%--        String realFileNm = "";--%>
<%--        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");--%>
<%--        String today = formatter.format(new java.util.Date());--%>
<%--        realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));--%>
<%--        String rlFileNm = filePath + realFileNm;--%>

<%--        InputStream is = request.getInputStream();--%>
<%--        OutputStream os = new FileOutputStream(rlFileNm);--%>
<%--        int numRead;--%>
<%--        byte bt[] = new byte[Integer.parseInt(request.getHeader("file-size"))];--%>
<%--        while ((numRead = is.read(bt, 0, bt.length)) != -1) {--%>
<%--            os.write(bt, 0, numRead);--%>
<%--        }--%>
<%--        if (is != null) {--%>
<%--            is.close();--%>
<%--        }--%>
<%--        os.flush();--%>
<%--        os.close();--%>

<%--        sFileInfo += "&bNewLine=true";--%>
<%--        sFileInfo += "&sFileName=" + filename;--%>
<%--        sFileInfo += "&sFileURL=/webapp/smarteditor2/multiupload/"+realFileNm;--%>
<%--        out.println(sFileInfo);--%>
<%--    }--%>
<%--%>--%>



</body>
</html>
