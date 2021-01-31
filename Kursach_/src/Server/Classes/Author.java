package Server.Classes;

import java.io.Serializable;

public class Author implements Serializable {
    private int id;
    private String surname;
    private String name;
    private String midname;
    private int year;

    public Author(int id, String surname, String name, int year){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.year = year;
    }

    public Author(String surname, String name, int year){
        this.surname = surname;
        this.name = name;
        this.midname = midname;
        this.year = year;
    }

    public String getMidname() {
        return midname;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
