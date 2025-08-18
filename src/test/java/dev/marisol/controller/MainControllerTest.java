package dev.marisol.controller;

import dev.marisol.controller.MainController;
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
        System.setOut(new PrintStream(outContent));

    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void shouldShowMenuAndExitWhenOptionFiveIsChosen() {
        // 1. SIMULA la entrada del usuario PRIMERO
        String simulatedInput = "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // 2. CREA el MainController DESPUÉS, para que use la entrada simulada
        mainController = new MainController(new Scanner(System.in));

        // 3. LLAMA al método que quieres testear
        mainController.start();

        // 4. VERIFICA la salida
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

        // Act
        controller.start();

        // Assert
        assertEquals(1, spy.addCalls);
        String output = outContent.toString();
        assertTrue(output.contains("Momento añadido correctamente."));
    }

    static class SpyMomentService extends dev.marisol.service.MomentService {
        int addCalls = 0;
        dev.marisol.model.Moment lastSaved;

        SpyMomentService() {
            super();
        }

        @Override
        public void addMoment(dev.marisol.model.Moment moment) {
            addCalls++;
            lastSaved = moment;
        }
    }

    @Test
    void shouldListAllMomentsWhenOptionTwoIsChosen() {
        // pre-cargamos el servicio real con dos momentos
        dev.marisol.service.MomentService service = new dev.marisol.service.MomentService();
        service.addMoment(new dev.marisol.model.Moment(
                10,
                "un viaje inesperado",
                "un viaje que surgio de la nada",
                dev.marisol.model.Emotion.HAPPINESS,
                java.time.LocalDate.of(2024, 5, 1)));
        service.addMoment(new dev.marisol.model.Moment(
                11,
                "se murio mi canario",
                "pues si, la ha palmado",
                dev.marisol.model.Emotion.SADNESS,
                java.time.LocalDate.of(2024, 6, 1)));

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

}