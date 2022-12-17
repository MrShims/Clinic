package org.example;

import org.example.DAO.PostgreSQL;
import org.example.Person.Patient;
import org.example.advertisement.AdvertisementStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsoleHelper {


    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static void Start() {

        String input = null;

        AdvertisementStorage.getInstance();

        do {

            Print("Добро пожаловать в клинку ООО Быстрые пальца");
            Print("Выберие за кого зайти ");
            Print("1 - Администратор ");
            Print("2 - Клиент ");
            Print("3 - Врач ");
            Print("4 - Выход ");

            input = ReadString();

            if (!input.equals("4")) {

                Loggin(input);
            }


        } while (!input.equals("4"));


    }


    private static void Loggin(String key) {
        Print("Введите логин");
        String loggin = ReadString();
        Print("Введите пароль");
        String pass = ReadString();
        String SQL = null;

        switch (key) {
            case "1": {
                break;
            }
            case "2": {

                SQL = "SELECT patient_id_log from patientbase where login_pat='" + loggin + "' and password_pat='" + pass + "'";
                break;
            }
            case "3": {
                SQL = "SELECT patient_id_log from doctorbase where login_pat='" + loggin + "' and password_pat='" + pass + "'";
                break;
            }
            default: {
                Print("Вы не то выбрали");
                Start();
                break;
            }

        }
        try (Statement statement = PostgreSQL.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL);


            while (resultSet.next()) {
                loadcab(key, resultSet.getInt("patient_id_log"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    private static void loadcab(String key, int value) {

        System.out.println(key + value);
        String SQL = null;

        switch (key) {
            case "1": {
                break;
            }

            case "2": {
                SQL = "SELECT * from patient where id_patient=" + value;
                try {
                    Statement statement = PostgreSQL.getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(SQL);
                    while (resultSet.next()) {

                        int id=resultSet.getInt("id_patient");
                        String fio_patient = resultSet.getString("fio_patient");
                        String telephone = resultSet.getString("telephone");
                        Date date = resultSet.getDate("birthday");
                        String email = resultSet.getString("email");
                        Patient patient = new Patient(id ,fio_patient, telephone, date, email);
                        new PatientAccount(patient).init();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
            case "3": {

            }


        }


    }

    public static void Delime()
    {

        Print("-----------------------------");


    }


    public static void Print(String text) {
        System.out.println(text);
    }

    public static String ReadString() {

        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
