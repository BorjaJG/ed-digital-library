package com.iesam.digitalibrary.digitalresources.ebook.presentation;

import com.iesam.digitalibrary.digitalresources.ebook.data.EbookDataRepository;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.ebook.domain.EbookRepository;
import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;

import java.util.Random;
import java.util.Scanner;

import static com.iesam.digitalibrary.user.presentation.UserPresentation.isUserIdTaken;

public class EbookPresentation {

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
                    addEbook();
                    break;
                case 2:
                    //modifyEbook();
                    break;
                case 3:
                    deleteEbook();
                    break;
                case 4:
                    //searchEbook();
                    break;
                case 5:
                    //listEbook();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    public static void menuConsola() {
        System.out.println("\nBienvenido al sistema de la biblioteca");
        System.out.println("----------------------------------");
        System.out.println("|     User Management System     |");
        System.out.println("----------------------------------");
        System.out.println("|  Options:                      |");
        System.out.println("|  1. Add Ebook                  |");
        System.out.println("|  2. Modify Ebook               |");
        System.out.println("|  3. Delete Ebook               |");
        System.out.println("|  6. Exit                       |");
        System.out.println("----------------------------------");
        System.out.print("Select an option: ");
    }

    public static void addEbook() {
        Ebook ebook = readUserDetails();
        if (ebook != null) {
            saveEbook(ebook);
        }
    }

    public static Ebook readUserDetails() {
        System.out.println("Enter Ebook Information:");
        //String userId = generateUniqueID(8);
        String isbn = null;
        String idDigitalResource;
        do {
            idDigitalResource = generateUniqueID(8);
        } while (isEbookIdTaken(idDigitalResource));

        System.out.println("Generated ISBN: " + idDigitalResource);

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();


        return new Ebook(idDigitalResource, isbn, title, author, publicationDate);

    }


    public static void saveEbook(Ebook ebook) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        ebookRepository.save(ebook);
        System.out.println("Ebook saved successfully.");
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

    public static boolean isEbookIdTaken(String idDigitalResource) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        Ebook existingebook = ebookRepository.findById(idDigitalResource);
        return existingebook != null;
    }


    public static void deleteEbook() {
        System.out.print("Enter Ebook ID to delete: ");
        String idEbook = scanner.nextLine();
        if (!idEbook.isEmpty()) {
            deleteEbookById(idEbook);
        } else {
            System.out.println("Invalid Ebook ID.");
        }
    }

    public static void deleteEbookById(String idEbook) {
        EbookDataRepository ebookRepository = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        ebookRepository.delete(idEbook);
        System.out.println("Ebook deleted successfully.");
    }


}
