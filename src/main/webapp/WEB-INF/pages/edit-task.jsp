<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
    <title>Edit existing Task</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
    <%@include file="header.jsp" %>
    <form action="${pageContext.request.contextPath}/edit-task" method="post">
        <input type="hidden" id="id" name="id" class="form-control"
               value="${task.id}"
               required>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" class="form-control"
                   value="${task.title}"
                   placeholder="Enter task title" required>
        </div>

        <div class="form-group">
            <label for="priority">Priority:</label>
            <select id="priority" name="priority" class="form-select" required>
                <option value="" disabled <%= request.getAttribute("priority") == null ? "selected" : "" %>>Select priority</option>
                <%
                    Task currentTask = (Task) request.getAttribute("task");
                    Priority selectedPriority = currentTask != null ? currentTask.getPriority() : null;
                    for (Priority priority : Priority.values()) {
                        boolean isSelected = selectedPriority != null && selectedPriority.equals(priority);
                %>
                <option value="<%= priority %>" <%= isSelected ? "selected" : "" %>><%= priority %></option>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Edit Task</button>
        <a href="${pageContext.request.contextPath}/tasks-list" class="btn">Cancel</a>
    </form>
</body>
</html>
