package Server.Classes;

import java.io.Serializable;

public class PublishingHouse implements Serializable {
    private int id;
    private String title;
    private String city;
    private String adress;
    private String phone;
    private String e_mail;
    private String fax;
    private String website;

    public PublishingHouse(int id, String title, String city, String adress, String phone, String e_mail, String fax, String website){
        this.id = id;
        this.title = title;
        this.city = city;
        this.adress = adress;
        this.phone = phone;
        this.e_mail = e_mail;
        this.fax = fax;
        this.website = website;
    }

    PublishingHouse(String title, String city, String adress, String phone, String e_mail, String fax, String website){
        this.title = title;
        this.city = city;
        this.adress = adress;
        this.phone = phone;
        this.e_mail = e_mail;
        this.fax = fax;
        this.website = website;
    }

    PublishingHouse(String title, String city){
        this.title = title;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getCity() {
        return city;
    }

    public String getFax() {
        return fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
