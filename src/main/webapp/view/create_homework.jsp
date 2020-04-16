<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Homework</title>
    <style>
        <%@include file="/view/css/style.css"%>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<form id="form" action="upload?course_id=${courseId}" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="submit" class="button" value="Upload"/>
</form>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>
