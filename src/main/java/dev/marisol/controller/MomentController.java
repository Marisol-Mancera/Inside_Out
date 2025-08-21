package dev.marisol.controller;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import dev.marisol.service.MomentService;
import dev.marisol.view.AddMomentView;
import dev.marisol.view.DeleteMomentView;
import dev.marisol.view.FilterByDateOfView;
import dev.marisol.view.FilterByEmotionView;
import dev.marisol.view.FilterMomentListView;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MomentController {

    private final AddMomentView addMomentView;
    private MomentService momentService;
    private final DeleteMomentView deleteView;
    private final FilterMomentListView filterView;       
    private final FilterByEmotionView filterByEmotionView;
    private final FilterByDateOfView filterByDateOfView;      

    public MomentController(AddMomentView addMomentView,
                            MomentService momentService,
                            DeleteMomentView deleteMomentView,
                            FilterMomentListView filterView,         
                            FilterByEmotionView filterEmotionView,
                            FilterByDateOfView filterDateView) {        
        this.addMomentView = Objects.requireNonNull(addMomentView);
        this.momentService = Objects.requireNonNull(momentService);
        this.deleteView = Objects.requireNonNull(deleteMomentView);
        this.filterView = Objects.requireNonNull(filterView);
        this.filterByEmotionView = Objects.requireNonNull(filterEmotionView);
        this.filterByDateOfView = Objects.requireNonNull(filterDateView);  
    }

    public void setMomentService(MomentService service) {
        this.momentService = Objects.requireNonNull(service);
    }

    public String addMoment() {
        try {
            String title = addMomentView.askTitle();
            String description = addMomentView.askDescription();
            Emotion emotion = addMomentView.askEmotion();
            LocalDate date = addMomentView.askDate();

            int nextId = momentService.getAllMoments().size() + 1;
            Moment toSave = new Moment(nextId, title, description, emotion, date);

            momentService.addMoment(toSave);
            return "Momento añadido correctamente";
        } catch (Exception e) {
            return "Error al añadir el momento: " + e.getMessage();
        }
    }

    public List<Moment> listMoments() {
        List<Moment> moments = momentService.getAllMoments();
        return (moments != null) ? moments : Collections.emptyList();
    }

    public String deleteMoment() {
        int id = deleteView.delete();
        momentService.deleteMoment(id);
        return "Momento eliminado correctamente.";
    }

    public List<Moment> filterMoments() {
        int option = filterView.filterMoments();
        switch (option) {
            case 1: {
                Emotion selected = filterByEmotionView.filterEmotion();
                return momentService.filterByEmotion(selected);
            }
            case 2: {
                LocalDate selectedDate = filterByDateOfView.filterDate();   
                return momentService.getMonthMoments(selectedDate.getMonthValue(), selectedDate.getYear());
            }
            default:
                return Collections.emptyList();
        }
    }
}
