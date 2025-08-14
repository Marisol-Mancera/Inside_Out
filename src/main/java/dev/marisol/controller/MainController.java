package dev.marisol.controller;

import java.util.Scanner;

public class MainController {

    // clase
    private final Scanner scanner;

    // constructor
    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("My diario:");
            System.out.println("1. Añadir momento");
            System.out.println("2. Ver todos los momentos disponibles");
            System.out.println("3. Eliminar un momento");
            System.out.println("4. Filtrar los momentos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            if (option == 5) {
                System.out.println("Hasta la próxima!!!");
                exit = true;
            } else if (option == 1) {

            }
        }
    }
}
