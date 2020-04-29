<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Preview Homework</title>
    <style>
        <%@include file="/view/css/style.css"%>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<div align="center">
    <iframe src = "${pageContext.request.contextPath}/homework/get?id=${homeworkId}" width='1000' height='600' allowfullscreen webkitallowfullscreen></iframe>
</div>
</body>
</html>
