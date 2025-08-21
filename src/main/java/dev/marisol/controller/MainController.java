package dev.marisol.controller;

import dev.marisol.model.Moment;
import dev.marisol.repository.MomentsRepository;
import dev.marisol.service.MomentService;
import dev.marisol.view.AddMomentView;
import dev.marisol.view.DeleteMomentView;
import dev.marisol.view.FilterMomentListView;
import dev.marisol.view.ListMomentsView;
import dev.marisol.view.MainMenuView;
import dev.marisol.view.MessageView;
import dev.marisol.view.FilterByEmotionView;
import dev.marisol.view.FilterByDateOfView;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainController {

    private final MomentsRepository repository;
    private final MomentService momentService;
    private final AddMomentView addMomentView;
    private final MomentController momentController;
    private final MessageView messageView;
    private final MainMenuView mainMenuView;
    private final Scanner scanner;
    private final DeleteMomentView deleteMomentView;
    private final FilterMomentListView filterView;
    private final FilterByEmotionView filterEmotion;
    private final FilterByDateOfView filterDate;

    public MainController() {
        this.scanner = new Scanner(System.in);
        this.repository = new MomentsRepository();
        this.momentService = new MomentService(repository);
        this.addMomentView = new AddMomentView(scanner);
        this.messageView = new MessageView();
        this.mainMenuView = new MainMenuView(scanner);
        this.deleteMomentView = new DeleteMomentView(scanner);
        this.filterView = new FilterMomentListView(scanner);
        this.filterDate = new FilterByDateOfView(scanner);
        this.filterEmotion = new FilterByEmotionView(scanner);

        this.momentController = new MomentController(
                addMomentView,
                momentService,
                deleteMomentView,
                filterView,
                filterEmotion,
                filterDate
        );
    }

    public void start() {
        boolean clicExit = false;
        while (!clicExit) {
            int option = mainMenuView.showMenu();

            switch (option) {
                case 1: {
                    String result = momentController.addMoment();
                    messageView.messageShow(result);
                    break;
                }
                case 2: {
                    ListMomentsView list = new ListMomentsView();
                    list.listMoments(momentController.listMoments());
                    break;
                }
                case 3: {
                    String result = momentController.deleteMoment();
                    messageView.messageShow(result);
                    break;
                }
                case 4: {
                    ListMomentsView list = new ListMomentsView();
                    list.listMoments(momentController.filterMoments());
                    break;
                }
                case 5: {
                    messageView.messageShow("Hasta la próxima!!!");
                    clicExit = true;
                    break;
                }
                default:
                    messageView.messageShow("Opción no válida, por favor intente de nuevo.");
            }
        }
        scanner.close();
    }
}


