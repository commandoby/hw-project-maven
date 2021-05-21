package com.SQLClassies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDClass {
    private static final String GET_ID = "SELECT id FROM medicalWorkers ORDER BY id";
    private static final String GET_ALL = "SELECT * FROM medicalWorkers";
    private static final String GET_WORKER = "SELECT * FROM medicalWorkers WHERE name = ? AND surname = ?";
    private static final String INSERT_WORKER = "INSERT INTO medicalWorkers(id, name, surname, position, department, years) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_POSITION = "UPDATE medicalWorkers SET position = ? WHERE id = ?";
    private static final String UPDATE_DEPARTMENT = "UPDATE medicalWorkers SET department = ? WHERE id = ?";
    private static final String UPDATE_YEARS = "UPDATE medicalWorkers SET years = ? WHERE id = ?";
    private static final String DELETE_WORKER = "DELETE FROM medicalWorkers WHERE id = ?";

    private static Connection connection = Utils.getConnection();

    public static List<Worker> getAllWorkers(int type) {
        String typeSort = GET_ALL;
        switch (type) {
            case 1 -> typeSort += " ORDER BY id";
            case 2 -> typeSort += " ORDER BY name";
            case 3 -> typeSort += " ORDER BY surname";
            case 4 -> typeSort += " ORDER BY position";
            case 5 -> typeSort += " ORDER BY department";
            case 6 -> typeSort += " ORDER BY years";
        }
        List<Worker> workers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(typeSort)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                workers.add(new Worker(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return workers;
    }

    public static List<Worker> getWorker(String name, String surname) {
        List<Worker> workers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_WORKER)) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                workers.add(new Worker(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return workers;
    }

    public static void insertWorker(String name, String surname, String position, String department, int years) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_WORKER);
             PreparedStatement psID = connection.prepareStatement(GET_ID)) {
            //поиск первого свободного id
            ResultSet setID = psID.executeQuery();
            int newID = 1;
            while (setID.next()) {
                if (setID.getInt("id") == newID) {
                    newID++;
                } else {
                    break;
                }
            }

            ps.setInt(1, newID);
            ps.setString(2, name);
            ps.setString(3, surname);
            ps.setString(4, position);
            ps.setString(5, department);
            ps.setInt(6, years);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateWorkerPosition(int id, String newPosition) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_POSITION)) {
            ps.setString(1, newPosition);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateWorkerDepartment(int id, String newDepartment) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DEPARTMENT)) {
            ps.setString(1, newDepartment);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateWorkerYears(int id, int newYears) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_YEARS)) {
            ps.setInt(1, newYears);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteWorker(int id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_WORKER)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
