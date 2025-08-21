package dev.marisol.view;

import java.time.LocalDate;
import java.util.Scanner;

public class FilterByDateView {
    private final Scanner scanner;

    public FilterByDateView(Scanner scanner) {
        this.scanner = scanner;
    }

    public LocalDate filterDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        while (date == null) {
            System.out.print("Ingresa la fecha (dd/mm/yyyy): ");
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato no válido, intenta de nuevo.");
            }
        }
        return date;
    }
}


