<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>Course Managemenr</title>
    <style><%@include file="/view/css/style.css"%></style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<h2>Welcome <security:authentication property="principal.username"/>
    <form method="post" action="/logout">
        <button type="submit">Logout</button>
    </form>
</h2>
</body>
</html>