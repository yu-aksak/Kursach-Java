package Server.Classes;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    public String surname;
    private String name;
    public String title;
    public String genre;

    public Book(int id, String surname, String name, String title, String genre) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.title = title;
        this.genre = genre;
    }

    Book(String surname, String name, String title, String genre) {

        this.surname = surname;
        this.name = name;
        this.title = title;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
