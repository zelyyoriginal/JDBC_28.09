package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    String username = "1";
    String password = "1";
    String url = "jdbc:mysql://localhost:3306/test";


    public Connection getConection() throws SQLException {

        return DriverManager.getConnection(url, username, password);


    }

    public void close(Connection connection) throws SQLException {
        connection.close();
    }
}
