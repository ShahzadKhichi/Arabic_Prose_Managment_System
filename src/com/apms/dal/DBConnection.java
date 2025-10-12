package com.apms.dal;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/apms-database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Singleton instance
    private static DBConnection instance;
    private Connection connection;

    // Private constructor to prevent direct instantiation
    private DBConnection() {
        try {
            // Load MySQL JDBC Driver (manual load for older JDKs)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connection established successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    // Public method to get the singleton instance
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    // Public method to get the connection
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Failed to close database connection.");
            e.printStackTrace();
        }
    }
}
