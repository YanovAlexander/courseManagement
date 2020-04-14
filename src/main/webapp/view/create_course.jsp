<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Course</title>
    <style>
        <%@include file="/view/css/style.css"%>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>

<div id="stylized" class="myform">
    <form id="form" name="form" method="post" action="createCourse">
        <h1>Create project form</h1>

        <label>Course title
            <span class="small">Course title</span>
        </label>
        <input type="text" name="title" id="title" />

        <label>Course Status
            <span class="small">Select status</span>
        </label>
        <select name="course_status" id="course_status">
            <c:forEach items="${courseStatuses}" var="courseStatus">
                <option>${courseStatus}</option>
            </c:forEach>
        </select>

        <button type="submit" class="button">Create</button>
        <div class="spacer"></div>

        <c:if test="${not empty errors}">
            <c:forEach items="${errors}" var="error">
                <p style="color: red">${error.field} ${error.error}</p>
            </c:forEach>
        </c:if>
    </form>
</div>

</body>
</html>
