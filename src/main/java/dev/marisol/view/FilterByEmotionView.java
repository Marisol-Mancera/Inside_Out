package dev.marisol.view;

import dev.marisol.model.Emotion;
import java.util.Scanner;


public class FilterByEmotionView {
    private final Scanner scanner;

    public FilterByEmotionView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Emotion filterEmotion() {
        Emotion[] emotions = Emotion.values();

        System.out.println("Seleccione una emoción:");
        for (int i = 0; i < emotions.length; i++) {
            System.out.println((i + 1) + ". " + emotions[i].name().charAt(0) + emotions[i].name().substring(1).toLowerCase());
        }

        int option = -1;
        while (option < 1 || option > emotions.length) {
            System.out.print("Seleccione el número de la opción: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option < 1 || option > emotions.length) {
                    System.out.println("El número seleccionado no es válido. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, solo admite números. Intente de nuevo.");
            }
        }

        return emotions[option - 1];
    }
}

