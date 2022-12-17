package org.example;

import org.example.DAO.PostgreSQL;
import org.example.Person.Patient;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientAccount {

    private Patient patient;

    public PatientAccount(Patient patient) {
        this.patient = patient;
    }


    private enum action {
        Profile("Профиль"),
        deleteRecord("Удалить запись"),
        newRecord("Записаться"),
        ShowCard("Показать карту"),
        Exit("Выход");

        private String value;

        action(String value) {
            this.value = value;
        }

        public static void actions() {
            action[] values = action.values();

            for (int i = 0; i < values.length; i++) {

                System.out.println((i + 1) + " - " + values[i].value);
            }


        }


    }

    public void init() {
        ConsoleHelper.Print("Добро пожаловать в личный кабинет - выберите действие");
        action.actions();
        String s = "10";
        while (!s.equals("5")) {
            s = ConsoleHelper.ReadString();

            switch (s) {
                case "1": {
                    ConsoleHelper.Print(patient.toString());
                    break;
                }
                case "2": {

                    deleteRecord();

                }
                case "3": {
                    newRecord();
                }
                case "4": {

                    ShowCard();
                }
                case "5": {
                    break;
                }
                default: {
                    continue;

                }

            }


        }


    }


    public void newRecord() {


    }

    public void deleteRecord() {


    }

    public void ShowCard() {
        try (ResultSet resultSet = PostgreSQL.ShowCardPatient(patient.getID());) {
            while (resultSet.next()) {
                String fio_doctor = resultSet.getString("fio_doctor");
                String specialization = resultSet.getString("specialization");
                Date daterep = resultSet.getDate("daterep");
                String textrep = resultSet.getString("textrep");


                String template = "Врач - %s \nСпециальность - %sДата приема %s\nОписание: %s";


                System.out.printf((template) + "%n", fio_doctor, specialization, daterep.toString(), textrep);
                System.out.println("---------------------------------------------------------------");


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}