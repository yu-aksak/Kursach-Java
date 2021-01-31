package Server.Database;

import Server.Server;

import java.sql.*;

public class Database {
    private Connection connection;
    private String dbUser = "root";
    private String dbPass = "rd567567tf678yg8";
    private String URL="jdbc:mysql://localhost/library?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    public Connection getMyConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL,
                dbUser, dbPass);
        return connection;
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try
            {
                rs.close();
            }
            catch (Exception e3)
            {
                e3.printStackTrace();
            }
        }
    }

}
