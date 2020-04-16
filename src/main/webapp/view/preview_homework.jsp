<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <object data="${pageContext.request.contextPath}/homework/get?id=${homeworkId}"
            type="application/pdf" width="1000" height="600"/>
</div>
</body>
</html>
