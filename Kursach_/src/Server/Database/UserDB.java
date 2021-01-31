package Server.Database;

import Server.Classes.Employee;
import Server.Classes.Reader;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDB extends DataTable {

    public UserDB(Statement statement, Database database) {
        super(statement, database);
    }

    public String CheckLogin(String login_, String password_, int level_) {
        String message = "";
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_ WHERE login = '" + login_ + "' AND password_ = '" + password_ + "' AND level_ = " + level_ + ";");
            if (resultSet.next())
                message = "success";
            else
                message = "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return message;
    }

    public String CheckUserInDB(int ID, int level) {
        String message = "";
        String table = "reader";
        if (level == 1)
            table = "employee";
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + " WHERE ID = '" + ID + "';");
            if (resultSet.next());
            else
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_ WHERE ID = '" + ID + "';");
            if (resultSet.next())
                return "fail1";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return "success";
    }

    public String AddUser(int ID, String login, String pass, int level) {
        String table = "";
        if (level == 1)
            table = "employee";
        else if (level == 2)
            table = "reader";
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + " WHERE ID = " + ID + ";");
            if (resultSet.next())
                ;
            else
                return "fail";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            statement.executeUpdate("INSERT INTO user_ VALUES ('" + ID + "', '" + login + "', '" + pass + "', '" + level + "');");
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return "success";
    }


    public Reader findReader(String login_, String password_, int level_) {
        Reader reader = null;
        int ID = 0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_ WHERE login = '" + login_ + "' AND password_ = '" + password_ + "' AND level_ = " + level_ + ";");
            if (resultSet.next())
                ID = resultSet.getInt(1);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reader WHERE ID = " + ID + ";");
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Midname = resultSet.getString(4);
                Date Birthdate_ = resultSet.getDate(5);
                String Adress = resultSet.getString(6);
                String Phone = resultSet.getString(7);
                reader = new Reader(id, Surname, Name_, Midname, Birthdate_.toString(), Adress, Phone);
            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return reader;
    }

    public Employee findEmployee(String login_, String password_, int level_) {
        Employee employee = null;
        int ID = 0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_ WHERE login = '" + login_ + "' AND password_ = '" + password_ + "' AND level_ = " + level_ + ";");
            if (resultSet.next())
                ID = resultSet.getInt(1);

        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE ID = " + ID + ";");
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Surname = resultSet.getString(2);
                String Name_ = resultSet.getString(3);
                String Midname = resultSet.getString(4);
                Date Birthdate_ = resultSet.getDate(5);
                String Adress = resultSet.getString(6);
                String Phone = resultSet.getString(7);
                employee = new Employee(id, Surname, Name_, Midname, Birthdate_.toString(), Adress, Phone);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return employee;
    }

    public String UpdateUser(int id, String surname, String name, String midname, String birthdate, String adress, String phone, int level){
        String message = "fail";
        String table = "";
        if (level == 1)
            table = "employee";
        else
            table = "reader";
        try {
            statement.executeUpdate("UPDATE " +  table + " SET Surname = '" + surname +"', Name_ = '" + name +
                    "', Midname = '" + midname + "', Birthdate_ = '" + birthdate + "', adress = '" + adress + "', Phone = '" +
                    phone + "' WHERE ID = '" + id + "';");
            message = "success";
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return message;
    }

}
