package Server.Classes;

import java.io.Serializable;

public class Reader implements Serializable {
    public int id;
    public String surname;
    private String name;
    private String midname;
    private String birthdate;
    private String adress;
    private String phone;

    public Reader(int id, String surname, String name, String midname, String birthdate, String adress, String phone){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.midname = midname;
        this.birthdate = birthdate;
        this.adress = adress;
        this.phone = phone;
    }

    Reader(String surname, String name, String midname, String birthdate, String adress, String phone){
        this.surname = surname;
        this.name = name;
        this.midname = midname;
        this.birthdate = birthdate;
        this.adress = adress;
        this.phone = phone;
    }

    Reader(String surname, String name, String birthdate){
        this.surname = surname;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public String getBirthdate() {
        return birthdate;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
