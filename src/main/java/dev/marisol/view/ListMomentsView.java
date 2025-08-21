package dev.marisol.view;

import dev.marisol.model.Moment;
import java.util.List;

public class ListMomentsView {

    public ListMomentsView() {
    }

    public void listMoments(List<Moment> moments) {
        if (moments == null || moments.isEmpty()) {
            System.out.println("No existen momentos para mostrar, añada momentos vividos en la opción 1");
        } else {
            System.out.println("Lista de momentos vividos:");
            for (Moment moment : moments) {
                System.out.println("Ocurrió el: " + moment.getMomentDate() + 
                                   ". Título: " + moment.getTitle() +
                                   ". Descripción: " + moment.getDescription() +
                                   ". Emoción: " + moment.getEmotion());
            }
            System.out.println();
        }
    }
}

