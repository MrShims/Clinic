package org.example.Person;

import java.util.Date;

public class Patient {


    private int ID;
    private String FIO;
    private String telephone;
    private Date birthday;
    private String email;

    public int getID() {
        return ID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getTelephone() {
        return telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "FIO='" + FIO + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }

    public Patient(int ID,String FIO, String telephone, Date birthday, String email) {
        this.ID=ID;
        this.FIO = FIO;
        this.telephone = telephone;
        this.birthday = birthday;
        this.email = email;
    }
}
