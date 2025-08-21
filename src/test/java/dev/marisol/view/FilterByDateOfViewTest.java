package dev.marisol.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByDateViewTest {

    private FilterByDateOfView viewWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(in);
        return new FilterByDateOfView(scanner);
    }

    @Test
    void shouldReturnParsedDateWhenValidDDMMYYYY() {
        FilterByDateOfView view = viewWithInput("10/06/2024\n");
        LocalDate date = view.filterDate();
        assertEquals(LocalDate.of(2024, 6, 10), date);
    }

    @Test
    void shouldRetryOnInvalidThenReturnValidDate() {
        // Primero inválido, luego válido
        FilterByDateOfView view = viewWithInput("2024-06-10\n10/06/2024\n");
        LocalDate date = view.filterDate();
        assertEquals(LocalDate.of(2024, 6, 10), date);
    }
}
