package dev.marisol.service;

import dev.marisol.model.Emotion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import dev.marisol.model.Moment;

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
    public void shouldReturnAListOfMoments() {
        Moment momentToAdd = new Moment(1, "A new moment", "Description", Emotion.HAPPINESS, LocalDate.now());

        service.addMoment(momentToAdd);

        List<Moment> moments = service.getAllMoments();

        assertEquals(1, moments.size());
        assertEquals(momentToAdd, moments.get(0));

    }

    @Test
    public void shouldDeleteAMoment() {
        Moment momentToAdd = new Moment(1, "A moment to delete", "Description", Emotion.SADNESS, LocalDate.now());
        service.addMoment(momentToAdd);

        List<Moment> moments = service.getAllMoments();
        assertEquals(1, moments.size());

        service.deleteMoment(momentToAdd.getId());

        moments = service.getAllMoments();
        assertTrue(moments.isEmpty());
    }

    @Test
    public void shouldNotDeleteAMomentIfIdDoesNotExist() {
        Moment momentToAdd = new Moment(1, "A moment to keep", "Description", Emotion.ANGER, LocalDate.now());
        service.addMoment(momentToAdd);

        List<Moment> moments = service.getAllMoments();
        assertEquals(1, moments.size());

        service.deleteMoment(999);

        moments = service.getAllMoments();
        assertEquals(1, moments.size());
        assertEquals(momentToAdd, moments.get(0));
    }

    @Test
    public void shouldReturnHappyMoments() {
        Moment happyMoment1 = new Moment(1, "Happy Day 1", "...", Emotion.HAPPINESS, LocalDate.now());
        Moment sadMoment = new Moment(2, "Sad Day", "...", Emotion.SADNESS, LocalDate.now());
        Moment happyMoment2 = new Moment(3, "Happy Day 2", "...", Emotion.HAPPINESS, LocalDate.now());

        service.addMoment(happyMoment1);
        service.addMoment(sadMoment);
        service.addMoment(happyMoment2);

        List<Moment> filteredMoments = service.filterByEmotion(Emotion.HAPPINESS);

        assertNotNull(filteredMoments);
        assertEquals(2, filteredMoments.size());
    }

    @Test
    public void shouldReturnMonthMomentWhenfilterByMonth() {
        Moment happyMoment = new Moment(1, "Happy day", "...", Emotion.HAPPINESS, LocalDate.of(2023, 10, 1));
        Moment sadMoment = new Moment(2, "Sad day", "...", Emotion.SADNESS, LocalDate.of(2023, 10, 15));
        Moment disgustMoment = new Moment(3, "Disgusting day", "...", Emotion.DISGUST, LocalDate.of(2023, 11, 20));

        service.addMoment(happyMoment);
        service.addMoment(sadMoment);
        service.addMoment(disgustMoment);

        List<Moment> filterdByMonthMoments = service.getMonthMoments(10, 2023);

        assertEquals(2, filterdByMonthMoments.size());

    }
}
