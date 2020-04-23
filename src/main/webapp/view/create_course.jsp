<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <form:form id="form" name="form" method="post" action="createCourse" modelAttribute="course">
        <h1>Create project form</h1>

        <label>Course title
            <span class="small">Course title</span>
        </label>
        <form:input type="text" path="title" id="title" /> <form:errors path="title" cssClass="error"/>

        <label>Course Status
            <span class="small">Select status</span>
        </label>
        <form:select path="courseStatus" items="${courseStatuses}"/>
        <form:errors path="courseStatus" cssClass="error"/>

        <button type="submit" class="button">Create</button>
        <div class="spacer"></div>
    </form:form>
</div>

</body>
</html>
