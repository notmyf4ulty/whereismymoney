package com.catnbear.model;

import java.sql.*;

public class DatabaseConnector {

    private static DatabaseConnector instance = null;
    private Connection connection;
    private String databaseName;
    private String tableName;

    private DatabaseConnector() {
        connection = establishConnection();
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    private Connection establishConnection() {

        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(
                            "jdbc:mysql://localhost:3306?zeroDateTimeBehavior=convertToNull",
                            "tutorial",
                            "password");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection == null) {
            System.out.println("Failed to make connection!");
        }

        return connection;
    }

    public ResultSet executeReadQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public int executeWriteQuery(String query) throws  SQLException {
        int rowsAffected = -1;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return rowsAffected;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
