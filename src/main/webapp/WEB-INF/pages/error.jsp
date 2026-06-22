<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
    <%@include file="header.jsp"%>

    <div class="error-container">
        <h2 class="text-danger">Error</h2>
        <div class="alert alert-danger">
            <%= request.getAttribute("message") != null ? request.getAttribute("message") : "An unexpected error occurred." %>
        </div>
        <div style="margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/home" class="btn">Go to Home</a>
            <a href="${pageContext.request.contextPath}/tasks-list" class="btn">View All Tasks</a>
        </div>
    </div>
</body>
</html>
