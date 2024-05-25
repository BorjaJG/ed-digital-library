package com.iesam.digitalibrary.digitalresources.presentaion;

import com.iesam.Main;
import com.iesam.digitalibrary.digitalresources.data.DigitalResourceDataRepository;
import com.iesam.digitalibrary.digitalresources.data.local.DigitalResourceResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.domain.*;
import com.iesam.digitalibrary.digitalresources.ebook.data.EbookDataRepository;
import com.iesam.digitalibrary.digitalresources.ebook.data.local.EbookResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.ebook.domain.Ebook;
import com.iesam.digitalibrary.digitalresources.ebook.domain.EbookFactory;
import com.iesam.digitalibrary.digitalresources.ebook.presentation.EbookPresentation;
import com.iesam.digitalibrary.digitalresources.movie.data.MovieDataRepository;
import com.iesam.digitalibrary.digitalresources.movie.data.local.MovieResourcesFileLocalDataSource;
import com.iesam.digitalibrary.digitalresources.movie.domain.Movie;
import com.iesam.digitalibrary.digitalresources.movie.domain.MovieFactory;
import com.iesam.digitalibrary.digitalresources.movie.presentation.MoviePresentation;


import java.util.List;
import java.util.Scanner;

import static com.iesam.digitalibrary.digitalresources.domain.ModifyDigitalResourceUseCase.changeTypeOfId;
import static com.iesam.digitalibrary.digitalresources.domain.ModifyDigitalResourceUseCase.getTypeFromId;
import static com.iesam.digitalibrary.digitalresources.domain.NewDigitalResourceUseCase.generateUniqueIdDigitalResource;

public class DigitalresourcePresentation {


    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Display the main menu
        showMenu();
        // Close the scanner to prevent resource leak
        scanner.close();
    }

    public static void showMenu() {
        // Infinite loop for displaying the menu repeatedly
        while (true) {
            // Display the menu for digital resources
            menuConsoleBook();

            // Read the user's choice
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Handle the user's choice
            switch (option) {
                case 1:
                    // Call the method to present ebooks
                    EbookPresentation.showMenu();
                    break;
                case 2:
                    // Call the method to present movies
                    MoviePresentation.showMenu();
                    break;
                case 3:
                    // Add a new digital resource
                    addNewDigitalResource();
                    break;
                case 4:
                    // Modify an existing digital resource
                    modifyDigitalResource();
                    break;
                case 5:
                    // Delete a digital resource
                    deleteDigitalResource();
                    break;
                case 6:
                    // List all digital resources
                    listDigitalResources();
                    break;
                case 7:
                    // Obtain a digital resource by its ID
                    obtainDigitalResourceById();
                    break;
                case 8:
                    // Return to the library menu
                    Main.showMainMenu();
                    break;
                case 9:
                    // Exit the program
                    printColor("Exiting...", "red");
                    return;
                default:
                    // Handle invalid option
                    printColor("Invalid option. Please select a valid option.", "red");
            }
        }
    }


    private static void addNewDigitalResource() {
        System.out.println("Enter the type of digital resource (MOVIE or EBOOK):");
        String type = scanner.nextLine();

        if (type.equals("MOVIE")) {
            addMovie();
        } else if (type.equals("EBOOK")) {
            addEbook();
        } else {
            System.out.println("Invalid type specified.");
        }
    }

    private static void addMovie() {
        System.out.println("Enter the following details of the movie:");

        String idDigitalResource = generateUniqueIdDigitalResource(TypeDigitalResource.MOVIE, 8);
        System.out.println("ID: " + idDigitalResource);
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Director: ");
        String director = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();

        MovieFactory movieFactory = new MovieFactory();
        DigitalResourceRepository<Movie> movieDigitalResource = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        NewDigitalResourceUseCase newDigitalResourceUseCase = new NewDigitalResourceUseCase(movieDigitalResource);
        Movie movie = movieFactory.build(idDigitalResource, isbn, title, director, publicationDate);
        newDigitalResourceUseCase.execute(movie);

        // Print the details of the saved movie
        System.out.println("Details of the saved movie:");
        System.out.println("ISBN: " + movie.getIsbn());
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Publication Date: " + movie.getPublicationDate());

    }

    private static void addEbook() {
        System.out.println("Enter the following details of the ebook:");

        // Generate unique ID for the ebook
        String idDigitalResource = generateUniqueIdDigitalResource(TypeDigitalResource.EBOOK, 8);

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();

        EbookFactory ebookFactory = new EbookFactory();
        DigitalResourceRepository<Ebook> ebookDigitalResource = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        NewDigitalResourceUseCase newDigitalResourceUseCase = new NewDigitalResourceUseCase(ebookDigitalResource);
        Ebook ebook = ebookFactory.build(idDigitalResource, isbn, title, author, publicationDate);
        newDigitalResourceUseCase.execute(ebook);

        // Print the details of the saved ebook
        System.out.println("Details of the saved ebook:");
        System.out.println("ID: " + idDigitalResource);
        System.out.println("ISBN: " + ebook.getIsbn());
        System.out.println("Title: " + ebook.getTitle());
        System.out.println("Author: " + ebook.getAuthor());
        System.out.println("Publication Date: " + ebook.getPublicationDate());
    }


    // Placeholder method to modify an existing digital resource
    private static void modifyDigitalResource() {
        System.out.print("Enter the ID of the digital resource to modify: ");
        String idDigitalResource = scanner.nextLine();

        TypeDigitalResource resourceType = getTypeFromId(idDigitalResource);
        if (resourceType == null) {
            System.out.println("Invalid resource ID.");
            return;
        }

        System.out.println("The current type of the resource is: " + resourceType);
        System.out.print("Do you want to change the type of the resource? (yes/no): ");
        String changeTypeResponse = scanner.nextLine().trim().toLowerCase();

        if (changeTypeResponse.equals("yes")) {
            System.out.print("Enter the new type (EBOOK/MOVIE): ");
            String newTypeStr = scanner.nextLine().trim().toUpperCase();
            TypeDigitalResource newType = null;

            switch (newTypeStr) {
                case "EBOOK":
                    newType = TypeDigitalResource.EBOOK;
                    modifyEbook(idDigitalResource);
                    System.out.println(idDigitalResource);
                    break;
                case "MOVIE":
                    newType = TypeDigitalResource.MOVIE;
                    modifyMovie(idDigitalResource);
                    System.out.println(idDigitalResource);
                    break;
                default:
                    System.out.println("Invalid type entered.");
                    break;
            }

        }
    }

    private static void modifyMovie(String idDigitalResource) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String director = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();

        MovieFactory movieFactory = new MovieFactory();
        DigitalResourceRepository<Movie> movieDigitalResource = new MovieDataRepository(new MovieResourcesFileLocalDataSource());
        ModifyDigitalResourceUseCase modifyDigitalResourceUseCase = new ModifyDigitalResourceUseCase(movieDigitalResource);
        Movie movie = movieFactory.build(idDigitalResource, isbn, title, director, publicationDate);
        modifyDigitalResourceUseCase.execute(movie);
    }

    private static void modifyEbook(String idDigitalResource) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = scanner.nextLine();

        EbookFactory ebookFactory = new EbookFactory();
        DigitalResourceRepository<Ebook> ebookDigitalResource = new EbookDataRepository(new EbookResourcesFileLocalDataSource());
        ModifyDigitalResourceUseCase modifyDigitalResourceUseCase = new ModifyDigitalResourceUseCase(ebookDigitalResource);
        Ebook ebook = ebookFactory.build(idDigitalResource, isbn, title, author, publicationDate);
        modifyDigitalResourceUseCase.execute(ebook);

    }

    // Placeholder method to delete a digital resource
    private static void deleteDigitalResource() {
        System.out.print("Enter Digital Resource ID to delete: ");
        String idDigitalResource = scanner.nextLine();
        deleteDigitalResourcebyId(idDigitalResource);
        System.out.println("Digital Resource deleted successfully.");
    }

    public static void deleteDigitalResourcebyId(String idDigitalResource) {
        DeleteDigitalResourceUseCase deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase(new DigitalResourceDataRepository(new DigitalResourceResourcesFileLocalDataSource()));
        deleteDigitalResourceUseCase.execute(idDigitalResource);
    }

    // Placeholder method to list all digital resources
    private static void listDigitalResources() {
        DigitalResourceDataRepository digitalResourceDataRepository = new DigitalResourceDataRepository(new DigitalResourceResourcesFileLocalDataSource());
        ListDigitalResourceUseCase listDigitalResourceUseCase = new ListDigitalResourceUseCase(digitalResourceDataRepository);
        List<DigitalResource> digitalResources = listDigitalResourceUseCase.execute();
        System.out.println(digitalResources);
    }

    // Placeholder method to obtain a digital resource by its ID
    private static void obtainDigitalResourceById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Digital Resource ID to delete: ");
        String iddigitalResource = sc.nextLine();
        DigitalResourceDataRepository digitalResourceDataRepository = new DigitalResourceDataRepository(new DigitalResourceResourcesFileLocalDataSource());
        GetDigitalResourceUseCase getDigitalResourceUseCase = new GetDigitalResourceUseCase(digitalResourceDataRepository);
        DigitalResource digitalResource = getDigitalResourceUseCase.execute(iddigitalResource);
        System.out.println(digitalResource.toString());
    }


    // Method to print colored text
    public static void printColor(String text, String color) {
        // ANSI color codes
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        // Determine the color based on the input
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

        // Print the text in the chosen color
        System.out.println(chosenColor + text + ANSI_RESET);
    }

    // Method to display the menu for digital resources
    public static void menuConsoleBook() {
        // Display the header and options for digital resources
        printColor("Welcome to the Library System ", "cyan");
        printColor("DIGITAL RESOURCE", "yellow");
        printColor("-----------------------------------------------", "cyan");
        printColor("|               Options:                      |", "cyan");
        printColor("|  1. Presentation Ebook                      |", "blue");
        printColor("|  2. Presentation Movie                      |", "blue");
        printColor("|  3. New Digital Resource                    |", "blue");
        printColor("|  4. Modify Digital Resource                 |", "blue");
        printColor("|  5. Delete Digital Resource                 |", "blue");
        printColor("|  6. List Digital Resource                   |", "blue");
        printColor("|  7. Obtain Digital Resource                 |", "blue");
        printColor("|  8. Return  Library                         |", "blue");
        printColor("|  9. Exit                                    |", "blue");
        printColor("-----------------------------------------------", "cyan");
        System.out.print("Select an option: ");
    }
}