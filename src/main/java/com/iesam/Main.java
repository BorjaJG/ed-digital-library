package com.iesam;

import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;
import com.iesam.digitalibrary.loan.presentation.LoanPresentation;
import com.iesam.digitalibrary.user.presentation.UserPresentation;

import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        while (true) {
            printColor("Welcome to the Library ", "cyan");
            printColor("----------------------------------", "cyan");
            printColor("|  1. Digital Resources Menu     |", "blue");
            printColor("|  2. Loans Menu                 |", "blue");
            printColor("|  3. Users Menu                 |", "blue");
            printColor("|  4. Exit                       |", "blue");
            printColor("----------------------------------", "cyan");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    DigitalresourcePresentation.showMenu();
                    break;
                case 2:
                    LoanPresentation.showMenu();
                    break;
                case 3:
                    UserPresentation.showMenu();
                    break;
                case 4:
                    printColor("Exiting...","red");// Exit the program
                    return;
                default:
                    printColor("Invalid option. Please select a valid option.","red"); // Handle invalid option
            }
        }
    }


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