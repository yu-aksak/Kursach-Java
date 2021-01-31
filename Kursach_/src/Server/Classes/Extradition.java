package Server.Classes;

import java.io.Serializable;

public class Extradition implements Serializable {
    private int id;
    private String surnameReader;
    private String nameReader;
    private String surnameAuthor;
    private String nameAuthor;
    private String title;
    private String date;
    private String returnDate;
    private int count;

    public Extradition(int id, String surnameReader, String nameReader, String surnameAuthor, String nameAuthor, String title, String date){
        this.id = id;
        this.surnameReader = surnameReader;
        this.nameReader = nameReader;
        this.surnameAuthor = surnameAuthor;
        this.nameAuthor = nameAuthor;
        this.title = title;
        this.date = date;
    }

    public Extradition(int id, String surnameReader, String nameReader, int count){
        this.id = id;
        this.surnameReader = surnameReader;
        this.nameReader = nameReader;
        this.count = count;
    }

    public Extradition(String surnameAuthor, String nameAuthor, String title, int count){
        this.surnameAuthor = surnameAuthor;
        this.nameAuthor = nameAuthor;
        this.title = title;
        this.count = count;
    }

    public Extradition(int id, String surnameReader, String nameReader, String surnameAuthor, String nameAuthor, String title, String date, String returnDate){
        this.id = id;
        this.surnameReader = surnameReader;
        this.nameReader = nameReader;
        this.surnameAuthor = surnameAuthor;
        this.nameAuthor = nameAuthor;
        this.title = title;
        this.date = date;
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public String getNameReader() {
        return nameReader;
    }

    public int getCount() {
        return count;
    }

    public String getSurnameAuthor() {
        return surnameAuthor;
    }

    public String getSurnameReader() {
        return surnameReader;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public void setSurnameAuthor(String surnameAuthor) {
        this.surnameAuthor = surnameAuthor;
    }

    public void setSurnameReader(String surnameReader) {
        this.surnameReader = surnameReader;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
