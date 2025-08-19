package dev.marisol.controller;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import dev.marisol.service.MomentService;
import dev.marisol.view.AddMomentView;
import dev.marisol.view.DeleteMomentView;
import dev.marisol.view.FilterByDateOfView;
import dev.marisol.view.FilterByEmotionView;
import dev.marisol.view.FilterMomentListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MomentControllerTest {

    private AddMomentView addMomentView;
    private MomentService momentService;
    private DeleteMomentView deleteView;
    private FilterMomentListView filterView;
    private FilterByEmotionView filterByEmotionView;
    private FilterByDateOfView filterByDateOfView;

    private MomentController controller;

    @BeforeEach
    void setUp() {
        addMomentView = mock(AddMomentView.class);
        momentService = mock(MomentService.class);
        deleteView = mock(DeleteMomentView.class);
        filterView = mock(FilterMomentListView.class);
        filterByEmotionView = mock(FilterByEmotionView.class);
        filterByDateOfView = mock(FilterByDateOfView.class);

        controller = new MomentController(
                addMomentView, momentService, deleteView, filterView, filterByEmotionView, filterByDateOfView
        );
    }

    @Test
    void addMoment_success_callsServiceAndReturnsMessage() {
        // Arrange
        when(addMomentView.askTitle()).thenReturn("Walk");
        when(addMomentView.askDescription()).thenReturn("A nice day out");
        when(addMomentView.askEmotion()).thenReturn(Emotion.HAPPINESS);
        when(addMomentView.askDate()).thenReturn(LocalDate.of(2023, 11, 25));
        when(momentService.getAllMoments()).thenReturn(Collections.emptyList()); // para id = size+1

        // Act
        String result = controller.addMoment();

        // Assert mensaje
        assertThat(result, is("Momento añadido correctamente"));

        // Assert llamada y contenido del Moment
        ArgumentCaptor<Moment> captor = ArgumentCaptor.forClass(Moment.class);
        verify(momentService, times(1)).addMoment(captor.capture());
        Moment saved = captor.getValue();
        assertThat(saved.getId(), is(1));
        assertThat(saved.getTitle(), is("Walk"));
        assertThat(saved.getDescription(), is("A nice day out"));
        assertThat(saved.getEmotion(), is(Emotion.HAPPINESS));
        assertThat(saved.getMomentDate(), is(LocalDate.of(2023, 11, 25)));
    }

    @Test
    void addMoment_error_returnsErrorMessage() {
        when(addMomentView.askTitle()).thenReturn("ErrorTest");
        when(addMomentView.askDescription()).thenReturn("Failure");
        when(addMomentView.askEmotion()).thenReturn(Emotion.ANGER);
        when(addMomentView.askDate()).thenReturn(LocalDate.of(2023, 11, 25));
        when(momentService.getAllMoments()).thenReturn(Collections.emptyList());

        doThrow(new RuntimeException("Failure")).when(momentService).addMoment(any(Moment.class));

        String result = controller.addMoment();

        assertThat(result, containsString("Error al añadir el momento: Failure"));
    }

    @Test
    void listMoments_returnsListFromService() {
        List<Moment> mocked = Arrays.asList(
                new Moment(1, "Trip", "Un viaje inolvidable", Emotion.HAPPINESS, LocalDate.of(2023, 5, 20))
        );
        when(momentService.getAllMoments()).thenReturn(mocked);

        List<Moment> result = controller.listMoments();

        assertThat(result, hasSize(1));
        assertThat(result.get(0).getDescription(), containsString("inolvidable"));
        verify(momentService, times(1)).getAllMoments();
    }

    @Test
    void deleteMoment_deletesByIdAndReturnsMessage() {
        when(deleteView.delete()).thenReturn(10);

        String result = controller.deleteMoment();

        assertThat(result, is("Momento eliminado correctamente."));
        verify(momentService, times(1)).deleteMoment(10);
    }

    @Test
    void filterMoments_byEmotion_returnsFilteredList() {
        when(filterView.filterMoments()).thenReturn(1);
        when(filterByEmotionView.filterEmotion()).thenReturn(Emotion.HAPPINESS);

        List<Moment> filtered = Arrays.asList(
                new Moment(1, "Happy day", "Great!", Emotion.HAPPINESS, LocalDate.of(2023, 5, 20))
        );
        when(momentService.filterByEmotion(Emotion.HAPPINESS)).thenReturn(filtered);

        List<Moment> result = controller.filterMoments();

        assertThat(result, hasSize(1));
        assertThat(result.get(0).getTitle(), containsString("Happy"));
        verify(momentService, times(1)).filterByEmotion(Emotion.HAPPINESS);
    }

    @Test
    void filterMoments_byDate_returnsMonthList() {
        when(filterView.filterMoments()).thenReturn(2);
        when(filterByDateOfView.filterDate()).thenReturn(LocalDate.of(2024, 6, 1));

        List<Moment> june = Arrays.asList(
                new Moment(2, "June note", "desc", Emotion.SADNESS, LocalDate.of(2024, 6, 3))
        );
        when(momentService.getMonthMoments(6, 2024)).thenReturn(june);

        List<Moment> result = controller.filterMoments();

        assertThat(result, hasSize(1));
        assertThat(result.get(0).getMomentDate().getMonthValue(), is(6));
        verify(momentService, times(1)).getMonthMoments(6, 2024);
    }

    @Test
    void filterMoments_invalidOption_returnsEmptyList() {
        when(filterView.filterMoments()).thenReturn(99);

        List<Moment> result = controller.filterMoments();

        assertThat(result, empty());
        verifyNoInteractions(filterByEmotionView, filterByDateOfView);
        verify(momentService, never()).filterByEmotion(any());
        verify(momentService, never()).getMonthMoments(anyInt(), anyInt());
    }
}
