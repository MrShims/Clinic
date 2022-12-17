package org.example;

import org.example.DAO.PostgreSQL;
import org.example.Person.Patient;
import org.example.Storage.DoctorsStrorage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class PatientAccount {

    private Patient patient;

    public PatientAccount(Patient patient) {
        this.patient = patient;
    }


    private enum action {
        Profile("Профиль"),
        ShowRerod("Показать записи"),
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
        while (!s.equals("6")) {
            s = ConsoleHelper.ReadString();


            switch (s) {
                case "1": {
                    ConsoleHelper.Print(patient.toString());
                    break;
                }
                case "2": {

                    ShowRecord();
                    break;
                }

                case "3": {

                    deleteRecord();
                    break;

                }
                case "4": {
                    newRecord();
                    break;
                }
                case "5": {

                    ShowCard();
                    break;
                }
                case "6": {
                    break;
                }
                default: {
                    continue;

                }


            }
            action.actions();

        }


    }

    public void ShowRecord() {
        try (ResultSet resultSet = PostgreSQL.ShowRecordsPatient(patient.getID());) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_record");
                String fio_doctor = resultSet.getString("fio_doctor");
                String spezialization = resultSet.getString("specialization");
                Date daterep = resultSet.getDate("daterep_record");
                String format = "id записи - %d\nВрач - %s\nCпециальность - %s\nДата записи - %s\n";
                System.out.printf(format, id, fio_doctor, spezialization, daterep);

            }


        } catch (SQLException e) {


        }


    }

    public void newRecord() {


        ConsoleHelper.Print("Выберите специализацию врача");

        List<String> spezialization = DoctorsStrorage.spezialization();


        for (int i = 0; i < spezialization.size(); i++) {

            ConsoleHelper.Print((i + 1) + " - " + spezialization.get(i));

        }

        String s = ConsoleHelper.ReadString();

        try (ResultSet resultSet = PostgreSQL.availableRecord(spezialization.get(Integer.parseInt(s) - 1));) {

            ConsoleHelper.Print("Доступные записи");
            ConsoleHelper.Delime();
            while (resultSet.next()) {

                int id_record = resultSet.getInt("id_record");
                Date daterep_record = resultSet.getDate("daterep_record");
                String fio_doctor = resultSet.getString("fio_doctor");


                ConsoleHelper.Print("Запись - " + String.valueOf(id_record));
                ConsoleHelper.Print("Дата записи - " + daterep_record.toString());
                ConsoleHelper.Print("ФИО врача - " + fio_doctor);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConsoleHelper.Print("Введите id записи, куда хотите записаться\n Введите exit для выхода");
        String s1 = ConsoleHelper.ReadString();

        if (s1.equals("exit"))
        {
            return;
        }
        else
        {

            System.out.println( PostgreSQL.newRecord(patient.getID(), s1));


        }

    }

    public void deleteRecord() {


        ConsoleHelper.Print("Введите id записи, которую хотите отменить");
        ConsoleHelper.Delime();
        ShowRecord();

        String s = ConsoleHelper.ReadString();


        ConsoleHelper.Print(PostgreSQL.CanselRecord(s));


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