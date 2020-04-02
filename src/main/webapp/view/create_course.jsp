<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create course</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<form method="post" action="createCourse">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Title</p>
            </td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>
                <p>Course status</p>
            </td>
            <td>
                <select name="course_status">
                    <c:forEach items="${courseStatuses}" var="courseStatus">
                        <option>${courseStatus}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        </tbody>
    </table>
    <button type="submit" class="button">Create</button>
</form>
<c:if test="${not empty errors}">
    <c:forEach items="${errors}" var="error">
        <p style="color: red">${error.field} ${error.error}</p><br>
    </c:forEach>
</c:if>
</body>
</html>
