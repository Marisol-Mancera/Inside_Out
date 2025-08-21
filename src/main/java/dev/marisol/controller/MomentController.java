package dev.marisol.controller;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import dev.marisol.service.MomentService;
import dev.marisol.view.AddMomentView;
import dev.marisol.view.DeleteMomentView;
import dev.marisol.view.FilterMomentListView;   
import dev.marisol.view.FilterByEmotionView;    
import dev.marisol.view.FilterByDateOfView;     
import dev.marisol.view.FilterByCategoryView;   

import java.time.LocalDate;
import java.util.List;

public class MomentController {

    private final AddMomentView addMomentView;
    private final DeleteMomentView deleteView;
    private final FilterMomentListView filterView;
    private final FilterByEmotionView filterByEmotionView;
    private final FilterByDateOfView filterByDateOfView;
    private final FilterByCategoryView filterByCategoryView;

    private MomentService momentService;

    public MomentController(AddMomentView addMomentView,
                            MomentService momentService,
                            DeleteMomentView deleteMomentView,
                            FilterMomentListView filterView,
                            FilterByEmotionView filterEmotionView,
                            FilterByDateOfView filterDateOfView,
                            FilterByCategoryView filterByCategoryView) {

        this.addMomentView = addMomentView;
        this.momentService = momentService;
        this.deleteView = deleteMomentView;
        this.filterView = filterView;
        this.filterByEmotionView = filterEmotionView;
        this.filterByDateOfView = filterDateOfView;
        this.filterByCategoryView = filterByCategoryView;
    }

    public void setMomentService(MomentService service) {
        this.momentService = service;
    }

    public String addMoment() {
        try {
            AddMomentDTO dto = new AddMomentDTO(
                addMomentView.askTitle(),
                addMomentView.askDescription(),
                addMomentView.askEmotion(),
                addMomentView.askDate(),
                addMomentView.askIfIsGood()
            );

            momentService.addMoment(dto);

            return "Momento añadido correctamente.";
        } catch (Exception e) {
            return "Error al añadir el momento: " + e.getMessage();
        }
    }

    public List<String> listMoments() {
        return momentService.listMoments();
    }

    public String deleteMoment() {
        int opcion = deleteView.delete();
        return momentService.deleteMoment(opcion);
    }

    public List<String> filterMoments() {
        int option = filterView.filterMoments();
        switch (option) {
            case 1: {
                Emotion selectedEmotion = filterByEmotionView.filterEmotion();
                return momentService.filterByEmotion(selectedEmotion);
            }
            case 2: {
                LocalDate selectedDate = filterByDateOfView.filterDate();
                return momentService.filterByDate(selectedDate);
            }
            case 3: {
                boolean category = filterByCategoryView.filterCategory(); 
                return momentService.filterByCategory(category);
            }
            default:
                return List.of("Opción no válida");
        }
    }
}
