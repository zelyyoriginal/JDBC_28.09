package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {


        try (Connection connection = new Util().getConection()) {
            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "    id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "    name VARCHAR(255) NOT NULL," +
                    "    lastName VARCHAR(255) NOT NULL," +
                    "    age TINYINT NOT NULL)";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        try (Connection connection = new Util().getConection()) {
            String sql = "DROP TABLE IF EXISTS users";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = new Util().getConection()) {
            String sql = "INSERT INTO users(name, lastName, age) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try(Connection connection = new Util().getConection()){

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = new Util().getConection()){
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               User user = new User(
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getByte(4)
               );
               user.setId(resultSet.getLong(1));
               users.add(user);
           }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connection = new Util().getConection()) {
            String sql = "TRUNCATE users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
