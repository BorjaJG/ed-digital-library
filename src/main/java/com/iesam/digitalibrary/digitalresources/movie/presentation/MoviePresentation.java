package com.iesam.digitalibrary.digitalresources.movie.presentation;


import com.iesam.Main;
import com.iesam.digitalibrary.digitalresources.domain.*;
import com.iesam.digitalibrary.digitalresources.ebook.data.EbookDataRepository;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.movie.data.MovieDataRepository;
import com.iesam.digitalibrary.digitalresources.movie.data.local.MovieResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;
import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;

import java.util.List;
import java.util.Scanner;

import static com.iesam.digitalibrary.digitalresources.domain.ListDigitalResourceUseCase.getTypeFromId;


public class MoviePresentation {
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);


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
                    listMovies();
                    break;
                case 2:
                    obtainMovieId();
                    break;
                case 3:
                    DigitalresourcePresentation.showMenu();;
                    break;
                case 4:
                    printColor("Exiting...", "red");// Exit the program
                    break;
                default:
                    printColor("Invalid option. Please select a valid option.", "red"); // Handle invalid option
            }
        }
    }

    private static void obtainMovieId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame el código de la música a mostrar");
        String idDigitalResource = sc.nextLine();
        DigitalResourceRepository digitalResourceRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        GetDigitalResourceUseCase getDigitalResourceUseCase = new GetDigitalResourceUseCase(digitalResourceRepository);
        Movie movie = (Movie) getDigitalResourceUseCase.execute(idDigitalResource);
        System.out.println(movie);
    }

    private static void listMovies() {
        // Inicializa el repositorio de recursos digitales para películas
        DigitalResourceRepository digitalResourceRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());

        // Inicializa el caso de uso para listar recursos digitales
        ListDigitalResourceUseCase listDigitalResourceUseCase = new ListDigitalResourceUseCase(digitalResourceRepository);

        // Obtiene la lista de recursos digitales
        List<DigitalResource> digitalResources = listDigitalResourceUseCase.execute();

        // Itera sobre los recursos digitales y verifica si son películas
        for (DigitalResource resource : digitalResources) {
            TypeDigitalResource type = getTypeFromId(resource.idDigitalResource);
            if (type == TypeDigitalResource.MOVIE) {
                System.out.println(resource);
            }
        }
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

    // Method to print formatted menu with colors
    public static void menuConsole() {

        // Header
        printColor("Welcome to the Library System ", "cyan");
        printColor("MOVIE", "yellow");
        printColor("----------------------------------", "cyan");
        printColor("|        Movie Management         |", "blue");
        printColor("----------------------------------", "cyan");
        printColor("|           Options:              |", "blue");
        printColor("|    1. List Movies               |", "blue");
        printColor("|    2. List an Movie by its ID   |", "blue");
        printColor("|    3. Menu Digital Resource     |", "blue");
        printColor("|    4. Exit                      |", "blue");
        printColor("----------------------------------", "cyan");
        printColor("Select an option: ", "white");
    }
}
