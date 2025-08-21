package dev.marisol.dto;

import java.time.LocalDate;
import dev.marisol.model.Emotion;

public class AddMomentDTO {
    private String title;
    private String description;
    private Emotion emotion;
    private LocalDate momentDate;
    private boolean isPositive;
    
    public AddMomentDTO(String title, String description, Emotion emotion, LocalDate momentDate, boolean isPositive) {
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentDate = momentDate;
        this.isPositive = isPositive;
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

    public boolean isPositive(){
        return isPositive;
    }
}