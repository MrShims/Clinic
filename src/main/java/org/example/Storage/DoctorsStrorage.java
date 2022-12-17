package org.example.Storage;

import org.example.DAO.PostgreSQL;
import org.example.Person.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DoctorsStrorage {
    private static List<Doctor> doctors =new ArrayList<>();
    public  List<Doctor> getDoctors() {
        return Collections.unmodifiableList(doctors);
    }
    private static final DoctorsStrorage doctorsStrorage=new DoctorsStrorage();

    static {

        try {
            Statement statement= PostgreSQL.getConnection().createStatement();

            String SQL="Select * from doctor";

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next())
            {
                int id_doctor = resultSet.getInt("id_doctor");
                String fio_doctor = resultSet.getString("fio_doctor");
                String telephone = resultSet.getString("telephone");
                String specialization = resultSet.getString("specialization");

                Doctor doctor=new Doctor(id_doctor,fio_doctor,telephone,specialization);
                doctors.add(doctor);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private DoctorsStrorage()
    {

    }
    public static DoctorsStrorage getInstance()
    {
        return doctorsStrorage;

    }


    public static List<String> spezialization()
    {

        Set<String> stringSet=new HashSet<>();

        doctors.forEach(e->stringSet.add(e.getSpezialization()));


        return new ArrayList<>(stringSet);













    }










}
