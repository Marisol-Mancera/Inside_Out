package dev.marisol.view;

import dev.marisol.model.Emotion;
import java.util.Scanner;

public class FilterByEmotionView {
    private final Scanner scanner;

    public FilterByEmotionView(Scanner scanner) {
        this.scanner = scanner;
    }

    /** En tests se mokea; aquí solo firma mínima. */
    public Emotion filterEmotion() {
        return Emotion.HAPPINESS; // valor dummy, no se usa en tests porque se mokea
    }
}
