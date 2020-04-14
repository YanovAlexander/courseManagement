<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Homework</title>
</head>
<body>
File: <br />
<form action="homework/upload" method="post"
      enctype="multipart/form-data">
    <label>Homework title
        <span class="small">Homework title</span>
    </label>
    <input name="title" id="title">
    <input type="file" name="file" size="50" />
    <br />
    <input type="submit" value="Upload" />
</form>
</body>
</html>
