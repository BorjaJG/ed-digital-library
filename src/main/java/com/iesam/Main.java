package com.iesam;

import com.iesam.digitalibrary.user.presentation.UserPresentation;

import java.util.Scanner;



public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        showMainMenu();
    }
    public static void showMainMenu() {
        while (true) {
            System.out.println("\nBienvenido al sistema de gestión de la biblioteca");
            System.out.println("----------------------------------");
            System.out.println("|  1. Menú de Recursos Digitales  |");
            System.out.println("|  2. Menú de Prestamos           |");
            System.out.println("|  3. Menú de Usuarios            |");
            System.out.println("|  4. Salir                       |");
            System.out.println("----------------------------------");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    UserPresentation.showMenu();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor seleccione una opción válida.");
            }
        }
    }
}