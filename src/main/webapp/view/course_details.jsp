<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Details</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<table class="zui-table">
    <thead>
    <tr>
        <th>Title</th>
        <th>Course Status</th>
        <th>Students</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <c:out value="${course.title}"/>
        </td>
        <td>
            <c:out value="${course.courseStatus}"/>
        </td>
        <td>
            <c:choose>
                <c:when test="${not empty course.users}">
                    <c:forEach items="${course.users}" var="user">
                        <a href="${pageContext.request.contextPath}/user/get?id=${user.id}" class="button" role="button"
                           tabindex="0">${user.firstName} ${user.lastName}</a><br>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No students yet</p>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
