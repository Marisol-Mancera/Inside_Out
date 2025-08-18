package dev.marisol.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private InputStream originalIn;

    private MainController mainController;

    @BeforeEach
    public void setUp() {
        originalIn = System.in;
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void shouldShowMenuAndExitWhenOptionFiveIsChosen() {
        String simulatedInput = "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        mainController = new MainController(new Scanner(System.in));
        mainController.start();

        String output = outContent.toString();
        assertTrue(output.contains("1. Añadir momento"));
        assertTrue(output.contains("5. Salir"));
        assertTrue(output.contains("Hasta la próxima!!!"));
    }

    @Test
    public void shouldCreateAMomentWhenOptionOneIsChosen() {
        String simulatedInput = "1\n" +
                "Un día en el parque de atracciones\n" +
                "Moment description\n" +
                "1\n" +
                "01/05/2024\n" +
                "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        mainController = new MainController(new Scanner(System.in));
        mainController.start();

        String output = outContent.toString();
        assertTrue(output.contains("1. Añadir momento"));
        assertTrue(output.contains("Descripción del momento:"));
    }

    @Test
    void shouldAddAndSaveMomentWhenOptionOneIsChosen() {
        String simulatedInput = "1\n" +
                "Un día en el museo de los horrores\n" +
                "Moment description\n" +
                "1\n" +
                "01/05/2024\n" +
                "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        SpyMomentService spy = new SpyMomentService();

        MainController controller = new MainController(new Scanner(System.in));
        controller.setMomentService(spy);

        controller.start();

        assertEquals(1, spy.addCalls);
        String output = outContent.toString();
        assertTrue(output.contains("Momento añadido correctamente."));
    }

    @Test
    void shouldListAllMomentsWhenOptionTwoIsChosen() {
        dev.marisol.service.MomentService service = new dev.marisol.service.MomentService();
        service.addMoment(new dev.marisol.model.Moment(
                10,
                "un viaje inesperado",
                "un viaje que surgio de la nada",
                dev.marisol.model.Emotion.HAPPINESS,
                java.time.LocalDate.of(2024, 5, 1)
        ));
        service.addMoment(new dev.marisol.model.Moment(
                11,
                "se murio mi canario",
                "pues si, la ha palmado",
                dev.marisol.model.Emotion.SADNESS,
                java.time.LocalDate.of(2024, 6, 1)
        ));

        String simulatedInput = "2\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MainController controller = new MainController(new Scanner(System.in));
        controller.setMomentService(service);

        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("Momentos registrados:"));
        assertTrue(output.contains("un viaje inesperado"));
        assertTrue(output.contains("se murio mi canario"));
    }

    @Test
    void shouldDeleteMomentWhenOptionThreeIsChosen() {
        dev.marisol.service.MomentService service = new dev.marisol.service.MomentService();
        service.addMoment(new dev.marisol.model.Moment(
                10, "título a borrar", "desc",
                dev.marisol.model.Emotion.HAPPINESS,
                java.time.LocalDate.of(2024, 5, 1)
        ));
        service.addMoment(new dev.marisol.model.Moment(
                11, "título que queda", "desc",
                dev.marisol.model.Emotion.SADNESS,
                java.time.LocalDate.of(2024, 6, 1)
        ));

        String simulatedInput = "3\n10\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MainController controller = new MainController(new Scanner(System.in));
        controller.setMomentService(service);

        controller.start();

        boolean exists10 = service.getAllMoments().stream().anyMatch(m -> m.getId() == 10);
        assertFalse(exists10, "El momento con id 10 debería haberse eliminado");

        String output = outContent.toString();
        assertTrue(output.contains("ID del momento a eliminar: "));
        assertTrue(output.contains("Momento eliminado correctamente."));
    }

    @Test
    void shouldWarnOnInvalidMenuInputAndContinueToExit() {
        String simulatedInput = "x\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MainController controller = new MainController(new Scanner(System.in));
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("Opción no válida. Por favor, ingrese un número."));
        assertTrue(output.contains("Hasta la próxima!!!"));
    }

    @Test
    void shouldMapEmotionCodeTenToNostalgiaWhenListing() {
        String simulatedInput = "1\n" +
                "título nostalgia\n" +
                "desc\n" +
                "10\n" +                // NOSTALGIA
                "01/07/2024\n" +
                "2\n" +                 // listar
                "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MainController controller = new MainController(new Scanner(System.in));
        controller.setMomentService(new dev.marisol.service.MomentService());

        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("NOSTALGIA"));
        assertTrue(output.contains("título nostalgia"));
    }

    @Test
    void shouldThrowWhenEmotionCodeOutOfRange() {
        String simulatedInput = "1\n" +
                "titulo\n" +
                "desc\n" +
                "11\n" +                // fuera de rango
                "01/07/2024\n" +
                "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        MainController controller = new MainController(new Scanner(System.in));

        assertThrows(IllegalArgumentException.class, controller::start);
    }

    // ==== Spy para observar llamadas a addMoment ====
    static class SpyMomentService extends dev.marisol.service.MomentService {
        int addCalls = 0;
        dev.marisol.model.Moment lastSaved;

        SpyMomentService() {
            super(); // tu MomentService tiene ctor sin args
        }

        @Override
        public void addMoment(dev.marisol.model.Moment moment) {
            addCalls++;
            lastSaved = moment;
        }
    }
}
