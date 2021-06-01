package com.commandoby.servlet;

import com.commandoby.calculator.Calculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {

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
        int sign = Calculator.signReader(text);
        if (sign != 0) {
            List<Integer> nums = Calculator.reader(text);
            int result = Calculator.result(sign, nums.get(0), nums.get(1));
            req.setAttribute("text", result);
            req.setAttribute("signText", Calculator.SIGNNAME[sign] + " ");
            dispatcher = req.getRequestDispatcher("Result.jsp");
        } else {
            req.setAttribute("text", text);
            dispatcher = req.getRequestDispatcher("ExceptionPage.jsp");
        }
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
