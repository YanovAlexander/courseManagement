<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
    <style>
        <%@include file="/view/css/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<div align="center">
    <table class="zui-table">
        <thead>
        <tr>
            <th>Full name</th>
            <th>Email</th>
            <th>User role</th>
            <th>User status</th>
            <th>Course</th>
            <th>Solutions</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                ${user.firstName} ${user.lastName}
            </td>
            <td>
                ${user.email}
            </td>
            <td>
                ${user.userRole}
            </td>
            <td>
                ${user.status}
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/course/get?id=${user.course.id}" class="button"
                   role="button" tabindex="0">${user.course.title}</a>
            </td>
            <td>
                <ul>
                    <c:choose>
                        <c:when test="${not empty user.solutions}">
                            <c:forEach items="${user.solutions}" var="solution">
                                <li><a href="${pageContext.request.contextPath}/solution/get?id=${solution.id}" class="button"
                                       role="button"
                                       tabindex="0">${solution.homework.title}</a><br>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No solutions yet</p>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
