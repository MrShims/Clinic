package org.example.Person;

public class Doctor {

    private int id;
    private String fio;
    private String telephone;
    private String spezialization;

    public Doctor(int id, String fio, String telephone, String spezialization) {
        this.id = id;
        this.fio = fio;
        this.telephone = telephone;
        this.spezialization = spezialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", telephone='" + telephone + '\'' +
                ", spezialization='" + spezialization + '\'' +
                '}';
    }
}
