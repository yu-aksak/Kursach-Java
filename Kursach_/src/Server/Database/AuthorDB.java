package Server.Database;

import Server.Classes.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorDB extends DataTable {
    public AuthorDB(Statement statement, Database database) {
        super(statement, database);
    }

    public String AddAuthor(String surname, String name, int year){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE Surname = '" + surname + "' AND Name_ = '" + name + "';");
            if (resultSet.next())
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("INSERT INTO author (Surname, Name_, Year_) VALUES ('" + surname + "', '" + name + "', '" + year + "');");
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail";
        }
        return "success";
    }

    public ArrayList<Author> SelectAuthors(){
        ArrayList<Author> authors = new ArrayList<>();
        String sql = "SELECT author.ID, author.Surname, author.Name_, author.Year_ FROM author";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                int year = resultSet.getInt(4);
                authors.add(new Author(id, Surname, Name_, year));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return authors;
    }

    public String DeleteAuthor(int id){
        try {
            statement.executeUpdate("DELETE FROM author WHERE ID = '" + id + "';");
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
