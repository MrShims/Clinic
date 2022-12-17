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


    public static ResultSet ShowRecordsPatient(int id)
    {

        String SQL="SELECT id_record,fio_doctor,specialization, daterep_record from record,doctor where id_doctor=record.id_doctor_record and id_patient_record="+id;

        try {
            Statement statement=connection.createStatement();
            return statement.executeQuery(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static String CanselRecord(String id)
    {

        String SQL="UPDATE record set id_patient_record=null, busy=false where id_record="+id;



        try {
            Statement statement=connection.createStatement();


            int i = statement.executeUpdate(SQL);

            return i==1?"Запись успешно удалена":"Ошибка удаления";


        } catch (SQLException e) {

            throw new RuntimeException(e);


        }


    }

    public static ResultSet availableRecord(String s)
    {
        String SQL="SELECT * from record inner join doctor d on d.id_doctor = record.id_doctor_record where id_patient_record is Null and specialization='"+s.trim()+"'";

        try {
            Statement statement=connection.createStatement();

            return  statement.executeQuery(SQL);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static String newRecord(int idPatient,String idRecord)
    {

        String SQL="UPDATE record set busy=true,id_patient_record="+idPatient+" where id_record="+idRecord;

        try {
            Statement statement=connection.createStatement();

            int i = statement.executeUpdate(SQL);

            return i==1?"Запись прошла успешно":"Ошибка записи";


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


















}
