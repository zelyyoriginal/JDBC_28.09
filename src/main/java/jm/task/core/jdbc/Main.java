package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService serviceUsers = new UserServiceImpl();
        List<User> users = List.of(
                new User("Саша", "Белый", (byte) 25),
                new User("Лорд", "Волдеморд", (byte) 90),
                new User("Наруто", "Узумаки", (byte) 17),
                new User("Виктор", "Цой", (byte) 99)
        );

        serviceUsers.createUsersTable();
        addUsers(users, serviceUsers);
        System.out.println(serviceUsers.getAllUsers());
        serviceUsers.cleanUsersTable();
        serviceUsers.dropUsersTable();

    }

    static void addUsers(List<User> users, UserService serviceUsers) {
        for (User user : users) {
            serviceUsers.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем — %s добавлен в базу данных\n", user.getName());
        }

    }
}
