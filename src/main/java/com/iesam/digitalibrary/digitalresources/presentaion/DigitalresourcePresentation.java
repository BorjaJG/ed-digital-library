package com.iesam.digitalibrary.digitalresources.presentaion;

import com.iesam.Main;
import com.iesam.digitalibrary.digitalresources.data.DigitalResourcesDataRepository;
import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.ebook.presentation.EbookPresentation;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitalresourcePresentation {


    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Display the main menu
        showMenu();
        // Close the scanner to prevent resource leak
        scanner.close();
    }

    // Method to display the main menu
    public static void showMenu() {
        // Infinite loop for displaying the menu repeatedly
        while (true) {
            // Display the menu for digital resources
            menuConsoleBook();

            // Read the user's choice
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Handle the user's choice
            switch (option) {
                case 1:
                    // Call the method to present ebooks
                    EbookPresentation.showMenu();
                    break;
                case 2:
                    // List all digital resources
                    listUser();
                    break;
                case 3:
                    // Search for a digital resource by ID
                    searchDG();
                    break;
                case 4:
                    Main.showMainMenu();
                    break;
                case 5:
                    // Exit the program
                    printColor("Exiting...", "red");
                    return;
                default:
                    // Handle invalid option
                    printColor("Invalid option. Please select a valid option.", "red");
            }
        }
    }

    // Method to display the menu for digital resources
    public static void menuConsoleBook() {
        // Display the header and options for digital resources
        printColor("Welcome to the Library System ", "cyan");
        printColor("DIGITAL RESOURCE", "yellow");
        printColor("-----------------------------------------------", "cyan");
        printColor("|               Options:                      |", "cyan");
        printColor("|  1. Presentation Ebook                      |", "blue");
        printColor("|  2. List Recurse Digital                    |", "blue");
        printColor("|  3. Search Recurse Digital                  |", "blue");
        printColor("|  4. Return  Library                         |", "blue");
        printColor("|  5. Exit                                    |", "blue");
        printColor("-----------------------------------------------", "cyan");
        System.out.print("Select an option: ");
    }

    // Method to list all digital resources
    public static void listUser() {
        System.out.println("List of Digital Resources:");
        // Retrieve all digital resources from the data repository
        DigitalResourcesDataRepository digitalResourcesRepository = new DigitalResourcesDataRepository(new DigitalResourcesFileLocalDataSource());
        ArrayList<DigitalResource> digitalResources = digitalResourcesRepository.findAll();
        // Display information about each digital resource
        for (DigitalResource digitalResource : digitalResources) {
            System.out.println(digitalResource.toString());
        }
    }

    // Method to search for a digital resource by ID
    public static DigitalResource searchDG() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Digital Resource ID to search: ");
        // Lee el ID ingresado por el usuario
        String id = scanner.nextLine();

        // Busca el recurso digital en el repositorio de datos
        DigitalResource digitalResource = getDGById(id);

        // Verifica si el ID comienza con "m" o "e"
        if (digitalResource != null) {
            // Muestra la información si se encuentra
            System.out.println("Digital Resource found:");
            System.out.println(digitalResource.toString());

            if (id.startsWith("m")) {
                System.out.println("It's a movie."); // Es una película
                //System.out.println(.toString());
            } else if (id.startsWith("e")) {
                System.out.println("It's an ebook.");// Es un ebook
                //System.out.println(.toString());

            } else {
                System.out.println("ID doesn't start with 'm' or 'e'."); // El ID no empieza con 'm' ni 'e'
            }
        } else {
            // Informa al usuario si el recurso digital no se encuentra
            System.out.println("Digital Resource not found with ID: " + id);
        }
        return digitalResource;
    }



    // Method to retrieve a digital resource by ID from the data repository
    public static DigitalResource getDGById(String id) {
        DigitalResourcesDataRepository digitalResourcesRepository = new DigitalResourcesDataRepository(new DigitalResourcesFileLocalDataSource());
        return digitalResourcesRepository.findById(id);
    }

    // Method to print colored text
    public static void printColor(String text, String color) {
        // ANSI color codes
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        // Determine the color based on the input
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

        // Print the text in the chosen color
        System.out.println(chosenColor + text + ANSI_RESET);
    }
}