package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    private Connection connection;
    private static MyConnection instance;

    private MyConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }

        return instance;
    }
}
