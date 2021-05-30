package com.commandoby.categories;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category("Arm", "arm.jpg"));
        categoryList.add(new Category("Cube", "cube.jpg"));
        categoryList.add(new Category("Hole", "hole.jpg"));
        categoryList.add(new Category("Pen", "pen.jpg"));
        categoryList.add(new Category("Hexagon", "hexagon.jpg"));
        categoryList.add(new Category("Spider", "spider.jpg"));

        req.setAttribute("categories", categoryList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("bootstrapPages/category.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
