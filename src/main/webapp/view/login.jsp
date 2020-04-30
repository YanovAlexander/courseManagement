<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <style>
        <%@include file="/view/css/common.css" %>
        <%@include file="/view/css/bootstrap.min.css"%>
    </style>
</head>

<body>
<div class="container">
    <form method="POST" class="form-signin" action="login">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${pageContext.request.contextPath}/user/registration">Create an account</a></h4>
        </div>
    </form>
</div>
</body>
</html>