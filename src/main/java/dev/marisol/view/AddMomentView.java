package dev.marisol.view;

import dev.marisol.model.Emotion;
import java.time.LocalDate;
import java.util.Scanner;

public class AddMomentView {
    private final Scanner scanner;

    public AddMomentView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askTitle() { return ""; }          
    public String askDescription() { return ""; }    
    public Emotion askEmotion() { return Emotion.HAPPINESS; } 
    public LocalDate askDate() { return LocalDate.now(); }  
}
