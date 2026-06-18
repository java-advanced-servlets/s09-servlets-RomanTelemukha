package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// URL path for accessing this servlet
@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("id");

        if(requestId != null && !requestId.isEmpty()) {
            int id = Integer.parseInt(requestId);
            Task task = taskRepository.read(id);
            if(task != null){
                request.setAttribute("task", task);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp");
                requestDispatcher.forward(request, response);
                return;
            }
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String requestPriority = request.getParameter("priority");
        String requestId = request.getParameter("id");

        Task newTask = null;

        if(title != null && !title.isEmpty() && requestPriority != null && !requestPriority.isEmpty()
            && requestId != null && !requestId.isEmpty()) {

            try{
                int id = Integer.parseInt(requestId);
                Priority priority = Priority.valueOf(requestPriority);
                newTask = new Task(title, priority, id);

                boolean updated = taskRepository.update(newTask);
                if(updated) {
                    response.sendRedirect(request.getContextPath() + "/tasks-list");
                    return;
                }else{
                    request.setAttribute("error", "Task with this index doesn`t exist!!!");
                }
            }catch (IllegalArgumentException e) {
                request.setAttribute("error", "Illegal parameters!!!");
            }
        }else {
            request.setAttribute("error", "All parameters must be present!!!");
        }

        try {
            int id = Integer.parseInt(requestId);
            request.setAttribute("task", taskRepository.read(id));
        } catch (NumberFormatException e) {
            request.setAttribute("task", new Task());
        }

        request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
    }
}