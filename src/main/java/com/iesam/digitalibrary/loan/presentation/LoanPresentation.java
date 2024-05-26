package com.iesam.digitalibrary.loan.presentation;

import com.iesam.Main;
import com.iesam.digitalibrary.digitalresources.data.DigitalResourceDataRepository;
import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourceResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

import com.iesam.digitalibrary.digitalresources.domain.DigitalResourceRepository;
import com.iesam.digitalibrary.loan.data.LoanDataRepository;
import com.iesam.digitalibrary.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitalibrary.loan.domain.*;
import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.domain.UserRepository;

import java.util.*;

import static com.iesam.digitalibrary.loan.domain.NewLoanUseCase.*;

public class LoanPresentation {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        showMenu();
        scanner.close();
    }

    // Display main menu
    public static void showMenu() {
        while (true) {
            displayMenuOptions();

            int option = getIntInput();

            switch (option) {
                case 1:
                    addLoan();
                    break;
                case 2:
                    deleteLoan();
                    break;
                case 3:
                    listLoansNonOutstanding();
                    break;
                case 4:
                    listOutstandingLoans();
                    break;
                case 5:
                    listAllLoans();
                    break;
                case 6:
                    modifyLoan();
                    break;
                case 7:
                    // Return to the library menu
                    Main.showMainMenu();
                    break;
                case 8:
                    exitProgram();
                    return;
                default:
                    handleInvalidOption();
            }
        }
    }

    private static void modifyLoan() {
        // Obtain user input
        System.out.println("Enter Loan Information:");
        System.out.println("Enter Loan ID: ");
        String idLoan = scanner.nextLine();
        System.out.println("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter Digital Resource ID: ");
        String idDigitalResource = scanner.nextLine();

        // Generate current date for fechaE
        Date fechaE = UpdateLoanUseCase.generateCurrentDate();

        // Initialize repositories and factory
        UserDataRepository userDataRepository = new UserDataRepository(new UserFileLocalDataSource());
        DigitalResourceRepository digitalResourceRepository = new DigitalResourceDataRepository(new DigitalResourceResourcesFileLocalDataSource());

        LoanFactory loanFactory = new LoanFactory();
        LoanRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());

        // Create and execute the use case
        UpdateLoanUseCase updateLoanUseCase = new UpdateLoanUseCase(loanRepository, digitalResourceRepository, userDataRepository, loanFactory);
        updateLoanUseCase.execute(idLoan, userId, idDigitalResource, null, null, fechaE);
    }

    private static void addLoan() {
        // Generate and display a unique loan ID
        String idLoan = NewLoanUseCase.generateUniqueIdLoan(8);
        System.out.println("Generated Loan ID: " + idLoan);

        // Get the user ID from input
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();
        UserDataRepository userDataRepository = new UserDataRepository(new UserFileLocalDataSource());

        // Get the digital resource ID from input
        System.out.println("Enter Digital Resource ID:");
        String idDigitalResource = scanner.nextLine();
        DigitalResourceRepository digitalResourceRepository = new DigitalResourceDataRepository(new DigitalResourceResourcesFileLocalDataSource());

        // Generate and display the start and end dates for the loan
        Date fechaI = NewLoanUseCase.generateCurrentDate();
        System.out.println("Start Date: " + fechaI);
        Date fechaF = NewLoanUseCase.generateDateFiveDaysAhead();
        System.out.println("End Date: " + fechaF);

        // Initialize the loan factory
        LoanFactory loanFactory = new LoanFactory();

        // Initialize the loan repository
        LoanRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());

        // Create an instance of the use case for adding a new loan
        NewLoanUseCase newLoanUseCase = new NewLoanUseCase(loanRepository, digitalResourceRepository, userDataRepository, loanFactory);

        // Execute the use case to add a new loan
        newLoanUseCase.execute(idLoan, userId, idDigitalResource, fechaI, fechaF);
    }

    private static void deleteLoan() {
        System.out.println("Enter Loan Information:");
        String code = scanner.nextLine();
        DeleteLoanUseCase deleteLoanUseCase = new DeleteLoanUseCase(new LoanDataRepository(new LoanFileLocalDataSource()));
        deleteLoanUseCase.execute(code);
    }

    private static void listAllLoans() {
        System.out.println("List of All Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ListLoanUseCase listLoanUseCase = new ListLoanUseCase(loanRepository);
        List<Loan> loans = listLoanUseCase.execute();
        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    private static void listOutstandingLoans() {
        System.out.println("List of Outstanding Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ListLoanUseCase listLoanUseCase = new ListLoanUseCase(loanRepository);
        List<Loan> loans = listLoanUseCase.execute();
        for (Loan loan : loans) {
            if (loan.fechaE == null) {
                System.out.println(loan);
            }
        }
    }

    private static void listLoansNonOutstanding() {
        System.out.println("List of Non-Outstanding Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ListLoanUseCase listLoanUseCase = new ListLoanUseCase(loanRepository);
        List<Loan> loans = listLoanUseCase.execute();
        for (Loan loan : loans) {
            if (loan.fechaE != null) {
                System.out.println(loan);
            }
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program...");
        scanner.close();
        System.exit(0);
    }

    private static void handleInvalidOption() {
        System.out.println("Invalid option. Please try again.");
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

    // Display menu options
    private static void displayMenuOptions() {
        // Print the header and menu with colors
        printColor("Welcome to the Library System", "cyan");
        printColor("----------------------------------", "cyan");
        printColor("|     Loan Management System     |", "cyan");
        printColor("----------------------------------", "cyan");
        printColor("|  Options:                      |", "cyan");
        printColor("|  1. Add Loan                   |", "blue");
        printColor("|  2. Delete Loan                |", "blue");
        printColor("|  3. List Non-Outstanding Loans |", "blue");
        printColor("|  4. List Outstanding Loans     |", "blue");
        printColor("|  5. List All Loans             |", "blue");
        printColor("|  6. Return an DG               |", "blue");
        printColor("|  7. Return Library             |", "blue");
        printColor("|  8. Exit                       |", "blue");
        printColor("----------------------------------", "cyan");
        System.out.print("Select an option: ");
    }
    private static int getIntInput() {
        int option;
        while (true) {
            try {
                option = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return option;
    }
}
