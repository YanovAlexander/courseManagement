<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Preview Homework</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<div align="center">
    <iframe src = "${pageContext.request.contextPath}/homework/get?id=${homeworkId}" width='1000' height='600' allowfullscreen webkitallowfullscreen></iframe>
</div>
</body>
</html>
