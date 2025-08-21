package dev.marisol.view;

import dev.marisol.model.Emotion;

import java.util.Scanner;

public class FilterByEmotionView {
    private final Scanner scanner;

    public FilterByEmotionView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Emotion filterEmotion() {
        AddMomentView add = new AddMomentView(scanner);
        return add.askEmotion();
    }
}
