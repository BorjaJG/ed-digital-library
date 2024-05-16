package com.iesam.digitalibrary.loan.presentation;


import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.ebook.presentation.EbookPresentation;
import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;
import com.iesam.digitalibrary.loan.data.LoanDataRepository;
import com.iesam.digitalibrary.loan.data.local.LoanFileLocalDataSource;
import com.iesam.digitalibrary.loan.domain.Loan;
import com.iesam.digitalibrary.user.domain.User;
import com.iesam.digitalibrary.user.presentation.UserPresentation;

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
                    //modifyUser();
                    break;
                case 3:
                    //deleteUser();
                    break;
                case 4:
                    //searchUser();
                    break;
                case 5:
                     //listUser();
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
        String fechaI = scanner.nextLine();
        System.out.print("fechaF: ");
        String fechaF = scanner.nextLine();
        System.out.print("fechaE: ");
        String fechaE = scanner.nextLine();
        System.out.print("User: ");
        User user = UserPresentation.searchUser();
        System.out.print("DigitalResource: ");
        DigitalResource digitalResource = DigitalresourcePresentation.searchDG();
        return new Loan(idLoan, digitalResource ,user,fechaI, fechaF, fechaE);


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
        System.out.println("|  2. Modify Loan                |");
        System.out.println("|  3. Delete Loan                |");
        System.out.println("|  4. Search Loan                |");
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

    public static String generarCorreoElectronico(String nombre, String id) {
        // Format the email address: nombre + id + "@biblio.com"
        String correo = nombre + id + "@biblio.com";
        return correo;
    }


}

