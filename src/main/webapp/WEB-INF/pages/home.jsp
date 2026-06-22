<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
    <title>Home Page</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
    <%@include file="header.jsp"%>

    <div class="welcome-container">
        <h2>Welcome to the To-Do List Application</h2>
        <p>This application helps you manage your tasks efficiently. You can create, view, edit, and delete tasks.</p>

        <div style="margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/create-task" class="btn btn-success">Create New Task</a>
            <a href="${pageContext.request.contextPath}/tasks-list" class="btn">View All Tasks</a>
        </div>
    </div>
</body>
</html>
