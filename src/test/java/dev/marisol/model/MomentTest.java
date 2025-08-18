package dev.marisol.model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class MomentTest {

    @Test
    public void testMomentConstructor_initializeCorrectly() {
        LocalDate testDate = LocalDate.now();

        Moment myMoment = new Moment(1, "Test de prueba", "un momento divertido", Emotion.HAPPINESS, LocalDate.now());

        assertEquals(myMoment.getId(), 1);
        assertEquals("Test de prueba", myMoment.getTitle());
        assertEquals("un momento divertido", myMoment.getDescription());
        assertEquals(Emotion.HAPPINESS, myMoment.getEmotion());
        assertEquals(testDate, myMoment.getMomentDate());
        assertNotNull(myMoment.getCreationDate());

    }

    @Test
    public void testModificationDate_initializeCorrectly() {
    LocalDate eventDate = LocalDate.of(2025, 8, 12); // Una fecha cualquiera para el evento
    Moment myMoment = new Moment(1, "Título", "Descripción", Emotion.HAPPINESS, eventDate);
    LocalDate newModificationDate = LocalDate.now(); // La nueva fecha de modificación

    myMoment.setModificationDate(newModificationDate);

    assertEquals(newModificationDate, myMoment.getModificationDate());

    assertEquals(LocalDate.now(), myMoment.getCreationDate());
}
}
