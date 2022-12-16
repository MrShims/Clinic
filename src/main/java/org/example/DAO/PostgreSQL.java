package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL {


    private static final String URL="jdbc:postgresql://localhost:5432/Clinic";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="1234";

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }





    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }







}
