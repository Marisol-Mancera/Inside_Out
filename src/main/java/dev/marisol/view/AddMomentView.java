package dev.marisol.view;

import dev.marisol.model.Emotion;
import java.time.LocalDate;
import java.util.Scanner;

public class AddMomentView {
    private final Scanner scanner;

    public AddMomentView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askTitle() { return ""; }          // dummy
    public String askDescription() { return ""; }    // dummy
    public Emotion askEmotion() { return Emotion.HAPPINESS; } // dummy
    public LocalDate askDate() { return LocalDate.now(); }    // dummy
}
