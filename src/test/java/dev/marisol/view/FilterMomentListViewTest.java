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
        // usuario escribe "1" y Enter
        FilterMomentListView view = viewWithInput("1\n");
        int option = view.filterMoments();
        assertEquals(1, option);
    }

    
}
