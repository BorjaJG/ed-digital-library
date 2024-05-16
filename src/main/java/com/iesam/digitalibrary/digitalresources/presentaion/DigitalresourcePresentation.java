package com.iesam.digitalibrary.digitalresources.presentaion;

import com.iesam.digitalibrary.digitalresources.data.DigitalResourcesDataRepository;
import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.DigitalResource;
import com.iesam.digitalibrary.digitalresources.ebook.presentation.EbookPresentation;
import com.iesam.digitalibrary.user.data.UserDataRepository;
import com.iesam.digitalibrary.user.data.local.UserFileLocalDataSource;
import com.iesam.digitalibrary.user.domain.User;

import java.util.ArrayList;
import java.util.Scanner;

public class DigitalresourcePresentation {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
        scanner.close();
    }

    public static void showMenu() {
        while (true) {
            menuConsoleBook();

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    EbookPresentation.showMenu();
                    break;
                case 2:
                    listUser();
                    break;
                case 3:
                    searchDG();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    public static void menuConsoleBook() {
        System.out.println("\nWelcome to the Book Presentation Management System");
        System.out.println("-----------------------------------------------");
        System.out.println("|               Options:                      |");
        System.out.println("|  1. Presentation Book                       |");
        System.out.println("|  2. List Recurse Digital                    |");
        System.out.println("|  4. Exit                                    |");
        System.out.println("-----------------------------------------------");
        System.out.print("Select an option: ");
    }

    public static void listUser() {
        System.out.println("List of Users:");
        DigitalResourcesDataRepository digitalResourcesRepository = new DigitalResourcesDataRepository(new DigitalResourcesFileLocalDataSource());
        ArrayList<DigitalResource> digitalResources = digitalResourcesRepository.findAll();
        for (DigitalResource digitalResource : digitalResources) {
            System.out.println(digitalResource.toString());
        }
    }

    public static DigitalResource searchDG() {
        System.out.print("Enter User ID to search: ");
        String id = scanner.nextLine();
        DigitalResource digitalResource = getDGById(id);
        if (digitalResource != null) {
            System.out.println("User found:");
            System.out.println(digitalResource.toString());
        } else {
            System.out.println("User not found with ID: " + id);
        }
        return digitalResource;
    }
    public static DigitalResource getDGById(String id) {
        DigitalResourcesDataRepository digitalResourcesRepository = new DigitalResourcesDataRepository(new DigitalResourcesFileLocalDataSource());
        return digitalResourcesRepository.findById(id);
    }


}
