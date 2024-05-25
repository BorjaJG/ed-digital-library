package com.iesam.digitalibrary.user.presentation;

import com.iesam.Main;
import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.domain.*;
import java.util.List;
import java.util.Scanner;
import static com.iesam.digitalibrary.user.domain.NewUserUseCase.generarCorreoElectronicoUser;
import static com.iesam.digitalibrary.user.domain.NewUserUseCase.generateUniqueIdUser;


public class UserPresentation {
    // Static scanner for reading user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu(); // Display the user menu
        scanner.close(); // Close the scanner at the end
    }

    // Method to display the user menu
    public static void showMenu() {
        while (true) { // Infinite loop until the user chooses to exit
            menuConsola();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    modifyUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    searchUser();
                    break;
                case 5:
                    listUser();
                    break;
                case 6:
                    Main.showMainMenu(); // Uncomment if there's a Main class with this method
                    break;
                case 7:
                    printColor("Exiting...", "red"); // Exit the program
                    return;
                default:
                    printColor("Invalid option. Please select a valid option.", "red"); // Handle invalid option
            }
        }
    }

    // Method to add a user
    public static void addUser() {
        User user = readUserDetails(); // Read user details
        if (user != null) {
            saveUser(user); // Save user if details are valid
            printColor("User saved successfully.", "green");
        }
    }

    // Method to save a user
    public static void saveUser(User user) {
        NewUserUseCase newUserUseCase = new NewUserUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        newUserUseCase.execute(user);
        System.out.println("User saved successfully.");
    }

    // Method to delete a user by ID
    public static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        String userId = scanner.nextLine();
        deleteUserById(userId);
        System.out.println("User deleted successfully.");
    }

    // Method to delete a user by ID
    public static void deleteUserById(String userId) {
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(new UserDataRepository(new UserFileLocalDataSource()));
        deleteUserUseCase.execute(userId);
        System.out.println("User deleted successfully.");
    }

    // Method to list all users
    public static void listUser() {
        System.out.println("List of Users:");
        UserDataRepository userRepository = new UserDataRepository(new UserFileLocalDataSource());
        ListUsersUseCase listUsersUseCase = new ListUsersUseCase(userRepository);
        List<User> users = listUsersUseCase.execute();
        for (User user : users) {
            System.out.println(user);
        }

    }

    // Method to search for a user by ID
    public static void searchUser() {
        System.out.print("Enter User ID to search: ");
        String userId = scanner.nextLine();
        User user = getUserById(userId);
        if (user != null) {
            System.out.println("User found:");
            System.out.println(user.toString());
        } else {
            System.out.println("User not found with ID: " + userId);
        }

    }

    // Method to get a user by ID
    public static User getUserById(String userId) {
        UserDataRepository userDataRepository = new UserDataRepository(new UserFileLocalDataSource());
        GetUserUseCase getUserUseCase = new GetUserUseCase(userDataRepository);
        return getUserUseCase.execute(userId);
    }


    // Method to read user details from input
    public static User readUserDetails() {
        System.out.println("Enter User Information:");

        String userId = generateUniqueIdUser(9);
        System.out.println("UserId: " + userId);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Role ID: ");
        String roleId = scanner.nextLine();

        String email = generarCorreoElectronicoUser(userId, name);
        System.out.println("Email: " + email);

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Registration Date: ");
        String registrationDate = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        System.out.print("History: ");
        String history = scanner.nextLine();

        System.out.print("Fines: ");
        String fines = scanner.nextLine();

        System.out.print("Transactions: ");
        String transactions = scanner.nextLine();

        System.out.print("Notification Preference: ");
        String notificationPreference = scanner.nextLine();

        System.out.print("User Type: ");
        String userType = scanner.nextLine();

        System.out.print("Additional Data: ");
        String additionalData = scanner.nextLine();

        return new User(userId, name, email, phoneNumber, address, registrationDate, userType, status, history, fines, transactions, notificationPreference, roleId, additionalData);
    }

    // Method to read modified user details from input
    public static void modifyUser() {
        System.out.println("Enter User Information:");
        String userId;

        System.out.print("Enter ID: ");
        userId = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        String email = generarCorreoElectronicoUser(userId, name);
        System.out.print("Email: ");


        System.out.print("Role ID: ");
        String roleId = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Registration Date: ");
        String registrationDate = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        System.out.print("History: ");
        String history = scanner.nextLine();

        System.out.print("Fines: ");
        String fines = scanner.nextLine();

        System.out.print("Transactions: ");
        String transactions = scanner.nextLine();

        System.out.print("Notification Preference: ");
        String notificationPreference = scanner.nextLine();

        System.out.print("User Type: ");
        String userType = scanner.nextLine();

        System.out.print("Additional Data: ");
        String additionalData = scanner.nextLine();

        UserFactory userFactory = new UserFactory();
        UserDataRepository userDataRepository = new UserDataRepository(new UserFileLocalDataSource());
        ModifyUserUseCase modifyUserUseCase = new ModifyUserUseCase(userDataRepository, userFactory);
        modifyUserUseCase.execute(userId, name, email, phoneNumber, address, registrationDate, userType, status, history, fines, transactions, notificationPreference, roleId, additionalData);
    }


    // Method to display the user menu in the console
    public static void menuConsola() {
        printColor("Welcome to the Library System ", "cyan");
        printColor("USER", "yellow");
        printColor("----------------------------------", "cyan");
        printColor("|     User Management System     |", "cyan");
        printColor("----------------------------------", "cyan");
        printColor("|  Options:                      |", "cyan");
        printColor("|  1. Add User                   |", "blue");
        printColor("|  2. Modify User                |", "blue");
        printColor("|  3. Delete User                |", "blue");
        printColor("|  4. Search User                |", "blue");
        printColor("|  5. List All Users             |", "blue");
        printColor("|  6. Return  Library            |", "blue");
        printColor("|  7. Exit                       |", "blue");
        printColor("----------------------------------", "cyan");
        System.out.print("Select an option: ");
    }


    // Method to print colored text
    public static void printColor(String text, String color) {
        // ANSI code for colors
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        // Selecting color based on input
        String chosenColor = ANSI_RESET; // Default color
        switch (color.toLowerCase()) {
            case "black":
                chosenColor = ANSI_BLACK;
                break;
            case "red":
                chosenColor = ANSI_RED;
                break;
            case "green":
                chosenColor = ANSI_GREEN;
                break;
            case "yellow":
                chosenColor = ANSI_YELLOW;
                break;
            case "blue":
                chosenColor = ANSI_BLUE;
                break;
            case "purple":
                chosenColor = ANSI_PURPLE;
                break;
            case "cyan":
                chosenColor = ANSI_CYAN;
                break;
            case "white":
                chosenColor = ANSI_WHITE;
                break;
        }

        // Print text in chosen color
        System.out.println(chosenColor + text + ANSI_RESET);
    }
}