package dev.marisol.view;

import dev.marisol.model.Emotion;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AddMomentViewTest {

    private AddMomentView createViewWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        java.util.Scanner scanner = new java.util.Scanner(in);
        return new AddMomentView(scanner);
    }

    @Test
    void askTitle_shouldReturnTypedTitle() {
        // Simula escribir el título y Enter
        AddMomentView view = createViewWithInput("Un día en la playa\n");
        String title = view.askTitle();
        assertEquals("Un día en la playa", title);

    }

    @Test
    void askDescription_shouldReturnTypedDescription() {
        AddMomentView view = createViewWithInput("Descripción del momento\n");
        String desc = view.askDescription();
        assertEquals("Descripción del momento", desc);
    }

     @Test
    void askDate_shouldParseValidDDMMYYYY() {
        AddMomentView view = createViewWithInput("01/05/2024\n");
        LocalDate date = view.askDate();
        assertEquals(LocalDate.of(2024, 5, 1), date);
    }

}