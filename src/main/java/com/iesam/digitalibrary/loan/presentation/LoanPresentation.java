package com.iesam.digitalibrary.loan.presentation;

import com.iesam.Main;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;

import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;
import com.iesam.digitalibrary.loan.data.LoanDataRepository;
import com.iesam.digitalibrary.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.presentation.UserPresentation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class LoanPresentation {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();

    public static void main(String[] args) {
        showMenu();
        scanner.close();
    }

    // Display main menu
    public static void showMenu() {
        while (true) {
            displayMenuOptions();

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Add a new loan
                    addLoan();
                    break;
                case 2:
                    // Delete a loan
                    deleteLoan();
                    break;
                case 3:
                    // List loans that are not outstanding
                    listLoansNonOutstanding();
                    break;
                case 4:
                    // List outstanding loans
                    listOutstandingLoans();
                    break;
                case 5:
                    // List all loans
                    listAllLoans();
                    break;
                case 6:
                    // Modify a loan
                    modifyLoan();
                    break;
                case 7:
                    Main.showMainMenu();
                    break;
                case 8:
                    // Exit the program
                    printColor("Exiting...", "red");
                    return;
                default:
                    // Handle invalid option
                    printColor("Invalid option. Please select a valid option.", "red");
            }
        }
    }

    // Modify a loan
    public static void modifyLoan() {
        Loan loan = setLoanReturnDate();
        if (loan != null) {
            modifyLoan(loan);
            printColor("Loan modify successfully.", "green");
        }
    }

    // Add a new loan
    private static void addLoan() {
        Loan loan = readLoanDetails();
        if (loan != null) {
            saveLoan(loan);
            printColor("Loan saved successfully.", "green");
        }
    }

    // Read loan details from user
    private static Loan readLoanDetails() {
        System.out.println("Enter Loan Information:");
        String idLoan = generateUniqueID(8);
        System.out.println("Generated Loan ID: " + idLoan);
        System.out.print("Start Date: ");
        Date startDate = generateCurrentDate();
        System.out.print("End Date: ");
        Date endDate = generateDateFiveDaysAhead();
        Date returnDate = null;

        User user = null;
        DigitalResource digitalResource = null;

        while (user == null || digitalResource == null) {
            System.out.print("DigitalResource: ");
            digitalResource = DigitalresourcePresentation.searchDG();
            if (digitalResource == null) {
                System.out.println("Digital resource not found. Please try again.");
            }
            System.out.print("User: ");
            user = UserPresentation.searchUser();
            if (user == null) {
                System.out.println("User not found. Please try again.");
            }

        }

        return new Loan(idLoan, digitalResource, user, startDate, endDate, returnDate);
    }

    // Save loan to the repository
    private static void saveLoan(Loan loan) {
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        loanRepository.save(loan);
        System.out.println("Loan saved successfully.");
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

    // Generate a unique ID
    private static String generateUniqueID(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    // Delete loan
    private static void deleteLoan() {
        System.out.print("Enter Loan ID to delete: ");
        String idLoan = scanner.nextLine();
        if (!idLoan.isEmpty()) {
            deleteLoanById(idLoan);
        } else {
            System.out.println("Invalid Loan ID.");
        }
    }

    // Delete loan by ID
    private static void deleteLoanById(String idLoan) {
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        loanRepository.delete(idLoan);
        System.out.println("Loan deleted successfully.");
    }

    // Generate current date
    private static Date generateCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // Generate date five days ahead
    private static Date generateDateFiveDaysAhead() {
        LocalDate futureDate = LocalDate.now().plusDays(5);
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // List all loans
    private static void listAllLoans() {
        System.out.println("List of All Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ArrayList<Loan> loans = loanRepository.findAll();
        for (Loan loan : loans) {
            System.out.println(loan);
        }
    }

    // List outstanding loans
    private static void listOutstandingLoans() {
        System.out.println("List of Outstanding Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ArrayList<Loan> loans = loanRepository.findAll();
        for (Loan loan : loans) {
            if (loan.fechaE == null) {
                System.out.println(loan);
            }
        }
    }

    // List non-outstanding loans
    private static void listLoansNonOutstanding() {
        System.out.println("List of Non-Outstanding Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ArrayList<Loan> loans = loanRepository.findAll();
        for (Loan loan : loans) {
            if (loan.fechaE != null) {
                System.out.println(loan.toString());
            }
        }
    }

    // Set eBook return date
    private static Loan setLoanReturnDate() {
        System.out.print("Enter Loan ID to search: ");
        String idLoan = scanner.nextLine();
        Loan loan = getLoanById(idLoan);

        if (loan != null) {
            System.out.println("Loan found:");
            System.out.println(loan);
            DigitalResource digitalResource = loan.digitalResource;
            User user = loan.user;
            Date fechaI = loan.fechaI;
            Date fechaF = loan.fechaF;
            Date fechaE = generateCurrentDate();
            System.out.println(fechaE);
            return new Loan(idLoan, digitalResource, user, fechaI, fechaF, fechaE);
        } else {
            System.out.println("Loan not found with ID: " + idLoan);
        }
        System.out.println("Loan: " + loan);
        return loan;
    }

    // Get loan by ID
    private static Loan getLoanById(String idLoan) {
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        return loanRepository.findById(idLoan);
    }

    private static void modifyLoan(Loan loan) {
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        loanRepository.modify(loan);
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
