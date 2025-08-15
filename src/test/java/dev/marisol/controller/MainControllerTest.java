package dev.marisol.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
