package com.commandoby.session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/session")
public class SessionSampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Integer count = (Integer) httpSession.getAttribute("count");

        if (!Optional.ofNullable(count).isPresent()) {
            count = 1;
            httpSession.setAttribute("count", count);
        } else {
            httpSession.setAttribute("count", count + 1);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("session/session.jsp");
        requestDispatcher.forward(req, resp);
    }
}
