<div class="navbar">
    <a href="${pageContext.request.contextPath}/">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Course
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/course/showCourses">Show Courses</a>
            <a href="${pageContext.request.contextPath}/course/createCourse">Create Course</a>
            <a href="${pageContext.request.contextPath}/course/findCourse">Find Course</a>
        </div>
    </div>
    <div class="dropdown">
        <button class="dropbtn">User
            <i></i>
        </button>
        <div class="dropdown-content">
            <a href="${pageContext.request.contextPath}/user/showUsers">Show Users</a>
            <a href="#">Create User</a>
            <a href="${pageContext.request.contextPath}/user/findPage">Find User</a>
        </div>
    </div>
</div>