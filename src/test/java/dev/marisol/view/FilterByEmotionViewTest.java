package dev.marisol.view;

import dev.marisol.model.Emotion;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByEmotionViewTest {

    private FilterByEmotionView viewWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(in);
        return new FilterByEmotionView(scanner);
    }

    @Test
    void shouldReturnHappinessWhenOption1() {
        FilterByEmotionView view = viewWithInput("1\n");
        Emotion result = view.filterEmotion();
        assertEquals(Emotion.HAPPINESS, result);
    }

    
}
