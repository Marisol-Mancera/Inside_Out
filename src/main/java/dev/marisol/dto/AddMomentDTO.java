package dev.marisol.dto;

import java.time.LocalDate;

import dev.marisol.model.Emotion;

public class AddMomentDTO {
    private String title;
    private String description;
    private Emotion emotion;
    private LocalDate momentDate;
    
    public AddMomentDTO(String title, String description, Emotion emotion, LocalDate momentDate) {
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentDate = momentDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public LocalDate getMomentDate() {
        return momentDate;
    }

    
}