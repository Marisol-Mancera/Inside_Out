package dev.marisol.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterMomentListViewTest {

    private FilterMomentListView viewWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        return new FilterMomentListView(new Scanner(in));
    }

    @Test
    void shouldReturn1WhenUserChoosesEmotion() {
        FilterMomentListView view = viewWithInput("1\n");
        int option = view.filterMoments();
        assertEquals(1, option);
    }

    @Test
    void shouldReturn2WhenUserChoosesDate() {
        FilterMomentListView view = viewWithInput("2\n");
        int option = view.filterMoments();
        assertEquals(2, option);
    }

     @Test
    void shouldReturn3WhenUserChoosesValoracion() {
        FilterMomentListView view = viewWithInput("3\n");
        int option = view.filterMoments();
        assertEquals(3, option);
    }

    @Test
    void shouldRetryOnNonNumericThenAcceptValid() {
        FilterMomentListView view = viewWithInput("abc\n2\n");
        int option = view.filterMoments();
        assertEquals(2, option);
    }
}
