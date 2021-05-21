package com.SQLClassies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    private static final String URL = "jdbc:h2:mem:hospital;INIT=RUNSCRIPT FROM 'classpath:init.sql'";
    private static final String USERNAME = "commandoby";
    private static final String USERPASSWORD = "";

    public static Connection getConnection () {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return connection;
    }
}
