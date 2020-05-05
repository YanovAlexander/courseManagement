<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Course
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/course/showCourses">Show Courses</a>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${pageContext.request.contextPath}/course/createCourse">Create Course</a>
            </security:authorize>
            <a href="${pageContext.request.contextPath}/course/findCourse">Find Course</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">User
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/user/showUsers">Show Users</a>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="#">Create User</a>
            </security:authorize>
            <a href="${pageContext.request.contextPath}/user/findPage">Find User</a>
        </div>
    </div>
    <div>
        <a style="float: right" href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
    <div>
        <p><security:authentication property="principal.username"/></p>
    </div>
</div>