package dev.marisol.service;

import dev.marisol.dto.AddMomentDTO;
import dev.marisol.model.Emotion;
import dev.marisol.model.Moment;
import dev.marisol.repository.MomentsRepository;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MomentService {
    private MomentService repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int id;

    public MomentService(MomentService repository) {
        this.repository = repository;
        this.id = 0;
    }

    public void addMoment(AddMomentDTO dto) {
        id++;

        Moment moment = new Moment(
                id,
                dto.getTitle(),
                dto.getDescription(),
                dto.getEmotion(),
                dto.getMomentDate(),
                dto.getIsPositive());

        moment.setModificationDate(LocalDate.now());

        repository.addMoment(moment);
    }

    private String formatMoment(Moment moment) {
        String emotionFormatted = moment.getEmotion().getDisplayName();
        String category;
        if (moment.IsPositive()) {
            category = "Bueno";
        } else {
            category = "Malo";
        }

        return moment.getId() + "-" +
                "Ocurrió el: " + moment.getMomentDate().format(formatter) +
                ". Título: " + moment.getTitle() +
                ". Descripción: " + moment.getDescription() +
                ". Emoción: " + emotionFormatted +
                ". Valoración: " + category;
    }

    public List<String> listMoments() {
        return repository.findAll()
                .stream()
                .map(this::formatMoment)
                .collect(Collectors.toList());
    }

    public String deleteMoment(int opcion) {

        boolean deleted = repository.deleteMoment(opcion);
        if (deleted) {
            return "Momento vivido eliminado correctamente";
        } else {
            return "El identificador proporcionado no existe en la lista";
        }
    }

    public List<String> filterByEmotion(Emotion emotion) {
        return repository.filterByEmotion(emotion)
                .stream()
                .map(this::formatMoment)
                .collect(Collectors.toList());

    }

    public List<String> filterByDate(LocalDate date) {
        return repository.filterByDate(date)
                .stream()
                .map(this::formatMoment)
                .collect(Collectors.toList());
    }

    public void exportToCSV(String filename) {

        List<Moment> momentsToExport = repository.findAll();
        // Usa 'try-with-resources' para asegurar que el archivo se cierre solo.
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            // 1. Escribe la cabecera del CSV
            writer.println("id,title,description,emotion,isPositive,momentDate,creationDate,modificationDate");

            // 2. Recorrer la lista de momentos y escribir cada uno
            for (Moment moment : momentsToExport) {
                // Construye una línea de texto con los datos del momento, separados por comas.
                String line = String.join(",",
                        String.valueOf(moment.getId()),
                        "\"" + moment.getTitle().replace("\"", "\"\"") + "\"", // Encierra en comillas para manejar
                                                                               // comas
                        "\"" + moment.getDescription().replace("\"", "\"\"") + "\"",
                        moment.getEmotion().toString(),
                        String.valueOf(moment.isPositive()),
                        moment.getMomentDate().toString(),
                        moment.getCreatedDate().toString(),
                        moment.getModifiedDate().toString());
                writer.println(line);
            }

            System.out.println("Momentos exportados correctamente a " + filename);

        } catch (IOException e) {
            // Maneja cualquier error que pueda ocurrir al escribir el archivo.
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }

}
