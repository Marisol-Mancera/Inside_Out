package dev.marisol.services;

import dev.marisol.model.Emotion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import dev.marisol.model.Moment;
import dev.marisol.services.MomentService;

public class MomentServiceTest {
    private MomentService service;

    @BeforeEach
    public void setUp() { 
        service = new MomentService();
    }
    


    @Test
    public void shouldReturnEmptyListWhenNoMoments() {

       List<Moment> moments = service.getAllMoments();

        assertNotNull(moments);
        assertTrue(moments.isEmpty());

    }

    @Test
    public void shouldReturnAListOfMoments(){
        Moment momentToAdd = new Moment(1, "A new moment", "Description", Emotion.HAPPINESS, LocalDate.now());

        service.addMoment(momentToAdd);

        List<Moment> moments = service.getAllMoments();

        assertEquals(1, moments.size());
        assertEquals(momentToAdd, moments.get(0));

}
}
