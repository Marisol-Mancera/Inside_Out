package dev.marisol.view;

import java.time.LocalDate;
import java.util.Scanner;

public class FilterByDateOfView {
    private final Scanner scanner;

    public FilterByDateOfView(Scanner scanner) {
        this.scanner = scanner;
    }

    public LocalDate filterDate() {
        return LocalDate.now(); 
    }
}
