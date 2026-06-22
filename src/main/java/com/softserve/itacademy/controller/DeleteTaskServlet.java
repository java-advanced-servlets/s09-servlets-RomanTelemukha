package com.softserve.itacademy.controller;

import com.softserve.itacademy.repository.TaskRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


// Specifies the URL path for this servlet
@WebServlet("/delete-task")
public class DeleteTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idParam = request.getParameter("id");
        var requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                boolean deleted = taskRepository.delete(id);
                if(!deleted){
                    request.setAttribute("error", "Task with this index doesn`t exist!!!");
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    requestDispatcher.forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Wrong parameter format!!!");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                requestDispatcher.forward(request, response);
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/tasks-list");
    }
}