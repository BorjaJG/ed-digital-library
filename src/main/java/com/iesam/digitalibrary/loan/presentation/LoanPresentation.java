package com.iesam.digitalibrary.loan.presentation;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;
import com.iesam.digitalibrary.loan.data.LoanDataRepository;
import com.iesam.digitalibrary.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.presentation.UserPresentation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class LoanPresentation {
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
                    addLoan();
                    break;
                case 2:
                    deleteLoan();
                    break;
                case 3:
                    //listLoanNotOutstanding();
                    break;
                case 4:
                    listLoanOutstanding();
                    break;
                case 5:
                    listLoan();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    public static void addLoan() {
        Loan loan = readUserDetails();
        if (loan != null) {
            saveLoan(loan);
        }
    }

    public static Loan readUserDetails() {
        System.out.println("Enter Loan Information:");
        String idLoan;
        idLoan = generateUniqueID(8);
        System.out.println("Generated Loan ID: " + idLoan);
        System.out.print("fechaI: ");
        Date fechaI = generateDate();
        System.out.print("fechaF: ");
        Date fechaF = generateDateFiveDaysAhead();
        System.out.println("fechaE: No se tine este dato aun ");
        Date fechaE = null;
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
        return new Loan(idLoan, digitalResource, user, fechaI, fechaF, fechaE);


    }


    public static void saveLoan(Loan loan) {
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        loanRepository.save(loan);
        System.out.println("Loan saved successfully.");
    }

    public static void menuConsola() {
        System.out.println("\nBienvenido al sistema de la biblioteca");
        System.out.println("----------------------------------");
        System.out.println("|     User Management System     |");
        System.out.println("----------------------------------");
        System.out.println("|  Options:                      |");
        System.out.println("|  1. Add Loan                   |");
        System.out.println("|  2. Delete Loan                |");
        System.out.println("|  3. List Loan not Outstanding  |");
        System.out.println("|  4. List  Outstanding         |");
        System.out.println("|  5. List All Loans             |");
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

    public static void deleteLoan() {
        System.out.print("Enter Loan ID to delete: ");
        String idLoan = scanner.nextLine();
        if (!idLoan.isEmpty()) {
            deleteLoanById(idLoan);
        } else {
            System.out.println("Invalid Loan ID.");
        }
    }

    public static void deleteLoanById(String idLoan) {
        LoanDataRepository LoanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        LoanRepository.delete(idLoan);
        System.out.println("Loan deleted successfully.");
    }


    public static Date generateDate() {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);
        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static Date generateDateFiveDaysAhead() {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(5);
        System.out.println("Date Five Days Ahead: " + futureDate);
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    private static void listLoan() {
        System.out.println("List of Loans:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ArrayList<Loan> loans = loanRepository.findAll();
        for (Loan loan: loans) {
            System.out.println(loan.toString());
        }
    }


    private static void listLoanOutstanding() {
        System.out.println("List of Loans Outstanding:");
        LoanDataRepository loanRepository = new LoanDataRepository(new LoanFileLocalDataSource());
        ArrayList<Loan> loans = loanRepository.findAll();
        for (Loan loan: loans) {
            if (loan.fechaE == null) {
                System.out.println(loan.toString());
            }
        }
    }













}

