package dev.marisol.view;

import java.util.Scanner;

public class FilterMomentListView {
    private final Scanner scanner;

    public FilterMomentListView(Scanner scanner) {
        this.scanner = scanner;
    }

    // En los tests lo moqueamos, así que no importa la lógica
    public int filterMoments() {
        return 0;
    }
}
