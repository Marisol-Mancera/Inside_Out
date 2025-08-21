package dev.marisol.controller;

import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import dev.marisol.service.MomentService;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private InputStream originalIn;

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

    // Utilidad para inyectar flujo de entrada
    private void setInput(String text) {
        System.setIn(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void shouldShowMenuAndExitWhenOptionFiveIsChosen() {
        setInput("5\n");

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("1. Añadir momento"));
        assertTrue(output.contains("5. Salir"));
        assertTrue(output.contains("Hasta la próxima!!!"));
    }

    @Test
    public void shouldCreateAMomentWhenOptionOneIsChosen() {
        String input = ""
                + "1\n"                                 // Añadir
                + "Un día en el parque de atracciones\n" // título
                + "Moment description\n"                 // descripción
                + "1\n"                                  // emoción
                + "01/05/2024\n"                         // fecha
                + "5\n";                                 // salir
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("1. Añadir momento"));
        assertTrue(output.contains("Ingrese la descripción:"));
        assertTrue(output.contains("Momento añadido correctamente"));
    }

    @Test
    void shouldAddAndSaveMomentWhenOptionOneIsChosen() {
        // Añadir y luego listar para verificar que aparece en la salida
        String input = ""
                + "1\n"
                + "Un día en el museo de los horrores\n"
                + "Moment description\n"
                + "1\n"
                + "01/05/2024\n"
                + "2\n"   // listar
                + "5\n";  // salir
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("Momento añadido correctamente."));
        assertTrue(output.toLowerCase().contains("museo de los horrores"));
    }

    @Test
    void shouldListAllMomentsWhenOptionTwoIsChosen() {
        // Añadimos dos momentos por UI y luego listamos
        String input = ""
                + "1\n" + "un viaje inesperado\n" + "un viaje que surgio de la nada\n" + "1\n" + "01/05/2024\n"
                + "1\n" + "se murio mi canario\n" + "pues si, la ha palmado\n" + "2\n" + "01/06/2024\n"
                + "2\n" // listar
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        // Si ya usas ListMomentsView con ese encabezado, asertea el exacto; dejamos tolerante:
        assertTrue(output.contains("Lista de momentos vividos:") || output.contains("Momentos registrados:"));
        assertTrue(output.toLowerCase().contains("un viaje inesperado"));
        assertTrue(output.toLowerCase().contains("se murio mi canario"));
    }

    @Test
    void shouldDeleteMomentWhenOptionThreeIsChosen() {
        // Añadimos dos momentos (ids 1 y 2) y borramos el 1. Luego listamos.
        String input = ""
                + "1\n" + "título a borrar\n" + "desc\n" + "1\n" + "01/05/2024\n"
                + "1\n" + "título que queda\n" + "desc\n" + "2\n" + "01/06/2024\n"
                + "3\n" + "1\n" // eliminar id=1
                + "2\n"         // listar
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("Momento eliminado correctamente."));
        assertFalse(output.toLowerCase().contains("título a borrar"));
        assertTrue(output.toLowerCase().contains("título que queda"));
    }

    @Test
    void shouldWarnOnInvalidMenuInputAndContinueToExit() {
        setInput("x\n5\n");

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.toLowerCase().contains("opción no válida")
                || output.toLowerCase().contains("opcion no valida"));
        assertTrue(output.contains("Hasta la próxima!!!"));
    }

    @Test
    void shouldMapEmotionCodeTenToNostalgiaWhenListing() {
        // Añadimos con emoción 10 via AddMomentView (selección de 1..n válida)
        String input = ""
                + "1\n"
                + "título nostalgia\n"
                + "desc\n"
                + "10\n"            // NOSTALGIA
                + "01/07/2024\n"
                + "2\n"             // listar
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("NOSTALGIA"));
        assertTrue(output.contains("título nostalgia"));
    }

    @Test
    void shouldRetryOnInvalidEmotionAndThenSucceed() {
        // Primero emoción inválida (11), UI reintenta, luego 1 (válida)
        String input = ""
                + "1\n"
                + "titulo\n"
                + "desc\n"
                + "11\n"  // inválida -> reintento
                + "1\n"   // válida
                + "01/07/2024\n"
                + "2\n"   // listar
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        // Mensajes de AddMomentView ante opción inválida
        assertTrue(output.toLowerCase().contains("número no válido")
                || output.toLowerCase().contains("numero no valido")
                || output.toLowerCase().contains("entrada inválida")
                || output.toLowerCase().contains("entrada invalida"));
        assertTrue(output.contains("Momento añadido correctamente"));
        assertTrue(output.contains("titulo"));
    }

    @Test
    void shouldShowFilteredMomentsWhenOptionFourByEmotionIsChosen() {
        // Añadimos happy y sad, filtramos por emoción 1 (happy)
        String input = ""
                // happy
                + "1\n" + "día feliz\n" + "desc feliz\n" + "1\n" + "01/05/2024\n"
                // sad
                + "1\n" + "día triste\n" + "desc triste\n" + "2\n" + "10/05/2024\n"
                // filtrar -> emoción
                + "4\n" + "1\n" + "1\n"
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.toLowerCase().contains("día feliz"));
        assertFalse(output.toLowerCase().contains("día triste"));
    }

    @Test
    void shouldShowMonthMomentsWhenOptionFourByDateIsChosen() {
        // mayo + junio; filtramos por fecha en junio
        String input = ""
                + "1\n" + "mayo titulo\n" + "desc mayo\n" + "1\n" + "01/05/2024\n"
                + "1\n" + "junio titulo\n" + "desc junio\n" + "1\n" + "10/06/2024\n"
                + "4\n" + "2\n" + "15/06/2024\n"  // filtro por fecha
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.toLowerCase().contains("junio titulo"));
        assertFalse(output.toLowerCase().contains("mayo titulo"));
    }

    @Test
    void shouldShowNoResultsMessageWhenFilterReturnsEmptyList() {
        // Sin añadir nada, filtramos por emoción y elegimos 1 → debería decir que no hay resultados
        String input = ""
                + "4\n" + "1\n" + "1\n"
                + "5\n";
        setInput(input);

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("Filtrar los momentos") || output.contains("Filtrar por"));
        assertTrue(output.contains("No hay momentos registrados."));
    }

    @Test
    void shouldExitWhenOptionFiveIsChosen() {
        setInput("5\n");

        MainController controller = new MainController();
        controller.start();

        String output = outContent.toString();
        assertTrue(output.contains("5. Salir"));
        assertTrue(output.contains("Hasta la próxima!!!"));
    }
}
