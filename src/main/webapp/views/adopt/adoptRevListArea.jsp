<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${tList}" var="t">
    <div class="adopt-thum">
        <img class="adopt-thumnail" src="${t.filePath}/${t.changeName}"  onclick="location.href='${context}/adoptRevDetail?bno=${t.boardNo}'">
        <p>${t.boardTitle}</p>
    </div>
</c:forEach>
