package com.commandoby.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("calculator.html");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("command");
        resp.setContentType("text/html");
        RequestDispatcher dispatcher;
        if (text == "" || text.isEmpty()) {
            req.setAttribute("text", "empty");
            dispatcher = req.getRequestDispatcher("ExceptionPage.jsp");
        } else {
            req.setAttribute("text", text);
            dispatcher = req.getRequestDispatcher("Result.jsp");
        }
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
