package dev.marisol.controller;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import dev.marisol.service.MomentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainController {

    private final Scanner scanner;
    private MomentService momentService;
    private MomentController momentController;

    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setMomentService(MomentService service) {
        this.momentService = service;
    }

    public void setMomentController(MomentController momentController) {
        this.momentController = momentController;
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
                    System.out.print("Título del momento: ");
                    String momentTitle = scanner.nextLine();

                    System.out.print("Descripción del momento: ");
                    String momentDescription = scanner.nextLine();

                    System.out.print("Emoción del momento (1-10): ");
                    int momentEmotion = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Fecha del momento (dd/MM/yyyy): ");
                    String momentDate = scanner.nextLine();

                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate parsedDate = LocalDate.parse(momentDate, fmt);
                    Emotion emotion = mapEmotionFromCode(momentEmotion);

                    Moment newMoment = new Moment(
                            1, // 
                            momentTitle,
                            momentDescription,
                            emotion,
                            parsedDate
                    );

                    if (momentService != null) {
                        momentService.addMoment(newMoment);
                    }

                    System.out.println("Momento añadido correctamente.");

                } else if (option == 2) {
                    System.out.println("Momentos registrados:");
                    List<Moment> moments = (momentService != null)
                            ? momentService.getAllMoments()
                            : Collections.emptyList();

                    if (moments.isEmpty()) {
                        System.out.println("No hay momentos registrados.");
                    } else {
                        for (Moment m : moments) {
                            System.out.println(
                                    "ID: " + m.getId()
                                            + " | Título: " + m.getTitle()
                                            + " | Emoción: " + m.getEmotion()
                                            + " | Fecha: " + m.getMomentDate()
                            );
                        }
                    }

                } else if (option == 3) {
                    System.out.print("ID del momento a eliminar: ");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine();

                    if (momentService != null) {
                        momentService.deleteMoment(idToDelete);
                    }

                    System.out.println("Momento eliminado correctamente.");

                } else if (option == 4){
                    System.out.println("Filtrar los momentos");
                    List<Moment> moments = momentController.filterMoments(); Collections.emptyList();
                    if (moments.isEmpty()){
                        System.out.println("No hay momentos registrados.");
                    } else {
                        for (Moment m : moments) {
                            System.out.println("Id: " + m.getId()
                            + " | Título " + m.getTitle()
                            + " | Emoción " + m.getEmotion()
                            + " | Fecha " + m.getMomentDate());
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

    // helper mínimo para mapear emoción 1..10 -> enum
    private Emotion mapEmotionFromCode(int code) {
        switch (code) {
            case 1:
                return Emotion.HAPPINESS;
            case 2:
                return Emotion.SADNESS;
            case 3:
                return Emotion.ANGER;
            case 4:
                return Emotion.DISGUST;
            case 5:
                return Emotion.FEAR;
            case 6:
                return Emotion.ANXIETY;
            case 7:
                return Emotion.ENVY;
            case 8:
                return Emotion.SHAME;
            case 9:
                return Emotion.BOREDOM;
            case 10:
                return Emotion.NOSTALGIA;
            default:
                throw new IllegalArgumentException("Emoción fuera de rango (1-10)");
        }
    }
}
