package dev.marisol.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteMomentViewTest {

    private DeleteMomentView createViewWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        return new DeleteMomentView(new Scanner(in));
    }

    @Test
    void shouldAcceptPositiveIntegerOnFirstTry() {
        DeleteMomentView view = createViewWithInput("7\n");
        int id = view.delete();
        assertEquals(7, id);
    }

    @Test
    void shouldRejectNonNumericThenAcceptValid() {
        DeleteMomentView view = createViewWithInput("abc\n5\n");
        int id = view.delete();
        assertEquals(5, id);
    }

      @Test
    void shouldRejectNegativeAndZeroThenAcceptValid() {
        DeleteMomentView view = createViewWithInput("-3\n0\n9\n");
        int id = view.delete();
        assertEquals(9, id);
    }
}
