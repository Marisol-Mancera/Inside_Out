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
        System.out.println("Ingrese el título:");
        return scanner.nextLine();
    }

    public String askDescription() {
        System.out.println("Ingrese la descripción:");
        return scanner.nextLine();
    }

    public Emotion askEmotion() {
        System.out.println("Selecciona una emoción:");
        Emotion[] emotions = Emotion.values();

        for (int i = 0; i < emotions.length; i++) {
            System.out.println((i + 1) + ". " 
                + emotions[i].name().charAt(0) 
                + emotions[i].name().substring(1).toLowerCase());
        }

        int option = -1;
        while (option < 1 || option > emotions.length) {
            System.out.print("Ingrese su opción: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option < 1 || option > emotions.length) {
                    System.out.println("Número no válido, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, solo números.");
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
