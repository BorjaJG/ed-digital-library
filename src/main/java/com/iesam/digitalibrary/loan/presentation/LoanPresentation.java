package com.iesam.digitalibrary.loan.presentation;

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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
    public static void modifyLoan() {
        Loan loan = setLoanReturnDate();
        if (loan != null) {
            modifyLoan(loan);
        }
    }
    // Add a new loan
    private static void addLoan() {
        Loan loan = readLoanDetails();
        if (loan != null) {
            saveLoan(loan);
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
            System.out.print("User: ");
            user = UserPresentation.searchUser();
            if (user == null) {
                System.out.println("User not found. Please try again.");
            }

            System.out.print("DigitalResource: ");
            digitalResource = DigitalresourcePresentation.searchDG();
            if (digitalResource == null) {
                System.out.println("Digital resource not found. Please try again.");
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
        System.out.println("\nWelcome to the Library System");
        System.out.println("----------------------------------");
        System.out.println("|     Loan Management System     |");
        System.out.println("----------------------------------");
        System.out.println("|  Options:                      |");
        System.out.println("|  1. Add Loan                   |");
        System.out.println("|  2. Delete Loan                |");
        System.out.println("|  3. List Non-Outstanding Loans |");
        System.out.println("|  4. List Outstanding Loans     |");
        System.out.println("|  5. List All Loans             |");
        System.out.println("|  6. Return an eBook            |");
        System.out.println("|  7. Exit                       |");
        System.out.println("----------------------------------");
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








}
