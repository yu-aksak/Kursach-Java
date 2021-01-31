package Server.Database;

import Server.Classes.PublishingHouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PublishingHouseDB extends DataTable {
    public PublishingHouseDB(Statement statement, Database database) {
        super(statement, database);
    }

    public String AddPublH(String title, String city, String adress, String phone, String e_mail, String fax, String website){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM publishinghouse WHERE Title = '" + title + "' AND City = '" + city + "';");
            if (resultSet.next())
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("INSERT INTO publishinghouse (Title, City, Adress, Phone, e_mail, fax, website) VALUES ('" + title + "', '" + city + "', '" + adress + "', '" + phone + "', '" + e_mail + "', '" + fax + "', '" + website + "');");
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail";
        }
        return "success";
    }

    public ArrayList<PublishingHouse> SelectPublH(){
        ArrayList<PublishingHouse> publishingHouses = new ArrayList<>();
        String sql = "Select * FROM publishinghouse;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Title = resultSet.getString(2);
                String City = resultSet.getString(3);
                String Adress = resultSet.getString(4);
                String Phone = resultSet.getString(5);
                String e_mail = resultSet.getString(6);
                String Fax = resultSet.getString(7);
                String website = resultSet.getString(8);
                publishingHouses.add(new PublishingHouse(id, Title, City, Adress, Phone, e_mail, Fax, website));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return publishingHouses;
    }
    public String DeletePublH(int id){
        try {
            statement.executeUpdate("DELETE FROM publishinghouse WHERE ID = '" + id + "';");
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
