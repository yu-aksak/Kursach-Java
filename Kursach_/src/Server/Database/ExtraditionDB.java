package Server.Database;

import Server.Classes.Extradition;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExtraditionDB extends DataTable {
    public ExtraditionDB(Statement statement, Database database) {
        super(statement, database);
    }

    private String getCurrData(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public String Accept(int idReader, int idBook){
        int ID = 0;
        String date = getCurrData();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM extradition WHERE ID_reader = '" + idReader + "' AND ID_book = '" + idBook + "';");
            if (resultSet.next())
                ID = resultSet.getInt(1);
            else
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("UPDATE extradition SET return_date = '" + date + "' WHERE ID = '" + ID + "';");
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail";
        }
        return "success";
    }

    public String Extradite(int idReader, int idBook, int idEmployee){
        String date = getCurrData();
        try {
            statement.executeUpdate("INSERT INTO extradition (Date_, ID_employee, ID_reader, ID_book) VALUES ('" + date + "', " + idEmployee + ", " + idReader + ", " + idBook + ");");
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail";
        }
        return "success";
    }

    public ArrayList<Extradition> SelectActualExtraditions(){
        ArrayList<Extradition> extraditions = new ArrayList<>();
        String sql = "Select extradition.ID, reader.Surname, reader.Name_, author.Surname, " +
                "author.Name_, publishbook.Title, extradition.Date_ " +
                "from extradition " +
                "join reader on reader.ID = extradition.ID_reader " +
                "join book on book.ID = extradition.ID_book " +
                "join author on author.ID = book.AuthorID " +
                "join publishbook on publishbook.ISBN = book.PublishbookID " +
                "where extradition.return_date is null;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String SurnameReader = resultSet.getString(2);
                String NameReader = resultSet.getString(3);
                String SurnameAuthor = resultSet.getString(4);
                String NameAuthor = resultSet.getString(5);
                String Title = resultSet.getString(6);
                String Date = resultSet.getString(7);
                extraditions.add(new Extradition(id, SurnameReader, NameReader, SurnameAuthor, NameAuthor, Title, Date));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return extraditions;
    }

    public ArrayList<Extradition> SelectActualReaderExtraditions(int idReader){
        ArrayList<Extradition> extraditions = new ArrayList<>();
        String sql = "Select extradition.ID, reader.Surname, reader.Name_, author.Surname, " +
                "author.Name_, publishbook.Title, extradition.Date_ " +
                "from extradition " +
                "join reader on reader.ID = extradition.ID_reader " +
                "join book on book.ID = extradition.ID_book " +
                "join author on author.ID = book.AuthorID " +
                "join publishbook on publishbook.ISBN = book.PublishbookID " +
                "where extradition.return_date is null AND extradition.ID_reader = " + idReader + ";";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String SurnameReader = resultSet.getString(2);
                String NameReader = resultSet.getString(3);
                String SurnameAuthor = resultSet.getString(4);
                String NameAuthor = resultSet.getString(5);
                String Title = resultSet.getString(6);
                String Date = resultSet.getString(7);
                extraditions.add(new Extradition(id, SurnameReader, NameReader, SurnameAuthor, NameAuthor, Title, Date));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return extraditions;
    }

    public ArrayList<Extradition> SelectExtraditions(){
        ArrayList<Extradition> extraditions = new ArrayList<>();
        String sql = "Select extradition.ID, reader.Surname, reader.Name_, author.Surname, " +
                "author.Name_, publishbook.Title, extradition.Date_, extradition.return_date " +
                "from extradition " +
                "join reader on reader.ID = extradition.ID_reader " +
                "join book on book.ID = extradition.ID_book " +
                "join author on author.ID = book.AuthorID " +
                "join publishbook on publishbook.ISBN = book.PublishbookID;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String SurnameReader = resultSet.getString(2);
                String NameReader = resultSet.getString(3);
                String SurnameAuthor = resultSet.getString(4);
                String NameAuthor = resultSet.getString(5);
                String Title = resultSet.getString(6);
                String Date = resultSet.getString(7);
                String ReturnDate = resultSet.getString(8);
                extraditions.add(new Extradition(id, SurnameReader, NameReader, SurnameAuthor, NameAuthor, Title, Date, ReturnDate));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return extraditions;
    }

    public ArrayList<Extradition> SelectCountExtraditions(){
        ArrayList<Extradition> extraditions = new ArrayList<>();
        String sql = "select reader.ID, reader.Surname, reader.Name_, count(extradition.ID) from extradition " +
                "join reader on reader.ID = extradition.ID_reader group by reader.ID;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String SurnameReader = resultSet.getString(2);
                String NameReader = resultSet.getString(3);
                int Count = resultSet.getInt(4);
                extraditions.add(new Extradition(id, SurnameReader, NameReader, Count));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return extraditions;
    }

    public ArrayList<Extradition> SelectCountBooks(){
        ArrayList<Extradition> extraditions = new ArrayList<>();
        String sql = "Select author.Surname, author.Name_, publishbook.Title, count(*) ID_book from extradition " +
                "join book on book.ID = extradition.ID_book " +
                "join publishbook on publishbook.ISBN = book.PublishBookID " +
                "join author on author.ID = book.AuthorID " +
                "group by ID_book;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String SurnameAuthor = resultSet.getString(1);
                String NameAuthor = resultSet.getString(2);
                String Title = resultSet.getString(3);
                int Count = resultSet.getInt(4);
                extraditions.add(new Extradition(SurnameAuthor, NameAuthor, Title, Count));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return extraditions;
    }
}
