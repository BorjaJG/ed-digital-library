package com.iesam.digitalibrary.digitalresources.ebook.presentation;

import com.iesam.digitalibrary.digitalresources.ebook.data.EbookDataRepository;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;


import java.util.Random;
import java.util.Scanner;




public class EbookPresentation {

    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    // Possible characters for generating unique IDs
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXY0123456789";
    // Random number generator
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        // Display the menu at the start of the program
        showMenu();
        // Close the scanner at the end
        scanner.close();
    }

    // Method to display the main menu
    public static void showMenu() {
        while (true) {

            menuConsole();// Display menu options

            int option = scanner.nextInt();// Read the selected option
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addEbook();// Add a new ebook
                    break;
                case 2:
                    modifyEbook();// Modify an existing ebook
                    break;
                case 3:
                    deleteEbook();// Delete an ebook
                    break;
                case 4:
                    DigitalresourcePresentation.showMenu();
                    break;
                case 5:
                    //listEbook();// List all ebooks (not implemented)
                    break;
                case 6:
                    printColor("Exiting...","red");// Exit the program
                    return;
                default:
                    printColor("Invalid option. Please select a valid option.","red"); // Handle invalid option
            }
        }
    }

    // Method to print formatted menu with colors
    public static void menuConsole() {

        // Header
        printColor("Welcome to the Library System ", "cyan");
        printColor("EBOOK", "yellow");
        printColor("----------------------------------", "cyan");
        printColor("|        Ebook Management         |", "blue");
        printColor("----------------------------------", "cyan");
        printColor("|           Options:              |", "blue");
        printColor("|    1. Add Ebook                 |", "blue");
        printColor("|    2. Modify Ebook              |", "blue");
        printColor("|    3. Delete Ebook              |", "blue");
        printColor("|    4. Menu Digital Resource     |", "blue");
        printColor("|    6. Exit                      |", "blue");
        printColor("----------------------------------", "cyan");
        printColor("Select an option: ","white");
    }

    // Method to add a new ebook
    public static void addEbook() {
        Ebook ebook = readEbookDetails();// Read ebook details from ebook
        if (ebook != null) {
            saveEbook(ebook);// Save the ebook if details are valid
        }
    }

    public static Ebook readEbookDetails() {
        System.out.println("Enter Ebook Information:");
        String isbn = null; //ISBN is not used in this example because we only generate idDigitalResource.
        String idDigitalResource;
        do {
            idDigitalResource = generateUniqueID(8);// Generate a unique ID
        } while (isEbookIdTaken(idDigitalResource));// Ensure the ID is not already taken

        System.out.println("Generated ISBN: " + idDigitalResource);

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();


        return new Ebook(idDigitalResource, isbn, title, author, publicationDate);

    }

    // Method to save an ebook
    public static void saveEbook(Ebook ebook) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        ebookRepository.save(ebook);
        System.out.println("Ebook saved successfully.");
    }

    // Method to generate a unique ID
    public static String generateUniqueID(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    // Method to check if an ebook ID is already taken
    public static boolean isEbookIdTaken(String idDigitalResource) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        Ebook existingebook = ebookRepository.findById(idDigitalResource);
        return existingebook != null;
    }

    // Method to delete an ebook
    public static void deleteEbook() {
        System.out.print("Enter Ebook ID to delete: ");
        String idEbook = scanner.nextLine();
        if (!idEbook.isEmpty()) {
            deleteEbookById(idEbook);// Delete the ebook if the ID is valid
        } else {
            printColor("Invalid Ebook ID.", "red");// Handle invalid ID input
        }
    }

    // Method to delete an ebook by ID
    public static void deleteEbookById(String idEbook) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        ebookRepository.delete(idEbook);// Delete the ebook from the repository
        System.out.println("Ebook deleted successfully.");
    }

    // Method to modify an ebook
    public static void modifyEbook() {
        Ebook ebook = readModyEbookDetails();
        if (ebook != null) {// Read new ebook details from user
            modifyEbook(ebook);// Modify the ebook if details are valid
        }
    }

    // Method to read modified ebook details from user
    private static Ebook readModyEbookDetails() {
        System.out.println("Enter Ebook Information:");
        String isbn = null;// ISBN is not used in this example
        String idDigitalResource;
        do {
            System.out.println("Enter ID:");
            idDigitalResource = scanner.nextLine();
            if (doesEbookIdTaken(idDigitalResource)) {
                printColor("Identification does not exist. Please enter a different ID.", "red");
            }
        } while (doesEbookIdTaken(idDigitalResource));// Ensure the ID exists

        System.out.println("Generated ISBN: " + idDigitalResource);

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();


        return new Ebook(idDigitalResource, isbn, title, author, publicationDate);


    }

    // Method to check if an ebook ID does not exist (for modification)
    public static boolean doesEbookIdTaken(String isbn) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        Ebook existingUser = ebookRepository.findById(isbn);
        return existingUser == null;// Return true if the ID does not exist
    }

    // Method to modify an ebook in the repository
    private static void modifyEbook(Ebook ebook) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        ebookRepository.modify(ebook);// Modify the ebook in the repository
    }

    // Method to printColor
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