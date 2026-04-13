package com.edutech.progressive.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static final Properties properties = new Properties();

    private static void loadProperties(){
        try {
            InputStream input = DatabaseConnectionManager.class
            .getClassLoader().getResourceAsStream("application.properties");

            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        loadProperties();
        return DriverManager.getConnection(
            properties.getProperty("spring.datasource.url"),
            properties.getProperty("spring.datasource.username"),
            properties.getProperty("spring.datasource.password")
        );
    }
}