package com.hotelreservation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final String URL = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.ufbbhacksryhxbiouwsu";
    private static final String PASSWORD = "[Comma_123---]";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            logger.info("Connecting to database...");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.error("PostgreSQL JDBC Driver not found", e);
            throw new SQLException("PostgreSQL JDBC Driver not found", e);
        }
    }
}
