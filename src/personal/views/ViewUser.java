package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;
        while (true) {
            String command = prompt("Введите команду: ");

            try {
                com = Commands.valueOf(command);
            } catch (IllegalArgumentException e) {
                System.out.println("Not found command");
            }

            if (com == Commands.EXIT) return;

            try {
                switch (com) {
                    case CREATE:
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone));
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");
                        try {
                            User user = userController.readUser(id);
                            System.out.println(user);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case LIST:
                        getListUser();
                        break;
                    case UPDATE:
                        User updateUser = setUser(true);
                        userController.updateUser(updateUser);
                    case DELETE:
                        String idUse = prompt("Идентификатор пользователя: ");
                        User userDelete = userController.readUser(idUse);
                        userController.deleteUser(userDelete);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private void getListUser() {
        List<User> userLists = userController.readUserList();
        for (User e : userLists) {
            System.out.println(e);
            System.out.println();
        }
    }

    private User setUser(boolean forUpdate) {
        String idString = "";
        if (forUpdate) {
            idString = prompt("Enter user ID: ");
        }

        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");

        if (forUpdate) {
            return new User(idString, firstName, lastName, phone);
        }
        return new User(firstName, lastName, phone);
    }
}
