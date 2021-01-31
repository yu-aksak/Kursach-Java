package Server.Database;

import Server.Classes.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDB extends DataTable {
    public BookDB(Statement statement, Database database) {
        super(statement, database);
    }


    public ArrayList<Book> SelectBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT book.ID, author.Surname, author.Name_, publishbook.Title, publishbook.Genre from author JOIN book ON book.AuthorID = author.ID JOIN publishbook ON book.PublishBookID = publishbook.ISBN;";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Title = resultSet.getString(4);
                String Genre = resultSet.getString(5);
                books.add(new Book(id, Surname, Name_, Title, Genre));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return books;
    }

    public String AddBook(String title, String genre, String series, int year, int pages,
                          String binding, int idPublH, int idAuthor) {
        int ID = 0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM publishbook WHERE Title = '" + title + "' AND Genre = '" + genre + "' AND  PublishID = '" + idPublH + "';");
            if (resultSet.next())
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("INSERT INTO publishbook (Title, Genre, Series, Year_, Pages, Binding, PublishID) VALUES ('"
                    + title + "', '" + genre + "', '" + series + "', '" + year + "', " + pages + ", '" + binding + "', " + idPublH + ");");
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail1";
        }
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM publishbook WHERE Title = '" + title + "' AND Genre = '" + genre + "' AND  PublishID = '" + idPublH + "';");
            if (resultSet.next())
                ID = resultSet.getInt(1);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("INSERT INTO book (PublishBookID, AuthorID) VALUES ('" + ID + "', '" + idAuthor + "');");
            return "success";
        } catch (Exception ex) {
            System.err.println(ex);
            return "fail2";
        }
    }

    public ArrayList<Book> SearchAuthor(String author) {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT book.ID, author.Surname, author.Name_, publishbook.Title, publishbook.Genre from author JOIN book ON book.AuthorID = author.ID JOIN publishbook ON book.PublishBookID = publishbook.ISBN WHERE author.Surname = '" + author + "';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Title = resultSet.getString(4);
                String Genre = resultSet.getString(5);
                books.add(new Book(id, Surname, Name_, Title, Genre));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return books;
    }

    public ArrayList<Book> SearchGenre(String genre) {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT book.ID, author.Surname, author.Name_, publishbook.Title, publishbook.Genre from author JOIN book ON book.AuthorID = author.ID JOIN publishbook ON book.PublishBookID = publishbook.ISBN WHERE publishbook.Genre = '" + genre + "';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Title = resultSet.getString(4);
                String Genre = resultSet.getString(5);
                books.add(new Book(id, Surname, Name_, Title, Genre));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return books;
    }

    public ArrayList<Book> SearchTitle(String title) {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT book.ID, author.Surname, author.Name_, publishbook.Title, publishbook.Genre from author JOIN book ON book.AuthorID = author.ID JOIN publishbook ON book.PublishBookID = publishbook.ISBN WHERE publishbook.Title = '" + title + "';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Title = resultSet.getString(4);
                String Genre = resultSet.getString(5);
                books.add(new Book(id, Surname, Name_, Title, Genre));
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return books;
    }

    public String DeleteBook(int id){
        try {
            statement.executeUpdate("DELETE FROM book WHERE ID = '" + id + "';");
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }

}