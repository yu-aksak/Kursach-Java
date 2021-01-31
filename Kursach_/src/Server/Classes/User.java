package Server.Classes;

public class User {
    private String e_mail;
    private String password;
    private int level;

    public User(String e_mail, String password, int level){
        this.e_mail = e_mail;
        this.password = password;
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public int getLevel() {
        return level;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setLevel(int level) {
            this.level = level;
    }
}
