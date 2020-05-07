<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Users</title>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet"/>
</head>
<body>
<c:import url="${contextPath}/WEB-INF/view/navibar.jsp"/>
<c:if test="${not empty users}">
    <table class="zui-table myform">
        <thead>
        <tr>
            <th>Full name</th>
            <th>Email</th>
            <th>Course</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                        ${user.firstName} ${user.lastName}
                </td>
                <td>
                        ${user.email}
                </td>
                <td>
                    <c:if test="${not empty user.course}">
                        <a href="${pageContext.request.contextPath}/course/get?id=${user.course.id}" class="button"
                           role="button" tabindex="0">${user.course.title}</a>
                    </c:if>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/get?id=${user.id}" class="button" role="button"
                       tabindex="0">Detailed</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
