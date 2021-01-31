package Server.Database;

import Server.Classes.Reader;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReaderDB extends DataTable {
    public ReaderDB(Statement statement, Database database) {
        super(statement, database);
    }

    public String AddReader(String surname, String name, String midname, String birthdate, String adress, String phone){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reader WHERE Surname = '" + surname + "' AND Name_ = '" + name + "' AND Midname = '" + midname + "' AND Phone = '" + phone + "';");
            if (resultSet.next())
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("INSERT INTO reader (Surname, Name_, Midname, Birthdate_, Adress, Phone) VALUES ('" + surname + "', '" + name + "', '" + midname + "', '" + birthdate + "', '" + adress + "', '" + phone + "');");
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail";
        }
        return "success";
    }

    public ArrayList<Reader> SelectReaders(){
        ArrayList<Reader> readers = new ArrayList<>();
        String sql = "SELECT * FROM reader";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Midname = resultSet.getString(4);
                Date Birthdate = resultSet.getDate(5);
                String Adress = resultSet.getString(6);
                String Phone = resultSet.getString(7);
                readers.add(new Reader(id, Surname, Name_, Midname, Birthdate.toString(), Adress, Phone));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return readers;
    }

    public String DeleteReader(int id){
        try {
            statement.executeUpdate("DELETE FROM reader WHERE ID = '" + id + "';");
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
