package com.iesam.digitalibrary.digitalresources.movie.presentation;

import com.iesam.digitalibrary.digitalresources.movie.data.MovieDataRepository;
import com.iesam.digitalibrary.digitalresources.movie.data.local.MovieResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.movie.domain.domain.Movie;
import com.iesam.digitalibrary.digitalresources.presentaion.DigitalresourcePresentation;

import java.util.Random;
import java.util.Scanner;

public class MoviePresentation {
    // Scanner for  Movie input
    private static Scanner scanner = new Scanner(System.in);
    // Possible characters for generating unique IDs
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXY0123456789";
    // Random number generator
    private static final Random RANDOM = new Random();

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
                    addMovie();// Add a new  Movie
                    break;
                case 2:
                    modifyMovie();// Modify an existing  Movie
                    break;
                case 3:
                    deleteMovie();// Delete an  Movie
                    break;
                case 4:
                    DigitalresourcePresentation.showMenu();
                    break;
                case 5:
                    //listMovie();// List all  Movies (not implemented)
                    break;
                case 6:
                    printColor("Exiting...","red");// Exit the program
                    return;
                default:
                    printColor("Invalid option. Please select a valid option.","red"); // Handle invalid option
            }
        }
    }

    // Method to print formatted menu with colors
    public static void menuConsole() {

        // Header
        printColor("Welcome to the Library System ", "cyan");
        printColor(" MOVIE", "yellow");
        printColor("----------------------------------", "cyan");
        printColor("|        Movie Management         |", "blue");
        printColor("----------------------------------", "cyan");
        printColor("|           Options:              |", "blue");
        printColor("|    1. Add  Movie                |", "blue");
        printColor("|    2. Modify  Movie             |", "blue");
        printColor("|    3. Delete  Movie             |", "blue");
        printColor("|    4. Menu Digital Resource     |", "blue");
        printColor("|    6. Exit                      |", "blue");
        printColor("----------------------------------", "cyan");
        printColor("Select an option: ","white");
    }

    // Method to add a new Movie
    public static void addMovie() {
        Movie movie = readMovieDetails();// Read ebook details from ebook
        if (movie != null) {
            saveMovie(movie);// Save the ebook if details are valid
        }
    }

    public static Movie readMovieDetails() {
        System.out.println("Enter Movie Information:");
        String isbn = null; //ISBN is not used in this example because we only generate idDigitalResource.
        String idDigitalResource;
        do {
            idDigitalResource = generateUniqueID(8);// Generate a unique ID
        } while (isMovieIdTaken(idDigitalResource));// Ensure the ID is not already taken

        System.out.println("Generated ISBN: " + idDigitalResource);

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();


        return new Movie(idDigitalResource, isbn, title, author, publicationDate);

    }

    // Method to save an Movie
    public static void saveMovie(Movie movie) {
        MovieDataRepository movieDataRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        movieDataRepository.save(movie);
        System.out.println("Ebook saved successfully.");
    }

    // Method to generate a unique ID
    public static String generateUniqueID(int length) {
        StringBuilder sb = new StringBuilder(length);

        // Always start with 'm'
        sb.append('m');

        // Now generate the remaining characters randomly
        for (int i = 1; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    // Method to check if an ebook ID is already taken
    public static boolean isMovieIdTaken(String idDigitalResource) {
        MovieDataRepository movieDataRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        Movie existingmovie = movieDataRepository.findById(idDigitalResource);
        return existingmovie != null;
    }

    // Method to delete an ebook
    public static void deleteMovie() {
        System.out.print("Enter Movie ID to delete: ");
        String idMovie = scanner.nextLine();
        if (!idMovie.isEmpty()) {
            deleteMovieById(idMovie);// Delete the Movie if the ID is valid
        } else {
            printColor("Invalid Movie ID.", "red");// Handle invalid ID input
        }
    }

    // Method to delete an Movie by ID
    public static void deleteMovieById(String idMovie) {
        MovieDataRepository movieRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        movieRepository.delete(idMovie);// Delete the Movie from the repository
        System.out.println(" Movie deleted successfully.");
    }

    // Method to modify a Movie
    public static void modifyMovie() {
        Movie movie = readModyMovieDetails();
        if (movie != null) {// Read new Movie details from user
            modifyMovie(movie);// Modify the Movie if details are valid
        }
    }

    // Method to read modified Movie details from user
    private static Movie readModyMovieDetails() {
        System.out.println("Enter Ebook Information:");
        String isbn = null;// ISBN is not used in this example
        String idDigitalResource;
        do {
            System.out.println("Enter ID:");
            idDigitalResource = scanner.nextLine();
            if (doesMovieIdTaken(idDigitalResource)) {
                printColor("Identification does not exist. Please enter a different ID.", "red");
            }
        } while (doesMovieIdTaken(idDigitalResource));// Ensure the ID exists

        System.out.println("Generated ISBN: " + idDigitalResource);

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();


        return new Movie(idDigitalResource, isbn, title, author, publicationDate);


    }

    // Method to check if an ebook ID does not exist (for modification)
    public static boolean doesMovieIdTaken(String isbn) {
        MovieDataRepository movieDataRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        Movie existingMovie = movieDataRepository.findById(isbn);
        return existingMovie == null;// Return true if the ID does not exist
    }

    // Method to modify an ebook in the repository
    private static void modifyMovie(Movie movie) {
        MovieDataRepository movieDataRepository = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        movieDataRepository.modify(movie);// Modify the movie in the repository
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
