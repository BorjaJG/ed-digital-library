package com.iesam.digitalibrary.user.presentation;


import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;


import java.util.Random;
import java.util.Scanner;

public class UserPresentation {
    private static Scanner scanner = new Scanner(System.in);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXY0123456789";
    private static final Random random = new Random();

    public static void main(String[] args) {
        showMenu();
        scanner.close();
    }

    public static void showMenu() {
        while (true) {

            menuConsola();

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    //modifyUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    searchUser();
                    break;
                case 5:
                    //listAllUsers();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    public static void addUser() {
        User user = readUserDetails();
        if (user != null) {
            saveUser(user);
        }
    }

    public static User readUserDetails() {
        System.out.println("Enter User Information:");
        //String userId = generateUniqueID(8);
        String userId;
        do {
            userId = generateUniqueID(8);
        } while (isUserIdTaken(userId));
        System.out.println("Generated User ID: " + userId);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Role ID: ");
        String roleId = scanner.nextLine();

        String email = generarCorreoElectronico(name, userId);
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

        return new User(userId, name, email, phoneNumber, address, registrationDate, userType, status,
                history, fines, transactions, notificationPreference, roleId, additionalData);
    }


    public static void saveUser(User user) {
        UserDataRepository userRepository = new UserDataRepository(new UserFileLocalDataSource());
        userRepository.save(user);
        System.out.println("User saved successfully.");
    }


    public static User searchUser() {
        System.out.print("Enter User ID to search: ");
        String userId = scanner.nextLine();
        User user = getUserById(userId);
        if (user != null) {
            System.out.println("User found:");
            System.out.println(user.toStringCarnet());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
        return user;
    }

    public static User getUserById(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserFileLocalDataSource());
        return userRepository.obtain(userId);
    }


    public static boolean isUserIdTaken(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserFileLocalDataSource());
        User existingUser = userRepository.obtain(userId);
        return existingUser != null;
    }


    public static void menuConsola() {
        System.out.println("\nBienvenido al sistema de la biblioteca");
        System.out.println("----------------------------------");
        System.out.println("|     User Management System     |");
        System.out.println("----------------------------------");
        System.out.println("|  Options:                      |");
        System.out.println("|  1. Add User                   |");
        System.out.println("|  2. Modify User                |");
        System.out.println("|  3. Delete User                |");
        System.out.println("|  4. Search User                |");
        System.out.println("|  5. List All Users             |");
        System.out.println("|  6. Exit                       |");
        System.out.println("----------------------------------");
        System.out.print("Select an option: ");
    }


    public static String generateUniqueID(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String generarCorreoElectronico(String nombre, String id) {
        // Format the email address: nombre + id + "@biblio.com"
        String correo = nombre + id + "@biblio.com";
        return correo;
    }
    public static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        String userId = scanner.nextLine();
        if (!userId.isEmpty()) {
            deleteUserById(userId);
        } else {
            System.out.println("Invalid user ID.");
        }
    }
    public static void deleteUserById(String userId) {
        UserDataRepository userRepository = new UserDataRepository(new UserFileLocalDataSource());
        userRepository.delete(userId);
        System.out.println("User deleted successfully.");
    }


}

