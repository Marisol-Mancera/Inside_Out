package dev.marisol;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import model.Emotion;
import model.Moment;

public class MomentTest {

    @Test
    public void testMomentConstructor_initializeCorrectly() {
        LocalDate testDate = LocalDate.now();

        Moment myMoment = new Moment(1, "Test de prueba", "un momento divertido", Emotion.HAPPINESS, LocalDate.now());

        assertEquals(myMoment.getId(), 1);
        assertEquals(myMoment.getTitle(), "Test de prueba");
        assertEquals("un momento divertido", myMoment.getDescription());
        assertEquals(Emotion.HAPPINESS, myMoment.getEmotion());
        assertEquals(testDate, myMoment.getMomentDate());
        assertNotNull(myMoment.getCreationDate());
        // assertNotNull(myMoment.getModificationDate());

    }

    @Test
    public void testModificationDate_initializeCorrectly() {
        // Crea un Moment con una fecha inicial
        LocalDate initialDate = LocalDate.of(2025, 8, 12);
        Moment myMoment = new Moment(1, "Título de prueba", "Descripción inicial", Emotion.HAPPINESS, initialDate);

        // Define una nueva fecha de modificación
        LocalDate newModificationDate = LocalDate.of(2025, 8, 13);

        // Llama al setter para actualizar la fecha
        myMoment.setModificationDate(newModificationDate);

        // Verifica que la fecha de modificación se ha actualizado
        assertEquals(newModificationDate, myMoment.getModificationDate());

        // Opcional: Asegúrate de que la fecha de creación no ha cambiado
        assertEquals(initialDate, myMoment.getCreationDate());
    }
}
