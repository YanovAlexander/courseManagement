<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Details</title>
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
            <th>Title</th>
            <th>Course Status</th>
            <th>Students</th>
            <th>Homeworks</th>
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
                <ul>
                    <c:choose>
                        <c:when test="${not empty course.users}">
                            <c:forEach items="${course.users}" var="user">
                                <li><a href="${pageContext.request.contextPath}/user/get?id=${user.id}" class="button"
                                       role="button"
                                       tabindex="0">${user.firstName} ${user.lastName}</a><br>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No students yet</p>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </td>
            <td>
                <ul>
                    <c:choose>
                        <c:when test="${not empty course.homework}">
                            <c:forEach items="${course.homework}" var="homework">
                                <li><a href="${pageContext.request.contextPath}/homework/get?id=${homework.id}"
                                       class="button"
                                       role="button"
                                       tabindex="0">${homework.title}</a>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No homework's yet</p>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div align="center">
    <a href="${pageContext.request.contextPath}/homework/upload?course_id=${course.id}"
       class="button"
       role="button"
       tabindex="0">Add homework</a>
</div>
</body>
</html>
