package com.commandoby.sonyShop.servlet;

import com.commandoby.sonyShop.commands.BaseCommand;
import com.commandoby.sonyShop.commands.CommandFactory;
import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sonyshop")
public class MainServlet extends HttpServlet {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseCommand requestCommand = CommandFactory.defineCommand(req);
        try {
            String path = "pages/" + requestCommand.execute(req);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        } catch (CommandException e) {
            log.error(e);
            req.getRequestDispatcher("pages/" + PagesPathEnum.SIGN_IN_PAGE.getPath()).forward(req, resp);
        }
    }
}
