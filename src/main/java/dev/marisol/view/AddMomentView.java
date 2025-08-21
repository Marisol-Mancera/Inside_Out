package dev.marisol.view;

import dev.marisol.model.Emotion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddMomentView {
    private final Scanner scanner;

    public AddMomentView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askTitle() {
        System.out.println("Ingrese el título del momento");
        System.out.println();
        return scanner.nextLine();
    }

    public String askDescription() {
        System.out.println("Ingrese la descripción del momento");
        System.out.println();
        return scanner.nextLine();
    }

    public Emotion askEmotion() {
        System.out.println("Seleccione una emoción:");
        System.out.println();

        Emotion[] emotions = Emotion.values();
        for (int i = 0; i < emotions.length; i++) {
            String pretty = emotions[i].name().charAt(0) + emotions[i].name().substring(1).toLowerCase();
            System.out.println((i + 1) + ". " + pretty);
        }

        int option = -1;
        while (option < 1 || option > emotions.length) {
            System.out.println("Ingrese el número de la opción:");
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option < 1 || option > emotions.length) {
                    System.out.println("El número seleccionado no es una opción válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, solo admite números. Intente de nuevo.");
            }
        }
        return emotions[option - 1];
    }

    public LocalDate askDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        while (date == null) {
            System.out.print("Ingresa la fecha (dd/MM/yyyy): ");
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato no válido, intenta de nuevo.");
            }
        }
        return date;
    }

    public boolean askIsPositive() {
        System.out.println("Clasifique el momento:");
        System.out.println("1 - Positivo");
        System.out.println("2 - Negativo");
        System.out.println();

        int option = -1;
        while (option < 1 || option > 2) {
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option < 1 || option > 2) {
                    System.out.println("Opción inválida. Solo puede ingresar 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Solo puede insertar números 1 o 2.");
            }
        }
        return option == 1;
    }

     //  Método opcional: se deja comentado porque no forma parte del enunciado actual
    /*
    public boolean askIsPositive() {
        while (true) {
            System.out.println("¿Fue un momento positivo? (S/N):");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("S")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println("Respuesta no válida. Por favor introduzca S o N.");
            }
        }
    }
    */
}
