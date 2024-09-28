package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String userName = "1";
    private static final String password = "1";
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static SessionFactory sessionFactory;
    private static Connection connection;


    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration();
                cfg.setProperty("hibernate.connection.url", url);
                cfg.setProperty("hibernate.connection.username", userName);
                cfg.setProperty("hibernate.connection.password", password);
                cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                cfg.setProperty("hibernate.hbm2ddl.auto", "update");
                cfg.setProperty("hibernate.show_sql", "true");
                cfg.addAnnotatedClass(User.class);
                sessionFactory = cfg.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
