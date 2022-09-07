package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Anton", "Logvinov", (byte) 38);
        userService.saveUser("Sergey", "Starykh", (byte) 28);
        userService.saveUser("Lionel", "Messi",(byte) 35);
        userService.saveUser("Aleksandr", "Paradeev", (byte) 23);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
