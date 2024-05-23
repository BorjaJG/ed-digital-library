package com.iesam.digitalibrary.user.presentation;


import com.iesam.Main;
import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalibrary.user.domain.NewUserUseCase;
import com.iesam.digitalibrary.user.domain.User;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.iesam.digitalibrary.digitalresources.ebook.presentation.EbookPresentation.generateUniqueID;
import static com.iesam.digitalibrary.user.domain.NewUserUseCase.generarCorreoElectronicoUser;
import static com.iesam.digitalibrary.user.domain.NewUserUseCase.generateUniqueIdUser;

public class UserPresentation {

    // Static scanner for reading user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();// Display the user menu
        scanner.close();// Close the scanner at the end
    }

    // Method to display the user menu
    public static void showMenu() {
        while (true) {// Infinite loop until the user chooses to exit
            menuConsola();// Display the menu in the console

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
                    Main.showMainMenu();
                    break;
                case 7:
                    printColor("Exiting...", "red");// Exit the program
                    return;
                default:
                    printColor("Invalid option. Please select a valid option.", "red"); // Handle invalid option
            }
        }
    }

    // Method to add a user
    public static void addUser() {
        User user = readUserDetails();// Read user details
        if (user != null) {
            saveUser(user);// Save user if details are valid
            printColor("User saved successfully.", "green");
        }
    }

    // Method to read user details from input
    public static User readUserDetails() {
        System.out.println("Enter User Information:");

        String userId = generateUniqueIdUser(9);
        System.out.print("UserId: " + userId);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Role ID: ");
        String roleId = scanner.nextLine();

        String email = generarCorreoElectronicoUser(userId, name);
        System.out.print("email: " + email);

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

        User user = new User(userId, name, email, phoneNumber, address, registrationDate, userType, status,
                history, fines, transactions, notificationPreference, roleId, additionalData);
        return user;
    }

    // Method to save a user
    public static void saveUser(User user) {
        NewUserUseCase newUserUseCase = new NewUserUseCase(new UserDataRepository( new UserFileLocalDataSource()));
        newUserUseCase.execute(user);
        System.out.println("User saved successfully.");
    }

    // Method to search for a user by ID
    public static User searchUser() {
        System.out.print("Enter User ID to search: ");
        String userId = scanner.nextLine();
        User user = getUserById(userId);
        if (user != null) {
            System.out.println("User found:");
            System.out.println(user.toString());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
        return user;
    }

    // Method to get a user by ID
    public static User getUserById(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
        return userRepository.obtain(userId);
    }

    // Method to check if a user ID is already taken
    public static boolean isUserIdTaken(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
        User existingUser = userRepository.obtain(userId);
        return existingUser != null;
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


    // Method to delete a user by ID
    public static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        String userId = scanner.nextLine();
        if (!userId.isEmpty()) {
            deleteUserById(userId);
        } else {
            System.out.println("Invalid user ID.");
        }
    }

    // Method to delete a user by ID
    public static void deleteUserById(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
        userRepository.delete(userId);
        System.out.println("User deleted successfully.");
    }

    // Method to modify a user
    public static void modifyUser() {
        User user = readModyUserDetails();
        if (user != null) {
            modifyUser(user);
            printColor("User modify successfully.", "green");
        }
    }

    // Method to read modified user details from input
    private static User readModyUserDetails() {

        System.out.println("Enter User Information:");
        //String userId = generateUniqueID(8);
        String userId;
        do {
            System.out.println("Enter ID:");
            userId = scanner.nextLine();
            if (doesUserIdTaken(userId)) {
                System.out.println("Identification does not exist. Please enter a different ID.");
            }
        } while (doesUserIdTaken(userId));
        System.out.println("Generated User ID: " + userId);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Role ID: ");
        String roleId = scanner.nextLine();
        String email = null;
        //String email = generarCorreoElectronico(name, userId);
        //System.out.println("Email: " + email);

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

        return new User(userId, name, email, phoneNumber, address, registrationDate, userType, status,
                history, fines, transactions, notificationPreference, roleId, additionalData);

    }

    // Method to check if a user ID exists
    public static boolean doesUserIdTaken(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
        User existingUser = userRepository.obtain(userId);
        return existingUser == null;
    }

    // Method to update a user
    private static void modifyUser(User user) {
        UserDataRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
        userRepository.modify(user);
    }

    // Method to list all users
    private static void listUser() {
        System.out.println("List of Users:");
        UserDataRepository userRepository = new UserDataRepository(new UserMemLocalDataSource(), new UserFileLocalDataSource());
        List<User> users = userRepository.list();
        for (User user : users) {
            System.out.println(user.toString());
        }
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

