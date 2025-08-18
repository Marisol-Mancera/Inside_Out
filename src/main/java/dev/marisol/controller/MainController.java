package dev.marisol.controller;


import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import dev.marisol.service.MomentService;


public class MainController {

    private final Scanner scanner;
    private MomentService momentService;


    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

     public void setMomentService(MomentService service) {
        this.momentService = service;
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            try { // <-- El try debe ir aquí, envolviendo todo el bucle
                System.out.println("My diario:");
                System.out.println("1. Añadir momento");
                System.out.println("2. Ver todos los momentos disponibles");
                System.out.println("3. Eliminar un momento");
                System.out.println("4. Filtrar los momentos");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                if (option == 5) {
                    System.out.println("Hasta la próxima!!!");
                    exit = true;
                } else if (option == 1) {
                    System.out.print("Título del momento: ");
                    String momentTitle = scanner.nextLine();

                    System.out.print("Descripción del momento: ");
                    String momentDescription = scanner.nextLine();

                    System.out.print("Fecha del momento (dd/MM/yyyy): ");
                    String momentDate = scanner.nextLine();

                    System.out.print("Emoción del momento (1-5): ");
                    int momentEmotion = scanner.nextInt();
                    scanner.nextLine();

                    Moment newMoment = new Moment(1, momentTitle, momentDescription, Emotion.HAPPINESS, LocalDate.of(2024, 5, 1));
                    System.out.println("Momento creado: " + newMoment);
                }
            } catch (InputMismatchException e) { // <-- El catch debe ir aquí, después del try
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                scanner.nextLine(); 
            }
        }
    }
}