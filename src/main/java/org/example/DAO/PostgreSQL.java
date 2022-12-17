package org.example.DAO;

import java.sql.*;

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


    public static ResultSet ShowCardPatient(int id)

    {

        String SQL= "SELECT fio_doctor,specialization,daterep,textrep from note,doctor where id_doctor=note.id_doctor_note and id_cart_note="+id;

        try {
            Statement statement= connection.createStatement();

            return statement.executeQuery(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


















}
