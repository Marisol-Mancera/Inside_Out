package dev.marisol.controller;

import dev.marisol.model.Moment;
import dev.marisol.service.MomentService;
import dev.marisol.view.AddMomentView;
import dev.marisol.view.DeleteMomentView;
import dev.marisol.view.FilterMomentListView;
import dev.marisol.view.FilterByEmotionView;
import dev.marisol.view.FilterByDateOfView;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainController {

    private final Scanner scanner;

    private MomentService momentService;
    private MomentController momentController;

    private AddMomentView addMomentView;
    private DeleteMomentView deleteMomentView;
    private FilterMomentListView filterMomentListView;
    private FilterByEmotionView filerByEmotionView;
    private FilterByDateOfView filterByDateOfView;

    public MainController(Scanner scanner) {
        this.scanner = scanner;

        this.momentService = new MomentService();

        this.addMomentView = new AddMomentView(this.scanner);
        this.deleteMomentView = new DeleteMomentView(this.scanner);
        this.filterMomentListView = new FilterMomentListView(this.scanner);
        this.filerByEmotionView = new FilterByEmotionView(this.scanner);
        this.filterByDateOfView = new FilterByDateOfView(this.scanner);

        this.momentController = new MomentController(
                addMomentView,
                momentService,
                deleteMomentView,
                filterMomentListView,
                filerByEmotionView,
                filterByDateOfView
        );
    }

    public void setMomentService(MomentService service) {
        this.momentService = service;
        if (this.momentController != null) {
            this.momentController.setMomentService(service);
        }
    }

    public void setMomentController(MomentController controller) {
        this.momentController = controller;
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("My diario:");
                System.out.println("1. Añadir momento");
                System.out.println("2. Ver todos los momentos disponibles");
                System.out.println("3. Eliminar un momento");
                System.out.println("4. Filtrar los momentos");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                int option = scanner.nextInt();
                scanner.nextLine(); 

                if (option == 5) {
                    System.out.println("Hasta la próxima!!!");
                    exit = true;

                } else if (option == 1) {
                    String result = momentController.addMoment();
                    if (!result.endsWith(".")) result = result + ".";
                    System.out.println(result);

                } else if (option == 2) {
                    System.out.println("Momentos registrados:");
                    List<Moment> moments = (momentService != null)
                            ? momentService.getAllMoments()
                            : Collections.emptyList();

                    if (moments.isEmpty()) {
                        System.out.println("No hay momentos registrados.");
                    } else {
                        java.time.format.DateTimeFormatter fmt =
                                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        for (Moment m : moments) {
                            System.out.println(
                                    "ID: " + m.getId()
                                            + " | Título: " + m.getTitle()
                                            + " | Emoción: " + m.getEmotion()
                                            + " | Fecha: " + m.getMomentDate().format(fmt)
                            );
                        }
                    }

                } else if (option == 3) {
                    String result = momentController.deleteMoment();
                    if (!result.endsWith(".")) result = result + ".";
                    System.out.println(result);

                } else if (option == 4) {
                    System.out.println("Filtrar los momentos");
                    List<Moment> moments =
                            (momentController != null) ? momentController.filterMoments() : Collections.emptyList();

                    if (moments.isEmpty()) {
                        System.out.println("No hay momentos registrados.");
                    } else {
                        java.time.format.DateTimeFormatter fmt =
                                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        for (Moment m : moments) {
                            System.out.println("ID: " + m.getId()
                                    + " | Título: " + m.getTitle()
                                    + " | Emoción: " + m.getEmotion()
                                    + " | Fecha: " + m.getMomentDate().format(fmt));
                        }
                    }

                } else {
                    System.out.println("Opción no válida. Por favor, ingrese un número.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Por favor, ingrese un número.");
                scanner.nextLine(); 
            }
        }
    }
}
